package com.example.smartcity.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.smartcity.R
import com.example.smartcity.dialog.CustomDialog
import com.example.smartcity.ui.activity.MainActivity

//kotlin中的参数接收直接方法的模式写在类前面 记得给上private
class IntroductionAdapter(private val icons: IntArray, val context: Context) : PagerAdapter() {

    override fun getCount(): Int {
        return icons.size
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        绑定子项布局
        val view =
            LayoutInflater.from(container.context).inflate(R.layout.guide_page, container, false)
        val nextButton: Button = view.findViewById(R.id.nextButton)
        val dialogShow: Button = view.findViewById(R.id.dialog_show)
        val guideImage: ImageView = view.findViewById(R.id.pageIcon)
        guideImage.setImageResource(icons[position])
        if (position == getCount() - 1) {
            // 如果是最后一页，显示按钮
            nextButton.visibility = View.VISIBLE
            dialogShow.visibility = View.VISIBLE
//            点击显示弹窗
            dialogShow.setOnClickListener {
                // 创建自定义Dialog实例
                val customDialog = CustomDialog(context)
                // 显示Dialog
                customDialog.show()
            }
            nextButton.setOnClickListener {
                // 在按钮点击时执行操作，例如跳转到主界面
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }
        } else {
            // 如果不是最后一页，隐藏按钮
            nextButton.visibility = View.GONE
        }
//        内容绑定到布局文件
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}