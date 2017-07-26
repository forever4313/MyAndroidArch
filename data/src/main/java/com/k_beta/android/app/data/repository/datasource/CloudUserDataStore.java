/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.k_beta.android.app.data.repository.datasource;

import com.k_beta.android.app.data.cache.UserCache;
import com.k_beta.android.app.data.entity.UserEntity;
import com.k_beta.android.app.data.net.RestApi;
import com.k_beta.android.app.data.repository.RepositoryUtils;
import io.reactivex.Observable;
import java.util.List;

/**
 * {@link UserDataStore} implementation based on connections to the api (Cloud).
 */
class CloudUserDataStore  extends  DefaultDatSource implements UserDataStore  {

  private final UserCache userCache;

  /**
   * Construct a {@link UserDataStore} based on connections to the api (Cloud).
   *
   * @param userCache A {@link UserCache} to cache data retrieved from the api.
   */
  CloudUserDataStore(UserCache userCache) {
    this.userCache = userCache;
  }

  @Override public Observable<List<UserEntity>> userEntityList() {
    return this.restApi.userEntityList();
  }

  @Override public Observable<UserEntity> userEntityDetails(final int userId) {
    return this.restApi.userEntityById(userId).doOnNext(CloudUserDataStore.this.userCache::put);
  }
}
