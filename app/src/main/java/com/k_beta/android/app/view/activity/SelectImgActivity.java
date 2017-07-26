package com.k_beta.android.app.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.k_beta.android.app.R;
import com.k_beta.android.app.lib.barcode.core.QRCodeView;
import com.k_beta.android.app.lib.barcode.zxing.ZXingView;
import com.k_beta.android.app.util.GifSizeFilter;
import com.k_beta.android.app.view.component.AutoLoadImageView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import java.util.List;
import java.util.Observer;

/**
 * Created by Kevin Dong on 2017/7/26.
 */
@Route(path = "/DemoApp/SelectImg")
public class SelectImgActivity extends BaseCommonActivity{
    private static final String TAG = SelectImgActivity.class.getSimpleName();
    private static final int REQUEST_CODE_CHOOSE = 23;
    private static final int REQUEST_CODE_CROP_IMG  = 24;


    @BindView(R.id.img_clip_result)
    AutoLoadImageView img;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_img);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_select_one_and_clip)
    void selectAndClipImg(){
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new io.reactivex.Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            Matisse.from(SelectImgActivity.this)
                                    .choose(MimeType.allOf())
                                    .countable(false)
                                    .maxSelectable(1)
                                    .capture(true)
                                    .captureStrategy(
                                            new CaptureStrategy(true, "com.k_beta.android.app.fileprovider"))
                                    .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                    .gridExpectedSize(
                                            getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                                    .thumbnailScale(0.85f)
                                    .imageEngine(new GlideEngine())
                                    .forResult(REQUEST_CODE_CHOOSE);
                        } else {
                            Toast.makeText(SelectImgActivity.this, R.string.permission_request_denied, Toast.LENGTH_LONG)
                                    .show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void showImage(String uri){
        img.setImageUrl(uri);
    }
    /**
     * 跳转到剪裁图片页面
     * @param uri
     */
    private void startCropActivity(Uri uri){
        startActivityForResult(CropImgActivity.callingIntent(this,uri),REQUEST_CODE_CROP_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> uriList = Matisse.obtainResult(data);
            if(uriList.size() == 1){
                startCropActivity(uriList.get(0));
            }
        }else if(requestCode == REQUEST_CODE_CROP_IMG && resultCode == RESULT_OK){
            String uriStr = data.getStringExtra("data");
            showImage(uriStr);
        }
    }



}