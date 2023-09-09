package com.example.finalapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalapplication.databinding.FragmentSixBinding
import com.example.finalapplication.databinding.FragmentTwoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SixFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SixFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding : FragmentSixBinding

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
        binding = FragmentSixBinding.inflate(inflater, container, false)

        binding.searchButton.setOnClickListener{
            var call: Call<SearchListResponse> = MyApplication.networkService2.getList(
                //API KEY,
                binding.input1.text.toString(),
                "video",
                "snippet"
            )

            call?.enqueue(object :Callback<SearchListResponse>{
                override fun onResponse(
                    call: Call<SearchListResponse>,
                    response: Response<SearchListResponse>
                ) {
                    if(response.isSuccessful){
                        binding.recyclerYoutube.layoutManager = LinearLayoutManager(context)
                        binding.recyclerYoutube.adapter = MyYoutubeAdapter(activity as Context, response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<SearchListResponse>, t: Throwable) {
                    Log.d("mobileApp", "youtube error.....")
                }
            })
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SixFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SixFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}