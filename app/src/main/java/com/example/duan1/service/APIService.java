package com.example.duan1.service;

import com.example.duan1.model.Message;
import com.example.duan1.model.Mon;
import com.example.duan1.model.RQMon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    String base_link = "https://sub2.tridinhne.click/api/";
    @GET("GetList.php")
    Call<ArrayList<Mon>> GetDS();

    @GET("GetListWithCondition.php")
    Call<ArrayList<Mon>> GetDSDK(@Query("idLoaiMon")int idLoaiMon);

    @POST("Add.php")
    Call<Message> Them(@Body RQMon rqMon);

    @POST("Update.php")
    Call<Message> Sua(@Body RQMon rqMon);

    @GET("GetMonById.php")
    Call<ArrayList<Mon>> getMonById(@Query("idMon") int idMon);

    @POST("TaoHoaDon.php")
    Call<Void> createBill(@Body Map<String, Object> payload);

    @POST("notifyNV.php")
    Call<Void> sendNotificationWithIdBan(@Body Map<String, Object> payload);

}
