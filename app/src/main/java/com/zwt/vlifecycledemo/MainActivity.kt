package com.zwt.vlifecycledemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import com.zwt.vlifecycledemo.widget.LifeCycleView

class MainActivity : BaseActivity() {

    private lateinit var mBanner: LifeCycleView
    private lateinit var mBtn: Button

    override fun initActivity(savedInstanceState: Bundle?) {
        mBanner = findViewById(R.id.life_cycle_view)
        mBtn = findViewById(R.id.btn_view)
        mKey?.let { mBanner.setLifeCycleKye(it) }
        mBtn.setOnClickListener {
            val intent: Intent = Intent()
            intent.setClass(this, TestActivity::class.java)
            this.startActivity(intent)
        }
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun getLifeCycleKey(): String? {
        return MainActivity::class.simpleName
    }
}