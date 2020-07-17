package com.example.small_iot.Model;

import com.google.gson.annotations.SerializedName;

public class DataOut {
    @SerializedName("id_data")
    private String id_data;
    @SerializedName("nama_data")
    private String nama_data;
    @SerializedName("value_data")
    private String value_data;

    public DataOut(String id_data, String nama_data, String value_data){
        this.id_data = id_data;
        this.nama_data = nama_data;
        this.value_data = value_data;
    }

    public String getId_data() {
        return id_data;
    }

    public String getNama_data() {
        return nama_data;
    }

    public String getValue_data() {
        return value_data;
    }
}
