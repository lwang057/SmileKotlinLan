package com.lwang.smilekotlinlan.data.local.dao

import androidx.room.*
import com.pcl.mvvm.network.entity.BannerBean
import com.pcl.mvvm.network.entity.HomeListBean

/**
 * @Author lwang
 * @Date 2021/5/15 21:58
 * @Description
 */
@Dao
interface HomeDao {

    @Query("SELECT * FROM HOME_DATA WHERE curPage = :page")
    suspend fun getHomeList(page: Int): HomeListBean?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(homeListBean: HomeListBean)


    @Query("DELETE FROM HOME_DATA")
    suspend fun deleteHomeAll()


    @Update
    suspend fun updataData(homeListBean: HomeListBean)


    @Delete
    suspend fun deleteData(vararg data: HomeListBean)

    //Banner
    @Query("SELECT * FROM BANNER")
    suspend fun getBannerList(): List<BannerBean>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanner(banners: List<BannerBean>)


    @Query("DELETE FROM BANNER")
    suspend fun deleteBannerAll()
}