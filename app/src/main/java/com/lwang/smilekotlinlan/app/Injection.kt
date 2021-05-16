package com.pcl.mvvm.utils

import com.lwang.smilekotlinlan.data.HomeRepository
import com.lwang.smilekotlinlan.data.http.HomeNetWork
import com.lwang.smilekotlinlan.data.local.LocalDatabase


object Injection {

    //两条分支组成一个数据仓库（网络数据源，本地数据源）
    fun getHomeRepository() = HomeRepository.getInstance(
        HomeNetWork.getInstance(), LocalDatabase.getInstanse().homeLocaData()
    )

}