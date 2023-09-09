package com.example.test

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.databinding.FragmentOneBinding
import com.example.test.databinding.FragmentTwoBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TwoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TwoFragment : Fragment() {
    var datas: MutableList<String>? = null
    lateinit var adapter: MyAdapter

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
        var binding = FragmentTwoBinding.inflate(inflater, container, false)
        var todoText="My Todo List"

        // 투두 불러오기
        var requestlauncher : ActivityResultLauncher<Intent>
                = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            when(todoText){
                "할일"-> it.data!!.getStringExtra("result_todo")?.let{
                    datas?.add(it)
                    adapter.notifyDataSetChanged()
                }

                "기념일" -> it.data!!.getStringExtra("result_date")?.let{
                    datas?.add(it)
                    adapter.notifyDataSetChanged()
                }
            }

        }

        binding.radioButton.setOnClickListener{
            todoText = "할일"
        }

        binding.radioButton2.setOnClickListener{
            todoText = "기념일"
        }

        // 버튼 - 클릭시 addActivity로 넘어감
        binding.eFab.setOnClickListener{
            // 실행하고자 하는 인텐트를 시스템에 전달 (this가 addActivity를 호출)
            var intent = Intent(context, activity_add::class.java)

            // 인텐트에 엑스트라 데이터를 추가하는 함수
            intent.putExtra("data",todoText)
            requestlauncher.launch(intent)
        }

        datas=savedInstanceState?.let{
            it.getStringArrayList("datas")?.toMutableList()
        } ?: let{ mutableListOf<String>() }

        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager=layoutManager
        adapter=MyAdapter(datas)
        binding.recyclerView.adapter=adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        )

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TwoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TwoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}