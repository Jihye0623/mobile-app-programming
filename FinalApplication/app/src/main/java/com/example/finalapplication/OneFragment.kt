package com.example.finalapplication

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalapplication.databinding.FragmentOneBinding
import com.example.finalapplication.databinding.ItemRecyclerviewBinding
import com.example.finalapplication.databinding.NavigationHeaderBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OneFragment : Fragment() {
    var datas: MutableList<String>? = null

    lateinit var adapter: MyAdapter
    lateinit var binding: FragmentOneBinding

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOneBinding.inflate(inflater, container, false)


        // 투두 불러오기
        var requestlauncher : ActivityResultLauncher<Intent>
                = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            it.data!!.getStringExtra("result")?.let{
                datas?.add(it)
                adapter.notifyDataSetChanged()

                // database 연동
                var db:SQLiteDatabase = DBHelper(activity as Context).writableDatabase
                // SQL: insert into 테이블명 (필드명) values(값)
                db.execSQL("insert into todo_tbl (todo) values (?)", arrayOf<String>(it))
                db.close()
            }
        }

        // DB 테이블 읽기
        // 항상 리사이클러 뷰 위쪽에 작성하기
        datas = mutableListOf<String>()
        val db:SQLiteDatabase = DBHelper(activity as Context).readableDatabase
        var cursor: Cursor = db.rawQuery("select * from todo_tbl", null)         // 커서가 첫번째 데이터 가리킴
        while(cursor.moveToNext()){
            datas?.add(cursor.getString(1))
        }
        db.close()

        val layoutManager = LinearLayoutManager(context)
        binding.oneRecyclerView.layoutManager=layoutManager
        adapter=MyAdapter(datas)
        binding.oneRecyclerView.adapter=adapter
        binding.oneRecyclerView.addItemDecoration(
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        )


        // 버튼 - 클릭시 addActivity로 넘어감
        binding.oneFab.setOnClickListener{
            // 실행하고자 하는 인텐트를 시스템에 전달 (this가 addActivity를 호출)
            var intent = Intent(context, AddActivity::class.java)

            // 인텐트에 엑스트라 데이터를 추가하는 함수
            intent.putExtra("data","My Todo List")
            requestlauncher.launch(intent)
        }

        return binding.root
    }

    // 새로고침할 때마다 실행
    override fun onResume() {
        super.onResume()

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity as Context)

        val bgColor:String? = sharedPreferences.getString("bg_color", "#FFFFFF")
        binding.oneRecyclerView.setBackgroundColor(Color.parseColor(bgColor))

        adapter.notifyDataSetChanged()

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OneFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}