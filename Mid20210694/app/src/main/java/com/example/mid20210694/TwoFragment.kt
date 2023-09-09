package com.example.mid20210694

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mid20210694.databinding.FragmentOneBinding
import com.example.mid20210694.databinding.FragmentTwoBinding

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

        var todoText=""

        var requestlauncher : ActivityResultLauncher<Intent>
                = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            var dataStr=""
            when(todoText){
                "친구"-> {
                    it.data!!.getStringExtra("result_friend_name")?.let {
                        dataStr=it
                    }

                    it.data!!.getStringExtra("result_friend_phone")?.let {
                        dataStr=dataStr+"\n"+it
                    }
                    datas?.add(dataStr)
                    adapter.notifyDataSetChanged()

                }
                "장소" -> {
                    it.data!!.getStringExtra("result_place_name")?.let {
                        dataStr=it
                    }

                    it.data!!.getStringExtra("result_place_phone")?.let {
                        dataStr=dataStr+"\n"+it
                    }
                    datas?.add(dataStr)
                    adapter.notifyDataSetChanged()
                }
            }

        }


        binding.fab.setOnClickListener{
            if (binding.radioButton.isChecked){
                todoText = binding.radioButton.text.toString()
            }
            else if (binding.radioButton2.isChecked){
                todoText = binding.radioButton2.text.toString()
            }

            var intent = Intent(context, SecondActivity::class.java)

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