/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.k_beta.android.app.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.k_beta.android.app.R;
import com.k_beta.android.app.contract.UserListContract;
import com.k_beta.android.app.domain.User;
import com.k_beta.android.app.framework.view.BaseMvpFragment;
import com.k_beta.android.app.presenter.UserListPresenter;
import com.k_beta.android.app.view.adapter.UsersAdapter;

import java.util.List;

/**
 * Fragment that shows a list of Users.
 */
public class UserListFragment extends BaseMvpFragment<UserListContract.UserListView, UserListContract.UserListPresenter> implements UserListContract.UserListView {

    /**
     * Interface for listening user list events.
     */
    public interface UserListListener {
        void onUserClicked(final User userModel);
    }


    @BindView(R.id.rv_users)
    RecyclerView rv_users;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;


    UsersAdapter usersAdapter;

    public UserListFragment() {
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, fragmentView);
        initViews();
        return fragmentView;
    }

    private void initViews() {
        this.usersAdapter = new UsersAdapter();
        this.usersAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (getActivity() instanceof UserListListener) {
                    ((UserListListener)getActivity()).onUserClicked(usersAdapter.getItem(position));
                }
            }
        });
        this.rv_users.setLayoutManager(new LinearLayoutManager(context()));
        this.rv_users.setAdapter(usersAdapter);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().loadUserList();
            }
        });
    }


    @Override
    public void showLoading() {
        refreshLayout.setRefreshing(true);

    }

    @Override
    public void hideLoading() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return getActivity();
    }

    @Override
    public UserListContract.UserListPresenter createPresenter() {
        return new UserListPresenter();
    }


    @Override
    public void addDatas(List<User> users) {
        usersAdapter.addData(users);
    }

    @Override
    public void showDatas(List<User> users) {
        usersAdapter.setNewData(users);
    }
}
