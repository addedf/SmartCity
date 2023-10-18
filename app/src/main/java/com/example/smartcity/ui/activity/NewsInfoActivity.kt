package com.example.smartcity.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.webkit.WebSettings
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.NewsCommentBean
import com.example.smartcity.bean.NewsInfoBean
import com.example.smartcity.databinding.ActivityNewsInfoBinding
import com.example.smartcity.databinding.ItemNewsCommentBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import kotlin.properties.Delegates

class NewsInfoActivity : AppCompatActivity() {
    lateinit var vb: ActivityNewsInfoBinding
    val TAG = "NewsInfoActivity"
    //    委托延迟赋值 intent访问到传递来的值再赋值
    private val newsId: Int by lazy {
        intent.getIntExtra("id", 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityNewsInfoBinding.inflate(layoutInflater)
        setContentView(vb.root)
        //        接收传递来的id 在onCreate完成接收赋值在传递给newsId完成延迟赋值
        val id = intent.getIntExtra("id", 0)
        vb.newsInfoTb.setOnClickListener {
            finish()
        }
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        点击弹出软键盘
        vb.newsInfoInput.setOnClickListener {
            imm.showSoftInput(vb.newsInfoInput, InputMethodManager.SHOW_IMPLICIT)
        }
//                验证是否登录
        tool.checkToken {
            if (it) {
//                收起软键盘放在这里是因为 需要一个事件触发
                imm.hideSoftInputFromWindow(vb.newsInfoInput.windowToken, 0)
//                发布评论方法
                submitComment()
//                加载评论
                loadComment()
//                加载新闻
                loadNews()
            } else {
                tool.snackbar(vb.root, "未登录", "去登陆") {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }
    }


    private fun loadNews() {
        tool.apply {
            send("/prod-api/api/park/press/press/$newsId", "GET", null, false) {
                val data = g.fromJson(it, NewsInfoBean::class.java).data
//                设置混合内容加载
//                vb.newsInfoView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
//                给图片设置宽度 同时使用replace检查图片给加上ip地址加载图片
//                null：表示基本 URL，通常是用于指定相对链接的基本路径。在这里，它设置为
//                "html/text"：这是要加载的内容的类型，指定为 HTML 文本。
//                "utf-8"：指定了字符编码，通常为 UTF-8。
//                null：这是历史数据参数，通常可以设置为 null。
                vb.newsInfoView.loadDataWithBaseURL(
                    null, "<style>img{width:100vw}</style>${
                        data.content.replace(
                            "src=\"",
                            "src=\"http://${get("server")}:${get("port")}"
                        )
                    }", "html/text", "utf-8", null
                )
//                vb.newsInfoText.text = Html.fromHtml(data.content)
                vb.newsInfoCommentNum.text = "评论人数:${data.commentNum.toString()}"
                vb.newsInfoCreateTime.text = "发布:${data.createTime}"
                vb.newsInfoUpdateTime.text = "修改:${data.updateTime}"
                vb.newsInfoLikeNum.text = "点赞:${data.likeNum}"
                vb.newsInfoReadNum.text = "阅读数:${data.readNum}"
                vb.newsInfoTitle.text = data.title
            }
        }
    }

    private fun loadComment() {
        tool.apply {
            //            加载评论
            send("/prod-api/api/park/press/comments/list", "GET", null, false) { it ->
//                根据时间排个序  sortedByDescending
                val data =
                    g.fromJson(
                        it,
                        NewsCommentBean::class.java
                    ).rows.sortedByDescending { it.commentDate }
//                根据传递的新闻id过滤新闻列表中属于这个新闻的评论
                val filterCommentData = data.filter { it.newsId == newsId }
                val adapter = GenericAdapter(filterCommentData.size,
                    { ItemNewsCommentBinding.inflate(layoutInflater) }) { binding, position ->
                    binding.itemNewsCommentCommentDate.text =
                        filterCommentData[position].commentDate
                    binding.itemNewsCommentContent.text = filterCommentData[position].content
                    binding.itemNewsCommentUserName.text = filterCommentData[position].userName
                }
                vb.newsInfoCommentNumList.adapter = adapter
                vb.newsInfoCommentNumList.layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun submitComment() {
//        两个参数 新闻id和 评论内容
        vb.newsInfoBtn.setOnClickListener {
            val data = """
                {
                "newsId":"$newsId",
                "content":"${vb.newsInfoInput.text}"
                }
            """.trimIndent()
            val req = data.toRequestBody("application/json".toMediaTypeOrNull())
            tool.apply {
                send("/prod-api/api/park/press/pressComment", "POST", req, true) {
                    if (it.contains("操作成功")) {
//                        提交成功在加载一次评论
                        loadComment()
//                       清空文本值
                        vb.newsInfoInput.clearFocus()
                        vb.newsInfoInput.setText("")
                        Toast.makeText(context, "评论成功", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, JSONObject(it).getString("msg"), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}