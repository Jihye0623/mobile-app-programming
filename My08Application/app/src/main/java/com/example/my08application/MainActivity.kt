package com.example.my08application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import com.example.my08application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)

        binding.helloBtn.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v:View?){
                Log.d("moblieApp", "helloBtn_CLICK")
            }
        })

        binding.helloBtn.setOnLongClickListener(object: View.OnLongClickListener{
            override fun onLongClick(v: View?): Boolean {
                Log.d("moblieApp", "helloBtn_LONG_CLICK")
                return true
            }
        })
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                Log.d("moblieApp","MotionEvent.ACTION_DOWN")
            }
            MotionEvent.ACTION_UP -> {
                Log.d("moblieApp","MotionEvent.ACTION_UP")
            }

            // +) override fun OnKeyLongPress(KeyCode:Int, event: KeyEvent?):Boolean {...}
        }

        return super.onTouchEvent(event)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode){
            KeyEvent.KEYCODE_0 -> {
                Log.d("moblieApp","KeyEvent.KEYCODE_0")
            }
            KeyEvent.KEYCODE_A -> {
                Log.d("moblieApp","KeyEvent.KEYCODE_A")
            }
//            KeyEvent.KEYCODE_BACK -> {
//                Log.d("moblieApp","KeyEvent.KEYCODE_BACK")
//            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        Log.d("moblieApp","onBackPressed_KeyEvent.KEYCODE_BACK")
    }

    // 또는 override fun onKeyDown(keyCode: Int, event:KeyEvent?):Boolean{
    //           when(keyCode){
    //              KeyEvent.KEYCODE_BACK -> Log.d(...,...)
    //              } return super.onKeyDown(keyCode, event) } 도 가능
    //  on

}

