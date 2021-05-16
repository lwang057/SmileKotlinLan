package com.lwang.smilekotlinlan.data.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.blankj.utilcode.util.Utils
import com.lwang.smilekotlinlan.data.local.dao.HomeDao
import com.pcl.mvvm.data.db.migration.MIGRATION
import com.pcl.mvvm.network.entity.BannerBean
import com.pcl.mvvm.network.entity.HomeListBean

/**
 * @Author lwang
 * @Date 2021/5/16 16:39
 * @Description
 */
@Database(entities = [HomeListBean::class, BannerBean::class], version = 2, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {


    abstract fun homeLocaData(): HomeDao


    companion object {
        fun getInstanse() =
            SingletonHolder.INSTANCE
    }


    private object SingletonHolder {
        val INSTANCE = Room.databaseBuilder(Utils.getApp(), LocalDatabase::class.java, "lin_db")
            .addMigrations(MIGRATION.MIGRATION_1_2)
            .build()
    }

}