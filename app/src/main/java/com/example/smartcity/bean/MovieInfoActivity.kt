package com.example.smartcity.bean

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.*
import com.example.smartcity.databinding.ActivityMovieInfoBinding
import java.lang.Exception

class MovieInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityMovieInfoBinding::inflate)
    val TAG = "MovieInfoActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        val id = intent.getIntExtra("id", 0)
        vb.miTb.setOnClickListener {
            finish()
        }
        tool.apply {
            send("/prod-api/api/movie/film/detail/${id}", "GET", null, false) {
                val data = g.fromJson(it, MovieInfoBean::class.java).data
                try {
                    Glide.with(context).load(getUrl(data.cover))
                        .error(getDrawable(R.drawable.chengshi))
                        .transform(CenterCrop(), RoundedCorners(5.dp))
                        .into(vb.miCover)
                } catch (e: Exception) {
                    Log.e(TAG, "${e.message}")
                }
                vb.miLike.text = data.likeNum.toString()
                vb.miName.text = data.name
                vb.miScore.text = data.score.toString()
                vb.miTime.text = data.playDate
                vb.miBtn.setOnClickListener {
                    finish()
                }
            }
        }
    }
}