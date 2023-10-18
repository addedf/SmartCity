package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityMainBinding
import com.example.smartcity.tool
import com.example.smartcity.ui.fragment.*
// TODO("影院买票评论订单功能没有要求以后再做")
class MainActivity : AppCompatActivity() {
//    lateinit 延迟赋值变量 var 变量 val 常量 ActivityMainBinding绑定视图写法
    lateinit var vb : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        挂载视图 绑定
        vb = ActivityMainBinding.inflate(layoutInflater)
//        tools:viewBindingIgnore="true" 想要忽略 某个视图不进行绑定
        setContentView(vb.root)   // root 指向的是对应的xml
//      Activity都有的私有属性包含了Tool文件的方法 apply构建一个tool对象 代码块的上下文默认是Tool 给服务器设置默认端口
        tool.apply {
//            判断是否为空 然后默认填入端口和ip
            if (get("server").isEmpty() || get("port").isEmpty()) {
                set("server","124.93.196.45")
                set("port","10001")
            }
        }
//        点击切换碎片
        vb.mainNav.setOnNavigationItemSelectedListener {
//            kotlin中的when类似于while 但是完全重写的这个方法有更多的拓展性 请查看官方文档
            val fragment = when (it.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_service -> ServiceFragment()
                R.id.nav_help -> HelpFragment()
                R.id.nav_new -> NewsFragment()
                R.id.nav_me -> MeFragment()
                else -> HomeFragment()
            }
//            提交加载
            supportFragmentManager.beginTransaction()
//               该方法需要两个参数 容器id和类型
                .replace(R.id.main_container,fragment)
//                提交
                .commit()
//            该方法是需要返回一个布尔值的
            true
        }
//        默认显示
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container,HomeFragment())
            .commit()
    }
}