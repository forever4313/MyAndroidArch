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
package com.k_beta.android.app.data.net;

import com.k_beta.android.app.data.entity.UserEntity;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestApi {
  String API_BASE_URL =
      "https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture/";

  /** Api url for getting all users */
  String API_URL_GET_USER_LIST = "users.json";
  /** Api url for getting a user profile: Remember to concatenate id + 'json' */
  String API_URL_GET_USER_DETAILS = "user_{user}.json";

  /**
   * Retrieves an {@link Observable} which will emit a List of {@link UserEntity}.
   */
  @GET(RestApi.API_URL_GET_USER_LIST)
  Observable<List<UserEntity>> userEntityList();

  /**
   * Retrieves an {@link Observable} which will emit a {@link UserEntity}.
   *
   * @param userId The user id used to get user data.
   */
  @GET(RestApi.API_URL_GET_USER_DETAILS)
  Observable<UserEntity> userEntityById(@Path("user") int userId);
}
