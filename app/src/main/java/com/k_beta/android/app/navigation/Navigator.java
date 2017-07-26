/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.k_beta.android.app.navigation;

import android.net.Uri;
import com.alibaba.android.arouter.launcher.ARouter;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

  @Inject
  public Navigator() {
    //empty
  }

  /**
   * Goes to the user list screen.
   *
   * A Context needed to open the destiny activity.
   */
  public void navigateToUserList() {
    ARouter.getInstance().build("/DemoApp/UserList").navigation();
  }

  /**
   * Goes to the user details screen.
   *
   * A Context needed to open the destiny activity.
   */
  public void navigateToUserDetails( int userId) {
    ARouter.getInstance().build("/DemoApp/UserDetail")
            .withInt("userId",userId)
            .navigation();
  }

  public void navigateToQrScan() {

    ARouter.getInstance().build("/DemoApp/QRScan")
            .navigation();
  }

  public void navigateToSelectImg() {

    ARouter.getInstance().build("/DemoApp/SelectImg")
            .navigation();

  }

  public void navigateToCropImgActivity(Uri uri) {
    ARouter.getInstance().build("/DemoApp/CropImg")
            .withString("imagUriStr",uri.toString())
            .navigation();
  }
}
