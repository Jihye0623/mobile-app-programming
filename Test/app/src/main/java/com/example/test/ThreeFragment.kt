package com.example.test

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.test.databinding.ActivityMainBinding
import com.example.test.databinding.FragmentThreeBinding
import com.example.test.databinding.FragmentTwoBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThreeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThreeFragment : Fragment() {
    lateinit var binding: FragmentThreeBinding

    @RequiresApi(Build.VERSION_CODES.R)
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
        binding = FragmentThreeBinding.inflate(inflater, container, false)
        var backBool = false
        var emailBool = false
        var phoneBool = false

        lateinit var emailStr:String
        lateinit var phoneStr:String
        lateinit var BackColor:Drawable
        lateinit var backStr:String
        var toastStr:String =""

        binding.radioGroup.setOnCheckedChangeListener{ group, checkedId ->
            when(checkedId){
                R.id.redBtn -> {
                    BackColor = binding.redBtn.background
                    backStr="빨강"
                }

                R.id.greenBtn -> {
                    BackColor = binding.greenBtn.background
                    backStr="초록"
                }
                R.id.blueBtn -> {
                    BackColor = binding.blueBtn.background
                    backStr="파랑"
                }

                R.id.whiteBtn -> {
                    BackColor = binding.whiteBtn.background
                    backStr="흰색"
                }
            }
        }

        val listener = CompoundButton.OnCheckedChangeListener{ buttonView, isChecked ->
            if(isChecked){
                when(buttonView.id){
                    R.id.backgroundCBox -> {
                        backBool = true
                    }
                    R.id.emailCBox -> {
                        emailStr = binding.emailEditText.text.toString()
                        emailBool=true
                    }
                    R.id.phoneCBox -> {
                        phoneStr = binding.phoneEditText.text.toString()
                        phoneBool = true
                    }

                }
            }

        }


        binding.backgroundCBox.setOnCheckedChangeListener(listener)
        binding.emailCBox.setOnCheckedChangeListener(listener)
        binding.phoneCBox.setOnCheckedChangeListener(listener)

        val alertHandler = object: DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when(which){
                    DialogInterface.BUTTON_POSITIVE -> {
                        when{
                            backBool==true -> {
                                binding.threeRoot.background = BackColor
                                toastStr=toastStr+"배경 변경"+ backStr
                            }
                            emailBool==true -> toastStr=toastStr+"이메일 변경"+emailStr
                            phoneBool==true -> toastStr=toastStr+"전화번호 변경"+phoneStr
                            else -> toastStr="변경사항 없음"
                        }
                        showToast(toastStr)
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        showToast("설정이 취소되었습니다.")
                    }
                }

                toastStr =""
                backBool = false
                emailBool = false
                phoneBool = false
            }
        }

        binding.exitBtn.setOnClickListener{
            AlertDialog.Builder(context).run{
                setTitle("알림")
                setMessage("정말 종료하시겠습니까?")
                setPositiveButton("YES", alertHandler) // 처리할 내용이 없다면 두번째는 null 대입
                setNegativeButton("NO",alertHandler)
                // 버튼은 최대 3개까지
                show()
            }
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
         * @return A new instance of fragment ThreeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ThreeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun showToast(msg:String){
        val t = Toast.makeText(context, msg, Toast.LENGTH_LONG)
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