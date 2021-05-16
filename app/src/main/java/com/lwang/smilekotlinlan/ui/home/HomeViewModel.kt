package com.lwang.smilekotlinlan.ui.home

import androidx.lifecycle.MutableLiveData
import com.aleyn.mvvm.base.BaseViewModel
import com.pcl.mvvm.network.entity.BannerBean
import com.pcl.mvvm.network.entity.HomeListBean
import com.pcl.mvvm.utils.Injection

/**
 * @Author lwang
 * @Date 2021/5/16 17:13
 * @Description
 */
class HomeViewModel : BaseViewModel() {


    private val homeRepository by lazy { Injection.getHomeRepository() }


    private val mBanners = MutableLiveData<List<BannerBean>>()


    private val projectData = MutableLiveData<HomeListBean>()


    /**
     * 获取banner数据
     */
    fun getBanner(refresh: Boolean = false): MutableLiveData<List<BannerBean>> {
        launchGo({
            mBanners.value = homeRepository.getBannerData(refresh)
        })
        return mBanners
    }


    /**
     * @param page 页码
     * @param refresh 是否刷新
     */
    fun getHomeList(page: Int, refresh: Boolean = false): MutableLiveData<HomeListBean> {
        launchGo({
            projectData.value = homeRepository.getHomeList(page, refresh)
        }, {
            projectData.value = null
        })
        return projectData
    }

}