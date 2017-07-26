package com.k_beta.android.app.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Window;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.k_beta.android.app.R;
import com.k_beta.android.app.contract.UserDetailContract;
import com.k_beta.android.app.domain.User;
import com.k_beta.android.app.framework.view.BaseMvpActivity;
import com.k_beta.android.app.presenter.UserDetailPresenter;
import com.k_beta.android.app.view.component.AutoLoadImageView;

/**
 * Created by Kevin Dong on 2017/7/25.
 */
@Route(path = "/DemoApp/UserDetail")
public class UserDetailActivity extends BaseMvpActivity<UserDetailContract.UserDetailView, UserDetailContract.UserDetailPresenter> implements UserDetailContract.UserDetailView{

    @BindView(R.id.iv_cover)
    AutoLoadImageView img;
    @BindView(R.id.tv_fullname)
    TextView tvFullName;
    @BindView(R.id.tv_followers)
    TextView tvFollower;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    // 从arouter 中获取 userid
    @Autowired
    int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.view_user_details);
        ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        getPresenter().loadUserDetail(userId);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().loadUserDetail(userId);
            }
        });
    }

    @Override
    public void showUser(User user) {
        bindViews(user);
    }

    @Override
    public void hideLoading() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showLoading() {
        refreshLayout.setRefreshing(true);
    }

    private void bindViews(User user){
        if(user != null) {
            img.setImageUrl(user.getCoverUrl());
            tvFullName.setText(user.getFullName());
            tvEmail.setText(user.getEmail());
            tvFollower.setText(String.valueOf(user.getFollowers()));
            tvDescription.setText(user.getDescription());
        }
    }

    @NonNull
    @Override
    public UserDetailContract.UserDetailPresenter createPresenter() {
            return new UserDetailPresenter();
    }
}
