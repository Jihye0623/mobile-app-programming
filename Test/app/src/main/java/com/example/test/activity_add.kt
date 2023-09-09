package com.example.test

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.test.databinding.ActivityAddBinding

class activity_add : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    lateinit var str:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mystr = intent.getStringExtra("data")
        str = intent.getStringExtra("data").toString()
        binding.myData.text = mystr


        setSupportActionBar(binding.toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_add_save){
            when(str){
                "할일" -> intent.putExtra("result_todo", "[할일]"+binding.addEditView.text.toString())
                "기념일" -> intent.putExtra("result_date", "[기념일]"+binding.addEditView.text.toString())
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
            return true
        }
        return false
    }
}