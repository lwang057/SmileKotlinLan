package com.lwang.smilekotlinlan.ui.me

import androidx.lifecycle.MutableLiveData
import com.aleyn.mvvm.base.BaseViewModel
import com.pcl.mvvm.network.entity.UsedWeb
import com.pcl.mvvm.utils.Injection

/**
 * @Author lwang
 * @Date 2021/5/16 18:23
 * @Description
 */
class MeViewModel : BaseViewModel() {

    var popularWeb = MutableLiveData<MutableList<UsedWeb>>()

    private val homeRepository by lazy { Injection.getHomeRepository() }


    fun getPopularWeb() {
        launchGo({
            val result = homeRepository.getPopularWeb()
            if (result.isSuccess()) {
                popularWeb.value = result.data
            }
        })
    }
}