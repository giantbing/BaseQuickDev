package com.zonlinks.giantbing.guangzhouboard.HttpClient;


import android.util.Log;

import com.google.gson.Gson;
import com.zonlinks.giantbing.guangzhouboard.C;
import com.zonlinks.giantbing.guangzhouboard.Entity.AllData;
import com.zonlinks.giantbing.guangzhouboard.Entity.CheackUpdate;
import com.zonlinks.giantbing.guangzhouboard.Entity.RegEntity;
import com.zonlinks.giantbing.guangzhouboard.Util.DeviceUtil;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.zonlinks.giantbing.guangzhouboard.HttpClient.HttpUtil.pragmaFix;

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

    public static void regDevice(Callback<RegEntity> clallback){
        Retrofit retrofit = new Retrofit.Builder()
                //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
                .baseUrl(C.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StudentService apiStores = retrofit.create(StudentService.class);
        String[] string = new String[2];
        Gson gson = new Gson();
        string[0] = gson.toJson(DeviceUtil.getLocalIpAddress());

        string[1] = gson.toJson(C.getMAC());
        RequestBody requestBody = RequestBody.create(MediaType.parse("Content-Type, application/json"), pragmaFix(string));
        Call<RegEntity> call = apiStores.regDevice(requestBody);
        call.enqueue(clallback);
    }

    public static void getAlldata(Callback<AllData> clallback){
        Retrofit retrofit = new Retrofit.Builder()
                //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
                .baseUrl(C.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StudentService apiStores = retrofit.create(StudentService.class);
        String[] string = new String[1];
        Gson gson = new Gson();
        string[0] = gson.toJson(C.getMAC());
        RequestBody requestBody = RequestBody.create(MediaType.parse("Content-Type, application/json"), pragmaFix(string));
        Call<AllData> call = apiStores.getAlldata(requestBody);
        call.enqueue(clallback);
    }
    public static void checkUpdate(Callback<CheackUpdate> clallback){
        Retrofit retrofit = new Retrofit.Builder()
                //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
                .baseUrl(C.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        StudentService apiStores = retrofit.create(StudentService.class);
        String[] string = new String[1];
        Gson gson = new Gson();
        string[0] = gson.toJson(C.getMAC());
        RequestBody requestBody = RequestBody.create(MediaType.parse("Content-Type, application/json"), pragmaFix(string));
        Call<CheackUpdate> call = apiStores.checkUpdate(requestBody);
        call.enqueue(clallback);
    }

}
