package com.example.finalapplication

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.preference.PreferenceManager
import com.example.finalapplication.databinding.ActivityMainBinding
import com.example.finalapplication.databinding.NavigationHeaderBinding
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter


class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener {
    lateinit var binding: ActivityMainBinding
    lateinit var toolbar : ActionBarDrawerToggle


    lateinit var oneFragment: OneFragment
    lateinit var twoFragment: TwoFragment
    lateinit var threeFragment: ThreeFragment
    lateinit var fourFragment: FourFragment
    lateinit var fiveFragment: FiveFragment
    lateinit var sixFragment: SixFragment
    lateinit var sevenFragment: SevenFragment
    lateinit var eightFragment: EightFragment

    var mode = "one"
    var authMenuItem : MenuItem? = null

    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        oneFragment = OneFragment()
        twoFragment = TwoFragment()
        threeFragment = ThreeFragment()
        fourFragment = FourFragment()
        fiveFragment = FiveFragment()
        sixFragment = SixFragment()
        sevenFragment = SevenFragment()
        eightFragment = EightFragment()

        // 드로어 레이아웃 - 토글 버튼을 만들어 액션바(툴바)에 붙임
        // ActionVarDrawerToggle은 반드시 R.string에 있는 파일을 써야 함
        toolbar = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.syncState()
        binding.mainDrawer.setNavigationItemSelectedListener(this)



        // frame 초기 설정
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, oneFragment)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.frame, twoFragment)
            .hide(twoFragment)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.frame, threeFragment)
            .hide(threeFragment)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.frame, fourFragment)
            .hide(fourFragment)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.frame, fiveFragment)
            .hide(fiveFragment)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.frame, sixFragment)
            .hide(sixFragment)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.frame, sevenFragment)
            .hide(sevenFragment)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.frame, eightFragment)
            .hide(eightFragment)
            .commit()
        supportActionBar?.title="할 일"
    }

    // 새로고침마다 설정 내용 반영하기
    override fun onResume() {
        super.onResume()

        val userName:String? = sharedPreferences.getString("id","")
        if(sharedPreferences.getBoolean("power", true)){
            Toast.makeText(this, "${userName} 안녕하세요!", Toast.LENGTH_LONG).show()
        }

    }


    // 액션바(툴바)의 옵션
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 툴바 - res의 menu의 menu_actionbar.xml 파일 불러옴
        menuInflater.inflate(R.menu.menu_actionbar, menu)

        authMenuItem = menu!!.findItem(R.id.menu_auth)
        if(MyApplication.checkAuth()){
            authMenuItem!!.title = "${MyApplication.email}님"
        }
        else{
            authMenuItem!!.title = "인증"
        }

        return super.onCreateOptionsMenu(menu)
    }

    // 툴바 - 액션바(툴바)의 메뉴가 선택될 때 마다 불려짐
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 드루어 레이아웃 - 만들어놓은 토글 버튼이 클릭되었을 때
        if (toolbar.onOptionsItemSelected(item)){
            return true
        }

        // 파일 읽기
        val file: File = File(filesDir, "todo_list.txt")

        when(item.itemId){
            R.id.menu1 -> {}
            R.id.menu2 -> {}

            R.id.menu_save_file -> {
                // 읽기
                val writeStream: OutputStreamWriter = file.writer()

                // data의 크기만큼 반복
                for(i in oneFragment.datas!!.indices)
                    writeStream.write(oneFragment.datas!![i]+"\n")

                // flush()를 해줘야 직접적으로 파일에 작성
                writeStream.flush()
                return true
            }

            R.id.menu_read_file -> {
                // 쓰기
                val readStream: BufferedReader = file.reader().buffered()

                // 제대로 읽었는지 확인
                readStream.forEachLine {
                    Log.d("mobileApp", "$it")
                }
                return true
            }

            R.id.menu_main_setting -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                return true
            }

            R.id.menu_auth -> {
                val intent = Intent(this, AuthActivity::class.java)

                if(authMenuItem!!.title!!.equals("인증")){
                    intent.putExtra("data", "logout")
                }
                else{   // 이메일, 구글 계정으로 로그인 시
                    intent.putExtra("data", "login")
                }

                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
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

    // 드루어 레이아웃: onNavigationItemSelectedListener 상속 시 필수 구현 메소드
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if(item.itemId === R.id.menu1 && mode !== "one"){
            supportFragmentManager.beginTransaction()
                .show(oneFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(twoFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(threeFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(fourFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(fiveFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(sixFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(sevenFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(eightFragment)
                .commit()
            mode="one"
            supportActionBar?.title="할 일"
        }
        else if(item.itemId === R.id.menu2 && mode !== "two"){
            supportFragmentManager.beginTransaction()
                .show(twoFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(oneFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(threeFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(fourFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(fiveFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(sixFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(sevenFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(eightFragment)
                .commit()
            mode="two"
            supportActionBar?.title="커뮤니티"
        }
        else if(item.itemId === R.id.menu3 && mode !== "three"){
            supportFragmentManager.beginTransaction()
                .show(threeFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(oneFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(twoFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(fourFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(fiveFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(sixFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(sevenFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(eightFragment)
                .commit()
            mode="three"
            supportActionBar?.title="정보"
        }
        else if(item.itemId === R.id.menu4 && mode !== "four"){
            supportFragmentManager.beginTransaction()
                .show(fourFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(oneFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(twoFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(threeFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(fiveFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(sixFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(sevenFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(eightFragment)
                .commit()
            mode="four"
            supportActionBar?.title="전화"
        }
        else if(item.itemId === R.id.menu5 && mode !== "five"){
            supportFragmentManager.beginTransaction()
                .show(fiveFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(oneFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(twoFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(fourFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(threeFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(sixFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(sevenFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(eightFragment)
                .commit()
            mode="five"
            supportActionBar?.title="의사소통"
        }
        else if(item.itemId === R.id.menu6 && mode !== "six"){
            supportFragmentManager.beginTransaction()
                .show(sixFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(oneFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(twoFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(fourFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(fiveFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(threeFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(sevenFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(eightFragment)
                .commit()
            mode="six"
            supportActionBar?.title="유튜브"
        }
        else if(item.itemId === R.id.menu7 && mode !== "seven"){
            supportFragmentManager.beginTransaction()
                .show(sevenFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(oneFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(twoFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(fourFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(fiveFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(threeFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(sixFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(eightFragment)
                .commit()
            mode="seven"
            supportActionBar?.title="그래프"
        }

        else if(item.itemId === R.id.menu8 && mode !== "eight"){
            supportFragmentManager.beginTransaction()
                .show(eightFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(oneFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(twoFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(fourFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(fiveFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(threeFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(sixFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .hide(sevenFragment)
                .commit()
            mode="eight"
            supportActionBar?.title="우편번호"
        }
        return true
    }
}