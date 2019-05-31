package com.ywj.util.net;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author: ywj
 * @date: 2019/5/31 16:46
 */
public interface TestApi {

    @FormUrlEncoded
    @POST("xxx/xxx")
    Observable<Object> postxxx(@Field("xx") String xx, @Field("xxxx") String xxx);
    @GET("xxx/xxx")
    Observable<Object> getxxx(@Query("xx") String xx, @Query("xxxx") String xxx);
}
