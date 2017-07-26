/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.k_beta.android.app.view.activity;

import android.os.Bundle;
import android.view.Window;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.k_beta.android.app.R;
import com.k_beta.android.app.domain.User;
import com.k_beta.android.app.view.fragment.UserListFragment;

/**
 * Activity that shows a list of Users.
 */
@Route(path = "/DemoApp/UserList")
public class UserListActivity extends BaseCommonActivity implements
        UserListFragment.UserListListener {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_user_list);
    if (savedInstanceState == null) {
      addFragment(R.id.fragmentContainer, new UserListFragment());
    }
  }

  @Override public void onUserClicked(User userModel) {
    this.navigator.navigateToUserDetails(userModel.getUserId());
  }

}
