package com.example.small_iot.Service;

import com.example.small_iot.Model.DataIn;
import com.example.small_iot.Model.DataOut;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface getService {
    @GET("IotIn")
    Call<List<DataIn>> getDataIn();

    @GET("IotOut")
    Call<List<DataOut>> getDataOut();

    @FormUrlEncoded
    @PUT("IotOut")
    Call<List<DataOut>> putOut(@Field("id_data") String id,
                             @Field("nama_data") String nama,
                             @Field("value_data") String value);
}
