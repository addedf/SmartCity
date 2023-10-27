package com.example.smartcity

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.res.Resources
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.gson.Gson

//拓展属性给视图的Activity和Fragment添加 在使用tool的时候也会返回一个Tool和使用视图的上下文的回调
val Activity.tool: Tool get() = Tool(this)
val Dialog.tool: Tool get() = context?.let { Tool(it) }!!
//let断言!!强制解包确保返回的Tool不为空 // 断言类似于使用前的校验判断传入的内容是否为空 为空则不去执行 不为空才执行 这样传入的内容空指针也不会崩溃
val Fragment.tool: Tool get() = context?.let { Tool(it) }!!

//拓展函数给Activity添加了一个jump方法 泛型接收一个Activity参数 进行跳转
fun <A : Activity> Activity.jump(act: Class<A>) {
    startActivity(Intent(this, act))
}

fun <A : Activity> Fragment.jump(act: Class<A>) {
    startActivity(Intent(context, act))
}

//绑定viewBinding 调用方法传入viewBinding 快速绑定 inline内联函数
inline fun <reified T : ViewBinding> Activity.viewBinding(noinline inflater: (LayoutInflater) -> T) =
//        lazy延迟初始化
    lazy(LazyThreadSafetyMode.NONE) {
        inflater.invoke(layoutInflater)
    }

inline fun <reified T : ViewBinding> Fragment.viewBinding(noinline inflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        inflater.invoke(layoutInflater)
    }

//拓展属性用于获取Gson实例
val g:Gson get() = Gson()
//把px转换为dp单位
val Int.dp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()