package com.lwang.smilekotlinlan.ui.me

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aleyn.mvvm.base.BaseFragment
import com.lwang.smilekotlinlan.R
import com.lwang.smilekotlinlan.databinding.FragmentMeBinding
import com.lwang.smilekotlinlan.ui.detail.DetailActivity
import com.pcl.mvvm.ui.me.MeWebAdapter

/**
 * @Author lwang
 * @Date 2021/5/16 18:26
 * @Description 我的fragment
 */
class MeFragment : BaseFragment<MeViewModel, FragmentMeBinding>() {

    private val mAdapter by lazy { MeWebAdapter() }


    companion object {
        fun newInstance() = MeFragment()
    }


    override fun layoutId() = R.layout.fragment_me


    override fun initView(savedInstanceState: Bundle?) {

        with(mBinding.rvMeUesdWeb) {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        viewModel.popularWeb.observe(viewLifecycleOwner, Observer {
            mAdapter.setNewInstance(it)
        })

        mAdapter.setOnItemClickListener { _, _, position ->
            val intent = Intent().apply {
                setClass(activity!!, DetailActivity::class.java)
                putExtra("url", (mAdapter.data[position]).link)
            }
            startActivity(intent)
        }
    }


    override fun lazyLoadData() {
        viewModel.getPopularWeb()
    }

}