package com.pcl.mvvm.ui.me

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.lwang.smilekotlinlan.R
import com.lwang.smilekotlinlan.databinding.ItemUsedwebBinding
import com.pcl.mvvm.network.entity.UsedWeb

/**
 * @Author lwang
 * @Date 2021/5/16 17:29
 * @Description
 */
class MeWebAdapter : BaseQuickAdapter<UsedWeb, BaseDataBindingHolder<ItemUsedwebBinding>>(R.layout.item_usedweb) {

    override fun convert(holder: BaseDataBindingHolder<ItemUsedwebBinding>, item: UsedWeb) {
        holder.dataBinding?.itemData = item
        holder.dataBinding?.executePendingBindings()
    }

}