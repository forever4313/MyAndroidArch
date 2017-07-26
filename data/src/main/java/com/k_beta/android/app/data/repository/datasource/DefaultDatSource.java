package com.k_beta.android.app.data.repository.datasource;

import com.k_beta.android.app.data.net.RestApi;

/**
 * Created by Kevin Dong on 2017/7/21.
 */
public class DefaultDatSource  extends AbstractRetrofitDataSource{

    @Override
    public String getApiUrl() {
        return RestApi.API_BASE_URL;
    }
}
