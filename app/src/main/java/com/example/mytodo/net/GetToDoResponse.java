package com.example.mytodo.net;

import com.example.mytodo.db.ToDo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetToDoResponse extends BaseResponse {

    @SerializedName("data")
    public List<ToDo> data;

}
