package com.k_beta.android.app.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.k_beta.android.app.MyAndroidApplication;
import com.k_beta.android.app.R;
import com.k_beta.android.app.internal.di.components.ApplicationComponent;
import com.k_beta.android.app.navigation.Navigator;

import javax.inject.Inject;

/**
 * Base {@link android.app.Activity} class for every Activity in this application.
 */
public abstract class BaseCommonActivity extends AppCompatActivity {

  @Inject
  Navigator navigator;

  LinearLayout containerLayout;
  Toolbar toolbar;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getApplicationComponent().inject(this);
  }


  @Override
  public void setContentView(int layoutId) {
    setContentView(View.inflate(this, layoutId, null));
  }
  @Override
  public void setContentView(View view) {
    super.setContentView(R.layout.activity_base_layout);
    containerLayout = (LinearLayout) findViewById(R.id.base_root_layout);
    if (containerLayout == null) return;
    containerLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    initToolbar();
  }

  private void initToolbar() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    if (toolbar != null) {
      setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setHomeButtonEnabled(true);
      toolbar.setNavigationOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          finish();
        }
      });
    }
  }

  protected  void hideToolbar(){
    if(toolbar !=null ) {
      toolbar.setVisibility(View.GONE);
    }
  }

  public void setCustomContentView(int layoutId){
    super.setContentView(layoutId);
  }

  public void setContentView(int layoutId,boolean specifyContent) {
    if(specifyContent){
      super.setContentView(layoutId);
    }else{
      setContentView(layoutId);
    }
  }

  /**
   * Adds a {@link Fragment} to this activity's layout.
   *
   * @param containerViewId The container view to where add the fragment.
   * @param fragment The fragment to be added.
   */
  protected void addFragment(int containerViewId, Fragment fragment) {
    final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
    fragmentTransaction.add(containerViewId, fragment);
    fragmentTransaction.commit();
  }

  /**
   * Get the Main Application component for dependency injection.
   *
   * @return {@link ApplicationComponent}
   */
  protected ApplicationComponent getApplicationComponent() {
    return ((MyAndroidApplication) getApplication()).getApplicationComponent();
  }

}
