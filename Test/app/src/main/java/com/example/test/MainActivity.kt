package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.test.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var toolbar : ActionBarDrawerToggle

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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu1 -> { Log.d("mobileApp", "네비게이션뷰 메뉴 1") }
            R.id.menu2 -> { Log.d("mobileApp", "네비게이션뷰 메뉴 2") }
            R.id.menu3 -> { Log.d("mobileApp", "네비게이션뷰 메뉴 3") }
            R.id.menu4 -> { Log.d("mobileApp", "네비게이션뷰 메뉴 4") }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_actionbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toolbar.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}