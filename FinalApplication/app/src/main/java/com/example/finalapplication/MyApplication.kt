package com.example.finalapplication

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 전역 응용 프로그램 상태를 유지하기 위한 기본 클래스
// 첫 번째 액티비티(MainActivity)가 표시되기 전에 전역 상태를 초기화하는 데 사용
class MyApplication: MultiDexApplication() {
    // 전역 변수
    companion object{
        lateinit var db : FirebaseFirestore
        lateinit var storage : FirebaseStorage

        var userName:String? = null

        lateinit var auth : FirebaseAuth
        var email:String? = null

        fun checkAuth():Boolean{
            var currentuser = auth.currentUser
            return currentuser?.let{
                email = currentuser.email
                if(currentuser.isEmailVerified) true
                else false
            } ?: false
        }


        var networkService : NetworkService
        var networkService2 : NetworkService2

        var gson = GsonBuilder().setLenient().create()
        val retrofit: Retrofit
            get() = Retrofit.Builder()
                .baseUrl("http://apis.data.go.kr/6260000/BusanDisabledFacService/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        val retrofit2:Retrofit
            get() = Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        init{
            networkService = retrofit.create(NetworkService::class.java)
            networkService2 = retrofit2.create(NetworkService2::class.java)
        }



    }

    override fun onCreate() {
        super.onCreate()
        auth = Firebase.auth

        db = FirebaseFirestore.getInstance()
        storage = Firebase.storage
    }
}