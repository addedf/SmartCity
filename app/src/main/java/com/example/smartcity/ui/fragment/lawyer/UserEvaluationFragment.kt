package com.example.smartcity.ui.fragment.lawyer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.LawyerCommentBean
import com.example.smartcity.databinding.FragmentUserEvaluationBinding
import com.example.smartcity.databinding.ItemLawyerCommentBinding
import java.lang.Exception

class UserEvaluationFragment : Fragment() {
    val TAG = "UserEvaluationFragment"
    companion object {
        fun newInstance(id: Int): UserEvaluationFragment {
            val fragment = UserEvaluationFragment()
            val bundle = Bundle()
            bundle.putInt("id", id)
            fragment.arguments = bundle
            return fragment
        }
    }
    private val vb by viewBinding(FragmentUserEvaluationBinding::inflate)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val id = arguments?.getInt("id", 0)
        loadComment(id)
        return vb.root
    }

    private fun loadComment(id: Int?) {
        tool.apply {
            send("/prod-api/api/lawyer-consultation/lawyer/list-evaluate?lawyerId=${id}","GET",null,true) {
                val data = g.fromJson(it,LawyerCommentBean::class.java).rows
                vb.lwyUserList.adapter = GenericAdapter(data.size,
                    { ItemLawyerCommentBinding.inflate(layoutInflater) }) { binding,position ->
                    binding.itemLawCommFromUserName.text = data[position].fromUserName
                    binding.itemLawCreateTime.text = data[position].createTime
                    binding.itemLawEvaluateContent.text = data[position].evaluateContent
                    try {
                        Glide.with(context).load(getUrl(data[position].fromUserAvatar))
                            .error(getDrawable(context,R.drawable.chengshi))
                            .transform(CenterCrop(), RoundedCorners(5.dp))
                            .into(binding.itemLawCommFromUserAvatar)
                    } catch (e:Exception) {
                        Log.e(TAG, "${e.message}")
                    }
                }
                vb.lwyUserList.layoutManager = LinearLayoutManager(context)
            }
        }
    }

}