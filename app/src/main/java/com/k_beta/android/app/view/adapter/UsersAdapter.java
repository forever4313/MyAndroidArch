/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 *
 * @author Fernando Cejas (the android10 coder)
 */
package com.k_beta.android.app.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.k_beta.android.app.R;
import com.k_beta.android.app.domain.User;


/**
 * Adaptar that manages a collection of {@link User}.
 */
public class UsersAdapter extends BaseQuickAdapter<User, BaseViewHolder> {

    public UsersAdapter() {
        super(R.layout.row_user, null);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, User item) {
        viewHolder.setText(R.id.title, item.getFullName());

    }


}
