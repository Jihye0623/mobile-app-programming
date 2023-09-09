package com.example.mid20210694

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mid20210694.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    @RequiresApi(Build.VERSION_CODES.R)
    lateinit var toolbar : ActionBarDrawerToggle

    class MyFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity){
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

        binding.viewpager2.adapter = MyFragmentAdapter(this)

        toolbar = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.syncState()
        binding.mainDrawer.setNavigationItemSelectedListener(this)

        TabLayoutMediator(binding.tabs, binding.viewpager2){
                tab, position -> tab.text = "TAB ${position + 1}"
        }.attach()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu1 -> {
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.duksung.ac.kr"))
                startActivity(intent)
            }
            R.id.menu2 -> {
                var intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:/119"))
                startActivity(intent)
            }
            R.id.menu3 -> {
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.5662952, 126.9779451"))
                startActivity(intent)
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_actionbar, menu)

        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toolbar.onOptionsItemSelected(item)){
            return true
        }

        when(item.itemId){
            R.id.login_menu -> {
                showToast("개발 중입니다.")
            }
            R.id.menu_search -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun showToast(msg:String){
        val t = Toast.makeText(this, msg, Toast.LENGTH_LONG)
        t.addCallback(
            object: Toast.Callback(){
                override fun onToastHidden() {
                    Log.d("mobileApp","onToastHidden")
                }

                override fun onToastShown() {
                    Log.d("mobileApp","onToastshown")
                }
            }
        )
        t.show()
    }
}