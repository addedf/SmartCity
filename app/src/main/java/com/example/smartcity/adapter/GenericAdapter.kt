package com.example.smartcity.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

//泛型绑定视图ViewBinding 让适配器接收参数封装成方法
class GenericAdapter<T : ViewBinding>(
    private val size: Int,
    private val create: () -> T,
    private val bind: (T, Int) -> Unit
) : RecyclerView.Adapter<GenericAdapter.VH>() {


    //    接收绑定视图
    class VH(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    //    绑定item子项试图
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = create()
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
//      bind绑定列表视图  as T泛型适配提前设定holder.binding是ViewBinding视图类型
        bind(holder.binding as T, position)
    }

    override fun getItemCount(): Int = size

}