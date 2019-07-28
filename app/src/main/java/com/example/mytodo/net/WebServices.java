package com.example.mytodo.net;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebServices {

    @GET("get_todo")
    Call<GetToDoResponse> getAllTodo();
}
