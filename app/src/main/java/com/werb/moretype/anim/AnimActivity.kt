package com.werb.moretype.anim

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.CompoundButton
import com.werb.library.MoreAdapter
import com.werb.library.extension.*
import com.werb.moretype.R
import com.werb.moretype.TitleViewType
import com.werb.moretype.data.DataServer
import kotlinx.android.synthetic.main.activity_anim.*


/**
 * Created by wanbo on 2017/7/15.
 */
class AnimActivity : AppCompatActivity() {

    private val adapter = MoreAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim)

        toolbar.setNavigationIcon(R.mipmap.ic_close_white_24dp)
        toolbar.setNavigationOnClickListener { finish() }

        anim_list.layoutManager = LinearLayoutManager(this)
        adapter.register(TitleViewType())
                .register(AnimViewType())
                .startAnimPosition(1)
                .attachTo(anim_list)

        switch_view.setOnCheckedChangeListener({ _: CompoundButton, check: Boolean ->
            adapter.firstShowAnim(check)
        })

        spinner.onItemSelectedListener = object : OnItemSelectedListener {

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                when (position){
                    0 -> {
                        adapter.removeAllData()
                        adapter.renderWithAnimation(AlphaAnimation())
                        adapter.loadData(DataServer.getAnimData())
                    }
                    1 -> {
                        adapter.removeAllData()
                        adapter.renderWithAnimation(ScaleInAnimation())
                        adapter.loadData(DataServer.getAnimData())
                    }
                    2 -> {
                        adapter.removeAllData()
                        adapter.renderWithAnimation(SlideInLeftAnimation())
                        adapter.loadData(DataServer.getAnimData())
                    }
                    3 -> {
                        adapter.removeAllData()
                        adapter.renderWithAnimation(SlideInRightAnimation())
                        adapter.loadData(DataServer.getAnimData())
                    }
                    4 -> {
                        adapter.removeAllData()
                        adapter.renderWithAnimation(SlideInBottomAnimation())
                        adapter.loadData(DataServer.getAnimData())
                    }
                }
            }

        }
    }

}