package com.example.my18application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.my18application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var volleyFragment: VolleyFragment
    lateinit var retrofitFragment: RetrofitFragment
    lateinit var boardFragment: BoardFragment
    var mode = "volley"     // 현재 내가 무슨 상태인지
    var authMenuItem : MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        volleyFragment= VolleyFragment()
        retrofitFragment= RetrofitFragment()
        boardFragment = BoardFragment()

        // 처음 실행될 때 volley를 보여주겠다
        supportFragmentManager.beginTransaction()               // 프레그먼트 변경할 때 쓰는 메소드
            .replace(R.id.activity_content, volleyFragment)     // R.id.activity_content = activity_main.xml 의미
            .commit()                                           // replace() 명령 실행
        supportFragmentManager.beginTransaction()
            .add(R.id.activity_content, retrofitFragment)
            .hide(retrofitFragment)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.activity_content, boardFragment)
            .hide(boardFragment)
            .commit()
        supportActionBar?.title="Volley Test"                   // 실행시키고 volleyFragment 보여주겠다
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        authMenuItem = menu!!.findItem(R.id.menu_auth)
        if(MyApplication.checkAuth()){
            authMenuItem!!.title = "${MyApplication.email}님"
        }
        else{
            authMenuItem!!.title = "인증"
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onStart() {
        // Intent에서 finish()로 돌아올 때 실행
        // onCreate -> onStart -> onCreateOptionMenu
        super.onStart()

        if(authMenuItem!=null){
            if (MyApplication.checkAuth()){
                authMenuItem!!.title = "${MyApplication.email}님"
            }
            else{
                authMenuItem!!.title = "인증"
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId === R.id.menu_volley && mode !== "volley"){
            supportFragmentManager.beginTransaction()
                .show(volleyFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(retrofitFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(boardFragment)
                .commit()
            mode="volley"
            supportActionBar?.title="Volley Test"
        }
        else if(item.itemId === R.id.menu_retrofit && mode !== "retrofit"){
            supportFragmentManager.beginTransaction()
                .show(retrofitFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(volleyFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(boardFragment)
                .commit()
            mode="retrofit"
            supportActionBar?.title="Retrofit Test"
        }
        else if(item.itemId === R.id.menu_board && mode !== "board"){
            supportFragmentManager.beginTransaction()
                .show(boardFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(volleyFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(retrofitFragment)
                .commit()
            mode="board"
            supportActionBar?.title="Board Test"
        }
        else if(item.itemId === R.id.menu_auth){
            val intent = Intent(this, AuthActivity::class.java)

            if(authMenuItem!!.title!!.equals("인증")){
                intent.putExtra("data", "logout")
            }
            else{   // 이메일, 구글 계정으로 로그인 시
                intent.putExtra("data", "login")
            }

            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}