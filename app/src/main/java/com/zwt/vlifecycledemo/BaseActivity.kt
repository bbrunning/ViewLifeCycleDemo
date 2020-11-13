package com.zwt.vlifecycledemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.zwt.vlifecycledemo.widget.DyLifeCycleUtil

public abstract class BaseActivity : AppCompatActivity() {

    var mKey: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val defLayoutId = R.layout.activity_base
        var layoutId = if (getContentViewId() > 0) getContentViewId() else defLayoutId
        setContentView(layoutId)
        mKey = getLifeCycleKey()
        initActivity(savedInstanceState)

    }

    abstract fun initActivity(savedInstanceState: Bundle?)

    abstract fun getContentViewId(): Int

    abstract fun getLifeCycleKey(): String?

    override fun onResume() {
        super.onResume()
        mKey?.let { DyLifeCycleUtil.postLifeCycle(it, DyLifeCycleUtil.ON_RESUME) }
    }

    override fun onPause() {
        super.onPause()
        mKey?.let { DyLifeCycleUtil.postLifeCycle(it, DyLifeCycleUtil.ON_PAUSE) }
    }

    override fun onDestroy() {
        super.onDestroy()
        mKey?.let { DyLifeCycleUtil.removeLifeCycleListener(it) }
    }
}