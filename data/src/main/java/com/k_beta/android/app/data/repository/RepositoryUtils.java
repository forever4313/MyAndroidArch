package com.k_beta.android.app.data.repository;


import com.k_beta.android.app.data.MyResponse;
import com.k_beta.android.app.data.exception.MyException;
import com.k_beta.android.app.data.util.GsonUtil;
import io.reactivex.Observable;

/**
 * Created by Kevin Dong on 2016/5/15.
 */
public class RepositoryUtils {

    public static <T> Observable<T> extractData(Observable<MyResponse> observable, final Class<T> clazz) {
        return observable.flatMap(myResponse -> {
            if(myResponse == null){
                return Observable.error(MyException.networkError("response is null"));
            }else if(myResponse.getCode() == MyResponse.CODE_OK){
                return Observable.just(GsonUtil.fromGson(myResponse.getDatas(), clazz));
            }else {
                return Observable.error(MyException.serverResponseError(myResponse));
            }
        });
    }

}
