package com.laioffer.tinnews.retrofit.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseResponse {
    public String status;
    public int totalResults;
    @SerializedName("articles")
    public List<News> articles;
}
