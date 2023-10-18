package com.example.smartcity

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.databinding.BannerViewBinding
import com.google.android.material.snackbar.Snackbar
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception

//工具类 接受一个上下文作为参数
class Tool(val context: Context) {
    //    伴生对象 类似于 java中的静态方法 但是更加灵活 其中创建的属性和方法与成员关联但是不与类关联 不是Tool的必须属性但确实方法需要的属性
//    伴生对象中的属性 不需要构建一个类的实例才能使用 可以通过属性名就能访问 同时还能够八初始化的属性放在其中
    companion object {
        const val TAG = "Tool"
        val handler = android.os.Handler()
        val client = OkHttpClient()
    }

    //    调用getSharedPreferences把ip端口号存储到本地
    val sp = context.getSharedPreferences("data", Context.MODE_PRIVATE)

    //    读取
    fun get(key: String): String {
        return sp.getString(key, "").toString()
    }

    //    写入
    fun set(key: String, value: String) {
        sp.edit().putString(key, value).apply()
    }

    //    获取完整请求方法
    fun getUrl(url: String): String {
        return "http://${get("server")}:${get("port")}$url"
    }

    //    封装网络请求方法
//    url 表示请求的URL
//    method 表示请求的方法（例如，"GET" 或 "POST"）
//    body 表示请求体 ?允许空指针
//    auth 表示是否需要授权（Authorization）
//    then 是一个回调函数，用于处理网络请求的响应数据
    fun send(
        url: String,
        method: String,
        body: RequestBody?,
        auth: Boolean,
        then: (String) -> Unit
    ) {
        try {
//            发送请求
            val req = Request.Builder()
            req.url(getUrl(url))
//            设置请求时间
//            放入请求类型和请求体
            req.method(method, body)
//            为真就给请求添加token
            if (auth) {
                req.addHeader("Authorization", "Bearer ${get("token")}")
            }

            client.newCall(req.build()).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e(TAG, "请求失败：${e.message}")
                }

                override fun onResponse(call: Call, response: Response) {
                    val str = response.body?.string()  // 解析请求体
                    handler.post {
                        if (str != null) {
//                         把解析完成的字符串放入回调中
                            then(str)
                        }
                    }
                }

            })
        } catch (e: Exception) {
            Log.e(TAG, "解析错误：${e.message}")
        }
    }

    //    检查是否登录  fn内部回调判断是否操作操作成功
    fun checkToken(fn: (Boolean) -> Unit) {
        send("/prod-api/api/common/user/getInfo", "GET", null, true) {
            try {
//                写入数据 JSONObject(it)返回的是用户的json数据 getInt转换用户id存储
                set("userId", JSONObject(it).getJSONObject("user").getInt("userId").toString())
            } catch (e: Exception) {
                Log.e(TAG, "未登录/写入失败")
                set("userId", "")
            }
            fn(it.contains("操作成功"))
        }
    }

    //    封装提示登录跳转按钮
    fun snackbar(view: View, msg: String, btn: String, action: () -> Unit) {
//    view 是用于显示 Snackbar 的视图
//    msg 是要显示的消息
//    btn 是按钮的文本
//    action 是一个无参数无返回值的函数，用于定义按钮点击后的操作
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction(btn) { action() }.show()
    }

//    提示未登录体跳转登录界面
//    fun loginTop(view: View) {
//        snackbar(view,"未登录","去登陆") {
//            context.startActivity(Intent(context,LoginActivity::class.java))
//        }
//    }

    //    封装轮播图 本方法是使用 RecyclerView模拟ViewPager做出轮播图效果因此无法做出指示点效果
    fun setBanner(view: ViewPager2, list: List<String>) {
        view.adapter = GenericAdapter(
            Int.MAX_VALUE,
//            lambda表达式 binding视图对象 position第几张图片 轮播图绑定到图片组件上
            { BannerViewBinding.inflate(LayoutInflater.from(context)) }) { binding, position ->
//            布局参数 占满空间
            binding.root.layoutParams = ViewGroup.LayoutParams(
//                宽高全部填充满
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
//            记载列表中的第几张图片
            Glide.with(context).load(getUrl(list[position % list.size])).centerCrop()
                .into(binding.root)
        }
//        自动切换
        view.postDelayed(object : Runnable {
            override fun run() {
                view.currentItem += 1
                view.postDelayed(this, 3000)
            }
        }, 3000)
    }
}
//lambda表达式事例
//fun operation(a: Int, b: Int, op: (Int, Int) -> Int): Int {
//    return op(a, b)  // 8
//}
//
//val result = operation(5, 3) { a, b -> a + b }
//
//val numbers = listOf(1, 2, 3, 4, 5)
//numbers.forEach { println(it) } // 1，2，3，4，5
//
//val numbers = listOf(1, 2, 3, 4, 5)
//val evenNumbers = numbers.filter { it % 2 == 0 }  // 2











































