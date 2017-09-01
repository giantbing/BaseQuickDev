package com.zonlinks.giantbing.guangzhouboard.HttpClient;


import com.zonlinks.giantbing.guangzhouboard.Entity.AllData;
import com.zonlinks.giantbing.guangzhouboard.Entity.CheackUpdate;
import com.zonlinks.giantbing.guangzhouboard.Entity.RegEntity;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by P on 2017/7/10.
 */

public interface StudentService {
    @GET
    Call<ResponseBody> getimg(@Url String imgurl);

    @POST("db/regdevice")
    Call<RegEntity> regDevice(@Body RequestBody json);

    @POST("biz/getalldata")
    Call<AllData> getAlldata(@Body RequestBody json);

    @POST("db/checkupdate")
    Call<CheackUpdate> checkUpdate(@Body RequestBody json);

}
