package com.example.mytodo.net;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebServices {

    @GET("get_todo")
    Call<GetToDoResponse> getAllTodo();

    @FormUrlEncoded
    @POST("add_todo")
    Call<AddToDoResponse> addTodo(
            @Field("title") String title,
            @Field("details") String details
    );
}
