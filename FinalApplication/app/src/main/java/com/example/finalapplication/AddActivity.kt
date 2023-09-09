package com.example.finalapplication

import android.app.Activity
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.finalapplication.databinding.ActivityAddBinding

// oneFragment - 투두 리스트 추가 화면
class AddActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mystr = intent.getStringExtra("data")
        binding.myData.text = mystr

        binding.addBtn.setOnClickListener{
            intent.putExtra("result", binding.addEditView.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}