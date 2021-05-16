package com.lwang.smilekotlinlan.app

import com.aleyn.mvvm.BuildConfig
import com.aleyn.mvvm.base.BaseApplication
import com.blankj.utilcode.util.LogUtils

/**
 * @Author lwang
 * @Date 2021/5/15 20:56
 * @Description
 */
class MyApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()


        LogUtils.getConfig().run {
            isLogSwitch = BuildConfig.DEBUG
            setSingleTagSwitch(true)
        }
    }


}