package com.k_beta.android.app.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.internal.Utils;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.k_beta.android.app.MyAndroidApplication;
import com.k_beta.android.app.R;
import com.k_beta.android.app.lib.common.utils.DeviceUtils;
import com.steelkiwi.cropiwa.CropIwaView;
import com.steelkiwi.cropiwa.config.CropIwaSaveConfig;
import com.steelkiwi.cropiwa.shape.CropIwaOvalShape;
import com.steelkiwi.cropiwa.shape.CropIwaRectShape;

import java.io.File;

/**
 * Created by Kevin Dong on 2017/7/26.
 */
@Route(path = "/DemoApp/CropImg")
public class CropImgActivity extends BaseCommonActivity{
    private static final String TAG = CropImgActivity.class.getSimpleName();
    private static final String EXTRA_URI = "EXTRA_CROP_URI";

    public static Intent callingIntent(Context context, Uri imageUri) {
        Intent intent = new Intent(context, CropImgActivity.class);
        intent.putExtra(EXTRA_URI, imageUri.toString());
        return intent;
    }

    @BindView(R.id.crop_view)
    CropIwaView cropView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_img);
        ARouter.getInstance().inject(this);
        ButterKnife.bind(this);
        Uri imgUri = Uri.parse(getIntent().getStringExtra(EXTRA_URI));
        cropView.setImageUri(imgUri);
        cropView.configureImage()
                .setImageScaleEnabled(true)
                .setImageTranslationEnabled(true)
                .apply();
        Paint gridPaint = cropView.configureOverlay()
                .getCropShape()
                .getGridPaint();
        float dashLength = DeviceUtils.dpToPixel(this, 2);
        float spaceLength = DeviceUtils.dpToPixel(this, 4);
        gridPaint.setPathEffect(new DashPathEffect(new float[] {dashLength, spaceLength}, 0));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_crop_img_right_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_done) {
            cropView.crop(new CropIwaSaveConfig.Builder(createNewEmptyFile())
                    .setCompressFormat(Bitmap.CompressFormat.PNG)
                    .setQuality(100) //Hint for lossy compression formats
                    .build());
            cropView.setErrorListener(new CropIwaView.ErrorListener() {
                @Override
                public void onError(Throwable throwable) {

                    Toast.makeText(CropImgActivity.this, "剪裁失败", Toast.LENGTH_SHORT).show();
                }
            });
            cropView.setCropSaveCompleteListener(new CropIwaView.CropSaveCompleteListener() {
                @Override
                public void onCroppedRegionSaved(Uri uri) {
                    Intent intent = getIntent();
                    intent.putExtra("data", uri.toString());
                    setResult(RESULT_OK, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                    finish();//此处一定要调用finish()方法
                }
            });

        }
        return super.onOptionsItemSelected(item);
    }

    public static Uri createNewEmptyFile() {
        return Uri.fromFile(new File(
                MyAndroidApplication.getInstance().getFilesDir(),
                System.currentTimeMillis() + ".png"));
    }

}