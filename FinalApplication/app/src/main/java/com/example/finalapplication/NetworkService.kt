package com.example.finalapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    @GET("getTblDisabledFac")
    fun getList(
        @Query("serviceKey") apikey:String,
        @Query("pageNo") page:String,
        @Query("numOfRows") pageSize:String,
        @Query("resultType") resultType:String, // ""는 문서에 있는 것과 동일한 이름이어야함
        ): Call<MyBody>
}