package com.lwang.smilekotlinlan.ui.project

import android.view.View
import android.widget.AdapterView
import androidx.databinding.ObservableArrayList
import com.aleyn.mvvm.app.MVVMLin
import com.aleyn.mvvm.base.BaseViewModel
import com.aleyn.mvvm.event.Message
import com.aleyn.mvvm.network.ResponseThrowable
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.google.android.material.tabs.TabLayout
import com.lwang.smilekotlinlan.R
import com.lwang.smilekotlinlan.BR
import com.pcl.mvvm.network.entity.ArticlesBean
import com.pcl.mvvm.network.entity.NavTypeBean
import com.pcl.mvvm.utils.Injection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * @Author lwang
 * @Date 2021/5/16 18:41
 * @Description
 */
class ProjectViewModel : BaseViewModel() {


    private val homeRepository by lazy { Injection.getHomeRepository() }

    private val itemOnClickListener = object : OnItemClickListener {
        override fun onItemClick(item: ArticlesBean) {
            defUI.msgEvent.postValue(Message(0, obj = item))
        }
    }

    private var page: Int = 0
    var navTitle = ObservableArrayList<String>()
    var navData = ObservableArrayList<NavTypeBean>()
    var items = ObservableArrayList<ArticlesBean>()

    var itemBinding = ItemBinding.of<ArticlesBean>(BR.itemBean, R.layout.item_project_list)
        .bindExtra(BR.listenner, itemOnClickListener)

    var tabOnClickListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabReselected(tab: TabLayout.Tab?) {

        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {

        }

        override fun onTabSelected(tab: TabLayout.Tab?) {
            tab?.let {
                getProjectList(navData[it.position].id)
            }
        }
    }


    /**
     * ?????????????????????????????????????????????????????????????????????????????? ?????????????????????
     *  ???????????????????????? ???  zip ????????? ???????????????????????????????????? flatMapMerge???flatMapConcat ??????
     *  ?????? RxJava ????????????????????????????????????  ????????????  `-` ???
     */
    @ExperimentalCoroutinesApi
    @FlowPreview
    fun getFirstData() {
        launchUI {
            launchFlow { homeRepository.getNaviJson() }
                .flatMapConcat {
                    return@flatMapConcat if (it.isSuccess()) {
                        navData.addAll(it.data)
                        it.data.forEach { item -> navTitle.add(item.name) }
                        launchFlow { homeRepository.getProjectList(page, it.data[0].id) }
                    } else throw ResponseThrowable(it.errorCode, it.errorMsg)
                }
                .onStart { defUI.showDialog.postValue(null) }
                .flowOn(Dispatchers.IO)
                .onCompletion { defUI.dismissDialog.call() }
                .catch { // ????????????
                    val err = MVVMLin.getConfig().globalExceptionHandle(it)
                    LogUtils.d("${err.code}: ${err.errMsg}")
                }
                .collect {
                    if (it.isSuccess()) items.addAll(it.data.datas)
                }
        }
    }


    fun getProjectList(cid: Int) {
        launchOnlyresult(
            { homeRepository.getProjectList(page, cid) },
            {
                items.clear()
                items.addAll(it.datas)
            })
    }


    interface OnItemClickListener {
        fun onItemClick(item: ArticlesBean)
    }

}