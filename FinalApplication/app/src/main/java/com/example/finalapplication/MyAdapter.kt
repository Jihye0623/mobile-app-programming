package com.example.finalapplication

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.Dimension
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalapplication.databinding.ItemRecyclerviewBinding

// oneFragment - 투두 리스트
class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val datas: MutableList<String>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    lateinit var context: Context

    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder{
        context = parent.context
        return MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // 배열(투두들)에 저장된 내용 중 해당되는 string의 내용을 반환
        val binding=(holder as MyViewHolder).binding
        binding.itemData.text= datas!![position]

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val txColor:String? = sharedPreferences.getString("tx_color", "#000000")
        binding.itemData.setTextColor(Color.parseColor(txColor))

    }
}
