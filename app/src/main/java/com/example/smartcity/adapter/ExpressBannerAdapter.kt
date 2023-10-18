package com.example.smartcity.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartcity.Tool
import com.example.smartcity.bean.ExpressBannerBean
import com.youth.banner.adapter.BannerAdapter

//轮播图适配器 该适配器收受一个上下文一个图片数据 继承轮播适配器
class ExpressBannerAdapter(private val context: Context,val data:List<ExpressBannerBean.Data>) : BannerAdapter<ExpressBannerBean.Data,ExpressBannerAdapter.VH>(data) {
    class VH(item:View): RecyclerView.ViewHolder(item)

    override fun onCreateHolder(p0: ViewGroup?, p1: Int): VH {
        val view = ImageView(context)
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        return VH(view)
    }

    override fun onBindView(p0: VH?, p1: ExpressBannerBean.Data?, p2: Int, p3: Int) {
        if (p1 != null) {
            if (p0 != null) {
                Glide.with(context).load(Tool(context).getUrl(p1.imgUrl)).centerCrop().into(p0.itemView as ImageView)
            }
        }
    }
}