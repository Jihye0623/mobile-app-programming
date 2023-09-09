package com.example.my18application

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {
    // http://apis.data.go.kr/B553748/CertImgListService/getCertImgListService?
    // getCertImgListService? 뒤에 아래 다섯개가 붙음
    @GET("getCertImgListService")
    fun getList(
        @Query("prdlstNm") q:String,
        @Query("ServiceKey") apikey:String,
        @Query("pageNo") page:Long,
        @Query("numOfRows") pageSize:Int,
        @Query("returnType") returnType:String, // ""는 문서에 있는 것과 동일한 이름이어야함
        ): Call<MyModel>
}