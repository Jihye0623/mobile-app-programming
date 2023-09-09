package com.example.my11application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.my11application.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
// alt+Enter : 자동 import

// 드루어 레이아웃 : 네비게이션 사용하려면 onNavigationItemSelectedListener 상속 받아야 함
class MainActivity : AppCompatActivity(), OnNavigationItemSelectedListener {
    // 드루어 레이아웃 : onCreated와 onOptionsItemSelected 함수 두 곳에서 모두 사용 가능 하도록
    lateinit var toolbar : ActionBarDrawerToggle

    // 프레그먼트 - 뷰페이저 - 뷰페이저 아답터 설정
    class MyFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity){
        // oneFragement, TwoFragment, ThreeFragment를 스와이프하겠다
        val fragments: List<Fragment>
        init{
            fragments = listOf(OneFragment(), TwoFragment(), ThreeFragment())
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

        override fun getItemCount(): Int {
            return fragments.size
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 툴바 - 툴바 사용하겠다고 선언
        setSupportActionBar(binding.toolbar)

        // 뷰페이저 - 뷰페이저 사용하겠다, 아답터 연결
        binding.viewpager2.adapter = MyFragmentAdapter(this)

        // 드로어 레이아웃 - 토글 버튼을 만들어 액션바(툴바)에 붙임
        // ActionVarDrawerToggle은 반드시 R.string에 있는 파일을 써야 함
        toolbar = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.syncState()
        binding.mainDrawer.setNavigationItemSelectedListener(this)

        // 탭 레이아웃 - 연결하기
        TabLayoutMediator(binding.tabs, binding.viewpager2){
            tab, position -> tab.text = "TAB ${position + 1}"
        }.attach()
    }

    // 드루어 레이아웃: onNavigationItemSelectedListener 상속 시 필수 구현 메소드
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu1 -> { Log.d("mobileApp", "네비게이션뷰 메뉴 1") }
            R.id.menu2 -> { Log.d("mobileApp", "네비게이션뷰 메뉴 2") }
            R.id.menu3 -> { Log.d("mobileApp", "네비게이션뷰 메뉴 3") }
            R.id.menu4 -> { Log.d("mobileApp", "네비게이션뷰 메뉴 4") }
        }
        return true
    }

    // 액션바(툴바)의 옵션
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // 툴바 - res의 menu의 menu_actionbar.xml 파일 불러옴
        menuInflater.inflate(R.menu.menu_actionbar, menu)

        // 툴바 - search 설정
        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("mobileApp", "검색어 : $query")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    // 툴바 - 액션바(툴바)의 메뉴가 선택될 때 마다 불려짐
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 드루어 레이아웃 - 만들어놓은 토글 버튼이 클릭되었을 때
        if (toolbar.onOptionsItemSelected(item)){
                return true
        }

        when(item.itemId){
            R.id.menu1 -> {
                Log.d("mobileApp", "Menu1")
            }
            R.id.menu2 -> {}
            R.id.menu_search -> {}
        }
        return super.onOptionsItemSelected(item)
    }
}