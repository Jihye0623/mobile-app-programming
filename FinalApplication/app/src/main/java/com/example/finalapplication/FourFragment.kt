package com.example.finalapplication

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.finalapplication.databinding.FragmentFourBinding
import com.example.finalapplication.databinding.FragmentOneBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FourFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FourFragment : Fragment() {

    lateinit var binding: FragmentFourBinding

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
        binding = FragmentFourBinding.inflate(inflater, container, false)

        binding.btnCall.setOnClickListener{
            var intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:/119"))
            startActivity(intent)

        }

        binding.btnCall2.setOnClickListener{
            var intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:/02-901-8000"))
            startActivity(intent)

        }

        binding.btnCall3.setOnClickListener{
            var intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:/010-9156-6233"))
            startActivity(intent)

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
         * @return A new instance of fragment FourFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FourFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}