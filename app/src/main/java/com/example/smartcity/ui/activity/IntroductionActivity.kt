package com.example.smartcity.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.smartcity.R
import com.example.smartcity.adapter.IntroductionAdapter
import com.example.smartcity.databinding.ActivityIntroductionBinding
import com.example.smartcity.dialog.CustomDialog

class IntroductionActivity : AppCompatActivity() {
    lateinit var vb: ActivityIntroductionBinding

    //    把引导页的三张图片放入集合中传入适配器使用
    private val iconArray = intArrayOf(R.drawable.page_d1, R.drawable.page_d2, R.drawable.page_d3)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityIntroductionBinding.inflate(layoutInflater)
//        引导页指示点方法
        itemDian(0)
        // 检查本地存储以确定是否是第一次启动应用
        val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true)

        if (isFirstLaunch) {
            // 第一次启动应用，显示滑动介绍
            setContentView(vb.root)
//            适配传入上下文和图片
            val adapter = IntroductionAdapter(iconArray, this)
            vb.Introduction.adapter = adapter
            // 标记用户已经看过介绍
            sharedPreferences.edit().putBoolean("isFirstLaunch", false).apply()
        } else {
            // 不是第一次启动应用，跳转到主活动或其他适当的界面
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
//        监视引导页滑动
        vb.Introduction.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            //            滑动时触发
            override fun onPageSelected(position: Int) {
                itemDian(position)
//                setBackgroundResource此方法有权重问题不会和js一样覆盖src资源 一旦组件中写了src就会已xml中的为准
//                when (position) {
//                    0 -> {
//                        vb.dot1.setBackgroundResource(R.drawable.splash_dot_active)
//                        vb.dot2.setBackgroundResource(R.drawable.splash_dot)
//                        vb.dot3.setBackgroundResource(R.drawable.splash_dot)
//                    }
//                    1 -> {
//                        vb.dot1.setBackgroundResource(R.drawable.splash_dot)
//                        vb.dot2.setBackgroundResource(R.drawable.splash_dot_active)
//                        vb.dot3.setBackgroundResource(R.drawable.splash_dot)
//                    }
//                    2 -> {
//                        vb.dot1.setBackgroundResource(R.drawable.splash_dot)
//                        vb.dot2.setBackgroundResource(R.drawable.splash_dot)
//                        vb.dot3.setBackgroundResource(R.drawable.splash_dot_active)
//                    }
//                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    //    因为组件中的src一开始没有资源所以要在开始前触发一次
    fun itemDian(position: Int) {
        val dots = arrayOf(vb.dot1, vb.dot2, vb.dot3)
//        派他思想 遍历组件id和position相等的就给定选中的资源文件
        for (i in dots.indices) {
            dots[i].setBackgroundResource(
                if (i == position) R.drawable.splash_dot_active
                else R.drawable.splash_dot
            )
        }
    }
}