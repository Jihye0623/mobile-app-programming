package com.example.finalapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.finalapplication.databinding.FragmentSevenBinding
import com.example.finalapplication.databinding.FragmentSixBinding
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SevenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SevenFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding : FragmentSevenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    // 프레그먼트 - 뷰페이저 - 뷰페이저 아답터 설정
    class MyFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity){
        // oneFragement, TwoFragment, ThreeFragment를 스와이프하겠다
        val fragments: List<Fragment>
        init{
            fragments = listOf(LineFragment(), RadarFragment())
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

        override fun getItemCount(): Int {
            return fragments.size
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSevenBinding.inflate(inflater, container, false)

        // 뷰페이저 - 뷰페이저 사용하겠다, 아답터 연결
        binding.viewpager2.adapter = MyFragmentAdapter(context as FragmentActivity)

        // 탭 레이아웃 - 연결하기
        TabLayoutMediator(binding.tabs, binding.viewpager2){
                tab, position -> tab.text = "TAB ${position + 1}"
        }.attach()

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SevenFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SevenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}