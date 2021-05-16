package com.lwang.smilekotlinlan.data

import com.aleyn.mvvm.base.BaseModel
import com.lwang.smilekotlinlan.data.local.dao.HomeDao
import com.lwang.smilekotlinlan.data.http.HomeNetWork
import com.pcl.mvvm.app.base.BaseResult
import com.pcl.mvvm.network.entity.BannerBean
import com.pcl.mvvm.network.entity.HomeListBean
import com.pcl.mvvm.network.entity.NavTypeBean
import com.pcl.mvvm.network.entity.UsedWeb

/**
 * @Author lwang
 * @Date 2021/5/15 21:10
 * @Description  MVVM的Model层，统一模块的数据仓库，包含网络数据和本地数据（一个应用可以有多个Repository）
 */
class HomeRepository private constructor(private val netWork: HomeNetWork, private val localData: HomeDao) : BaseModel() {


    companion object {

        @Volatile
        private var INSTANCE: HomeRepository? = null

        fun getInstance(netWork: HomeNetWork, homeDao: HomeDao) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: HomeRepository(netWork, homeDao).also { INSTANCE = it }
        }
    }


    suspend fun getBannerData(refresh: Boolean = false): List<BannerBean> {
        return cacheNetCall(
            { netWork.getBannerData() },
            { localData.getBannerList() },
            {
                if (refresh) localData.deleteBannerAll()
                localData.insertBanner(it)
            },
            { !refresh && !it.isNullOrEmpty() },
        )
    }


    suspend fun getHomeList(page: Int, refresh: Boolean): HomeListBean {
        return cacheNetCall(
            { netWork.getHomeList(page) },
            { localData.getHomeList(page + 1) },
            {
                if (refresh) localData.deleteHomeAll()
                localData.insertData(it)
            },
            { !refresh }
        )
    }


    suspend fun getNaviJson(): BaseResult<List<NavTypeBean>> {
        return netWork.getNaviJson()
    }


    suspend fun getProjectList(page: Int, cid: Int): BaseResult<HomeListBean> {
        return netWork.getProjectList(page, cid)
    }


    suspend fun getPopularWeb(): BaseResult<MutableList<UsedWeb>> {
        return netWork.getPopularWeb()
    }
}