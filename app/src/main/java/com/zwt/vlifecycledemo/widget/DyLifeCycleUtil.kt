package com.zwt.vlifecycledemo.widget

import android.text.TextUtils
import java.util.*

/**
 * Created by Android Studio.
 * User: ZWT
 * Date: 2020/6/17
 * Time: 17:48
 */
object DyLifeCycleUtil {
    const val ON_RESUME = 1
    const val ON_PAUSE = 2
    const val USER_VISIBLE_HINT = 3
    const val ON_HIDDEN_CHANGED = 4

    private val mLifeCycleListenerMap: MutableMap<String, LifeCycleListener>? =
        HashMap<String, LifeCycleListener>()

    fun addLifeCycleListener(key: String, lifeCycleListeners: LifeCycleListener) {
        if (mLifeCycleListenerMap != null && !TextUtils.isEmpty(key)) {
            mLifeCycleListenerMap[key] = lifeCycleListeners
        }
    }

    fun removeLifeCycleListener(key: String) {
        try {
            if (!TextUtils.isEmpty(key) && mLifeCycleListenerMap != null && mLifeCycleListenerMap.isNotEmpty()) {
                mLifeCycleListenerMap.remove(key)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    @JvmOverloads
    fun postLifeCycle(key: String, type: Int, bool: Boolean = false) {
        try {
            if (mLifeCycleListenerMap != null) {
                val listener: LifeCycleListener? = mLifeCycleListenerMap[key]
                if (listener != null) {
                    when (type) {
                        ON_RESUME -> listener.onResume()
                        ON_PAUSE -> listener.onPause()
                        USER_VISIBLE_HINT -> listener.setUserVisibleHint(bool)
                        ON_HIDDEN_CHANGED -> listener.onHiddenChanged(bool)
                    }
                }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}