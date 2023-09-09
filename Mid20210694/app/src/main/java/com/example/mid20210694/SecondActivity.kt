package com.example.mid20210694

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.mid20210694.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var str:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mystr = intent.getStringExtra("data")
        str = intent.getStringExtra("data").toString()
        binding.titleText.text = mystr + " 추가하기"

        binding.button.setOnClickListener{
            when(str){
                "친구" -> {
                    intent.putExtra("result_friend_name", "[친구]" + binding.nameEdit.text.toString())
                    intent.putExtra("result_friend_phone", binding.phoneEdit.text.toString())
                }

                "장소" -> {
                    intent.putExtra("result_place_name", "[장소]" + binding.nameEdit.text.toString())
                    intent.putExtra("result_place_phone", binding.phoneEdit.text.toString())

                }
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}