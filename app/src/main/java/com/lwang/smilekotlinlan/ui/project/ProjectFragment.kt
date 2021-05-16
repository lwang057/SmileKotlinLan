package com.lwang.smilekotlinlan.ui.project

import android.content.Intent
import android.os.Bundle
import com.aleyn.mvvm.base.BaseFragment
import com.aleyn.mvvm.event.Message
import com.lwang.smilekotlinlan.R
import com.lwang.smilekotlinlan.databinding.FragmentProjectBinding
import com.lwang.smilekotlinlan.ui.detail.DetailActivity
import com.pcl.mvvm.network.entity.ArticlesBean
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

/**
 * @Author lwang
 * @Date 2021/5/16 18:41
 * @Description 项目fragment
 */
class ProjectFragment : BaseFragment<ProjectViewModel, FragmentProjectBinding>() {


    companion object {
        fun newInstance() = ProjectFragment()
    }


    override fun layoutId() = R.layout.fragment_project


    override fun initView(savedInstanceState: Bundle?) {

        mBinding.viewModel = viewModel
    }


    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun lazyLoadData() {
        viewModel.getFirstData()
    }


    override fun handleEvent(msg: Message) {
        when (msg.code) {
            0 -> {
                val bean = msg.obj as ArticlesBean
                val intent = Intent().apply {
                    setClass(activity!!, DetailActivity::class.java)
                    putExtra("url", bean.link)
                }
                startActivity(intent)
            }
        }
    }

}