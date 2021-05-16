package com.lwang.smilekotlinlan.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.aleyn.mvvm.base.BaseActivity
import com.aleyn.mvvm.base.NoViewModel
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.PermissionUtils
import com.lwang.smilekotlinlan.R
import com.lwang.smilekotlinlan.databinding.ActivityMainBinding
import com.lwang.smilekotlinlan.ui.home.HomeFragment
import com.lwang.smilekotlinlan.ui.me.MeFragment
import com.lwang.smilekotlinlan.ui.project.ProjectFragment
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener

class MainActivity : BaseActivity<NoViewModel, ActivityMainBinding>() {


    private val fragments = ArrayList<Fragment>()


    override fun initView(savedInstanceState: Bundle?) {

        BarUtils.setStatusBarColor(this, ColorUtils.getColor(R.color.colorPrimary))
        fragments.add(HomeFragment.newInstance())
        fragments.add(ProjectFragment.newInstance())
        fragments.add(MeFragment.newInstance())

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragments[0])
            .commitNow()

        val navCtl = mBinding.pageNavigationView.material()
            .addItem(R.drawable.tab_shop_selected, "首页")
            .addItem(R.drawable.tab_car_selected, "项目")
            .addItem(R.drawable.tab_me_selected, "我的")
            .build()

        navCtl.addTabItemSelectedListener(object : OnTabItemSelectedListener {
            override fun onSelected(index: Int, old: Int) {
                switchPage(index, old)
            }

            override fun onRepeat(index: Int) {

            }
        })

        PermissionUtils.permission(*PermissionUtils.getPermissions().toTypedArray())
            .callback(object : PermissionUtils.FullCallback {
                override fun onGranted(granted: MutableList<String>) {

                }

                override fun onDenied(
                    forever: MutableList<String>, denied: MutableList<String>
                ) {

                }

            })
            .request()
    }


    override fun initData() {

    }


    private fun switchPage(index: Int, old: Int) {

        val now = fragments[index]
        supportFragmentManager.beginTransaction().apply {
            if (!now.isAdded) {
                add(R.id.container, now)
            }

            show(now)
            hide(fragments[old])
            commit()
        }
    }

}