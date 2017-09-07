package com.zonlinks.giantbing.guangzhouboard.HttpClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by P on 2017/8/15.
 * @param <T> Successful response body type.
 */

public class MyCallBack implements Callback<Object> {

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {

    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {

    }
}
