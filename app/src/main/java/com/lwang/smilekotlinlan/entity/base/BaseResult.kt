package com.pcl.mvvm.app.base

import com.aleyn.mvvm.base.IBaseResponse


data class BaseResult<T>(val errorMsg: String, val errorCode: Int, val data: T) : IBaseResponse<T> {

    override fun code() = errorCode

    override fun msg() = errorMsg

    override fun data() = data

    override fun isSuccess() = errorCode == 0

}