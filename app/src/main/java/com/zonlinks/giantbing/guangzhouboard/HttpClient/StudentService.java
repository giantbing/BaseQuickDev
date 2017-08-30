package com.zonlinks.giantbing.guangzhouboard.HttpClient;


import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by P on 2017/7/10.
 */

public interface StudentService {
        //@Streaming
        @GET
        Call<ResponseBody> getimg(@Url String imgurl);


}
