package com.example.my12application

import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my12application.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var datas: MutableList<String>? = null
    lateinit var adapter: MyAdapter

    lateinit var sharedPreferences:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        // 투두 불러오기
        var requestlauncher : ActivityResultLauncher<Intent>
            = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                it.data!!.getStringExtra("result")?.let{
                    datas?.add(it)
                    adapter.notifyDataSetChanged()

                    // database 연동
                    var db:SQLiteDatabase = DBHelper(this).writableDatabase
                    // SQL: insert into 테이블명 (필드명) values(값)
                    db.execSQL("insert into todo_tbl (todo) values (?)", arrayOf<String>(it))
                    db.close()
                }
        }

        // 버튼 - 클릭시 addActivity로 넘어감
        binding.mainFab.setOnClickListener{
            // 실행하고자 하는 인텐트를 시스템에 전달 (this가 addActivity를 호출)
            var intent = Intent(this, AddActivity::class.java)

            // 인텐트에 엑스트라 데이터를 추가하는 함수
            intent.putExtra("data","My Todo List")
//            startActivity(intent)
//            ActivityResultLauncher
            requestlauncher.launch(intent)
        }

        /* DB 사용하면 saveInstanceState 사용 안해도 됨
        datas=savedInstanceState?.let{
            it.getStringArrayList("datas")?.toMutableList()
        } ?: let{ mutableListOf<String>() }
        */

        // DB 테이블 읽기
        // 항상 리사이클러 뷰 위쪽에 작성하기
        datas = mutableListOf<String>()
        val db:SQLiteDatabase = DBHelper(this).readableDatabase
        var cursor:Cursor = db.rawQuery("select * from todo_tbl", null)         // 커서가 첫번째 데이터 가리킴
        while(cursor.moveToNext()){
            datas?.add(cursor.getString(1))
        }
        db.close()

        val layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.layoutManager=layoutManager
        adapter=MyAdapter(datas)
        binding.mainRecyclerView.adapter=adapter
        binding.mainRecyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }

    // 새로고침마다 설정 내용 반영하기
    override fun onResume() {
        super.onResume()

        val bgColor:String? = sharedPreferences.getString("bg_color", "")
        binding.mainRecyclerView.setBackgroundColor(Color.parseColor(bgColor))

        // 어댑터가 바뀌었으니 반영하라
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val file: File = File(filesDir, "todo_list.txt")
        // 첫번째는 안드로이드에서 주어진 변수명, 두번째는 내가 맘대로 정하기

        when(item.itemId){
            R.id.menu_save_file -> {
                // 읽기
                val writeStream:OutputStreamWriter = file.writer()
                writeStream.write("hello android!!!\n")

                // data의 크기만큼 반복
                for(i in datas!!.indices)
                    writeStream.write(datas!![i]+"\n")

                // flush()를 해줘야 직접적으로 파일에 작성
                writeStream.flush()
                return true
            }
            R.id.menu_read_file -> {
                // 쓰기기
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
        }
        return false
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putStringArrayList("datas", ArrayList(datas))
    }

}