package com.example.mid20210694

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.mid20210694.databinding.FragmentOneBinding

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
        var binding = FragmentOneBinding.inflate(inflater, container, false)

        val items = arrayOf<String>("Room1","Room2","Room3")
        var myRoom = ""
        var nameStr=""
        var dateStr=""
        var timeStr=""
        var roomStr=""

        binding.dateBtn.setOnClickListener{
            DatePickerDialog(requireContext(), object: DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year:Int, month:Int, dayOfMonth:Int){
                    binding.dateBtn.text = "${year}년 ${month+1}월 ${dayOfMonth}일"
                    dateStr="${year}년 ${month+1}월 ${dayOfMonth}일"
                }
            }, 2023,4,1).show()     // month는 0부터 시작

        }

        binding.timeBtn.setOnClickListener{
            TimePickerDialog(requireContext(), object: TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay:Int, minute:Int){
                    binding.timeBtn.text ="${hourOfDay}시 ${minute}분"
                    timeStr="${hourOfDay}시 ${minute}분"
                }
            }, 15,0, false).show()

        }

        val alertHandler = object:DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                when(which){
                    DialogInterface.BUTTON_POSITIVE -> {
                        roomStr=myRoom
                        showToast("$myRoom 이 최종 선택되었습니다.")
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        showToast("예약룸 선택이 취소되었습니다")
                    }
                }
            }
        }
        binding.singleBtn.setOnClickListener{
            AlertDialog.Builder(requireContext()).run{
                setTitle("예약룸 선택하기")
                setSingleChoiceItems(items, 0, object: DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        myRoom = items[which]
                    }
                })
                setPositiveButton("OK", alertHandler)
                setNegativeButton("CANCEL", alertHandler)
                show()
            }
        }

        binding.finishBtn.setOnClickListener{
            nameStr=binding.nameEdit.text.toString()
            binding.resultText.text="예약자는  $nameStr  날짜는 $dateStr 시간은 $timeStr  룸은 $roomStr"
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