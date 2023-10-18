package com.example.smartcity.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.smartcity.R
import com.example.smartcity.bean.UserInfoBean
import com.example.smartcity.databinding.FragmentMeBinding
import com.example.smartcity.tool
import com.example.smartcity.ui.activity.*
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
import java.lang.Exception

class MeFragment : Fragment() {
    private val TAG = "MeFragment"
    lateinit var vb: FragmentMeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentMeBinding.inflate(layoutInflater)
        initView()
        return vb.root
    }

    private fun initView() {
        val close = {
            Toast.makeText(context, "未登录", Toast.LENGTH_SHORT).show()
        }
//        调用检查登录方法 点击功能模块
        tool.checkToken {
            vb.apply {
                if (it) {
//                发送用户信息检查有没有登录 auth为真表示要加token
                    tool.send("/prod-api/api/common/user/getInfo", "GET", null, true) {
                        val user = Gson().fromJson(it, UserInfoBean::class.java).user
                        try {
                            meName.text = user.nickName
                            Glide.with(tool.context).load(user.avatar).circleCrop().into(meAvatar)
                        } catch (e:Exception) {
                            meAvatar.setImageResource(R.drawable.ic_avatar)
                            Log.e(TAG, "${e.message}")
                        }
                        vb.meInfo.setOnClickListener {
                            context?.startActivity(Intent(context, UserInfoActivity::class.java))
                        }
                        vb.mePassword.setOnClickListener {
                            context?.startActivity(Intent(context, PasswordActivity::class.java))
                        }
                        vb.meOrder.setOnClickListener {
                            context?.startActivity(Intent(context, OrderActivity::class.java))
                        }
                        vb.meFeedback.setOnClickListener {
                            context?.startActivity(Intent(context, FeedbackActivity::class.java))
                        }
                        vb.meBtn.text = "退出登录"
                        vb.meBtn.setOnClickListener {
//                          删除token
                            tool.set("token","")
//                            再次调用方法执行未登录逻辑 改变按钮文字 让功能模块无法点击
                            initView()
                        }
                    }
                } else {
//                没有登录
                    meName.text = "未登录"
                    meAvatar.setImageResource(R.drawable.ic_avatar)
//                    遍历meList功能模块 添加点击事件
                    for (i in 0 until meList.childCount) {
                        val view = meList.getChildAt(i)
                        if (view is LinearLayout) {
//                            登录点击提示
                            view.setOnClickListener {
                                close()
                            }
                        } else {
//                            不是LinearLayout布局绑定点击跳转登录
                            (view as MaterialButton).text = "登录"
                            view.setOnClickListener {
                                context?.startActivity(Intent(context, LoginActivity::class.java))
                            }
                        }
                    }
                }
            }
        }
    }

    //    调用前台显示界面的时候显示
    override fun onResume() {
        initView()
        super.onResume()
    }
}