package com.lwang.smilekotlinlan.data.http

import com.lwang.smilekotlinlan.utils.RetrofitClient

/**
 * @Author lwang
 * @Date 2021/5/15 21:12
 * @Description
 */
class HomeNetWork {


    // lazy 应用于单例模式(if-null-then-init-else-return)，而且当且仅当变量被第一次调用的时候，委托方法才会执行。
    private val apiService by lazy {
        RetrofitClient.getInstance().create(HomeApiService::class.java)
    }


    companion object {

        @Volatile
        private var netWork: HomeNetWork? = null

        fun getInstance() = netWork ?: synchronized(this) {
            netWork ?: HomeNetWork().also { netWork = it }
        }
    }


    // 「代码执行到 suspend 函数的时候会『挂起』，并且这个『挂起』是非阻塞式的，它不会阻塞你当前的线程。」
    // suspend关键字作用是:[提醒],,,函数的创建者对函数的调用者的提醒
    suspend fun getBannerData() = apiService.getBanner()


    suspend fun getHomeList(page: Int) = apiService.getHomeList(page)


    suspend fun getNaviJson() = apiService.naviJson()


    suspend fun getProjectList(page: Int, cid: Int) = apiService.getProjectList(page, cid)


    suspend fun getPopularWeb() = apiService.getPopularWeb()


}