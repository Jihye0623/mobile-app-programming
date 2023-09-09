package com.example.my11application

import MyAdapter
import MyDecoration
import android.app.AlertDialog
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.component2
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my11application.databinding.FragmentOneBinding
import com.example.my11application.databinding.ItemRecyclerviewBinding

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

    // 이곳에서 모든 작업 이루어진다!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 바인딩하기
        var binding = FragmentOneBinding.inflate(inflater, container, false)

        // this가 아니라 context여야 (activity와 차이점)
        // Toast.makeText(context , "프레그먼트 토스트", Toast.LENGTH_LONG.show())

        // 수정가능한 mutable로
        val datas = mutableListOf<String>()

        // 아이템 추가
        for (i in 1..20){
            datas.add("Item $i")
        }

        // binding.recyclerView.layoutManager = LinearLayoutManager(context)   // activity

        // 리사이클러 뷰 - layoutManager, adapter는 필수로 선언하기
        // fragment_one.xml과 연결
        // GridLayoutManager, LinearLayoutManager, StaggeredGridLayoutManager
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = MyAdapter(datas)

        // 뒤에 이미지 넣기
        binding.recyclerView.addItemDecoration(MyDecoration(activity as Context))

        // Extended Button
        binding.fab.setOnClickListener{
            AlertDialog.Builder(context).run{
                setTitle("테스트 알림창")
                setMessage("데이터를 추가하시겠습니까?")
                setPositiveButton("OK", null)
                setNegativeButton("Cancel", null)
                show()
            }

//            when(binding.fab.isExtended){
//                true -> binding.fab.shrink()
//                false -> binding.fab.extend()
//            }

        }

        return binding.root
//        return inflater.inflate(R.layout.fragment_one, container, false)  : 첫번째+마지막 줄과 동일한 기능
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