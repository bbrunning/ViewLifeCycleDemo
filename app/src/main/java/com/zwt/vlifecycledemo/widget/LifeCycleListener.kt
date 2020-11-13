package com.zwt.vlifecycledemo.widget

interface LifeCycleListener {

    fun onResume()

    fun onPause()

    fun setUserVisibleHint(isVisibleToUser: Boolean)

    fun onHiddenChanged(hidden: Boolean)
}