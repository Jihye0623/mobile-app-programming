package com.example.finalapplication

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finalapplication.databinding.FragmentLineBinding
import com.example.finalapplication.databinding.FragmentRadarBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RadarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RadarFragment : Fragment() {
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
        val binding = FragmentRadarBinding.inflate(layoutInflater)

        var radar_values : ArrayList<RadarEntry> = ArrayList()
        radar_values.add(RadarEntry(12.0F))
        radar_values.add(RadarEntry(13.0F))
        radar_values.add(RadarEntry(10.0F))
        radar_values.add(RadarEntry(10.0F))
        radar_values.add(RadarEntry(7.0F))
        radar_values.add(RadarEntry(1.0F))
        radar_values.add(RadarEntry(5.0F))

        var radar_dataset = RadarDataSet(radar_values, "DataSet2")

        radar_dataset.color = Color.MAGENTA
        val label = arrayOf("지체장애", "청각장애", "뇌병변", "시각장애", "지적", "자폐성", "정신")
        val xAxis: XAxis = binding.radarChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(label)

        var radar_data = RadarData(radar_dataset)
        binding.radarChart.data = radar_data

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RadarFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RadarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}