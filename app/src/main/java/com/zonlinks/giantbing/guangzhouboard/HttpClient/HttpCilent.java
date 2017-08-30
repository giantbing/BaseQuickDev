package com.zonlinks.giantbing.guangzhouboard.HttpClient;


import com.zonlinks.giantbing.guangzhouboard.C;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by P on 2017/7/7.
 */

public class HttpCilent {

    public static void putimg(Callback<ResponseBody> clallback,String imgUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
                .baseUrl(C.TESTPICURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StudentService apiStores = retrofit.create(StudentService.class);


        Call<ResponseBody> call = apiStores.getimg(imgUrl);
        call.enqueue(clallback);
    }

}
