package com.zwt.vlifecycledemo.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.listener.OnPageChangeListener
import com.zwt.vlifecycledemo.R
import com.zwt.vlifecycledemo.data.BannerBean
import java.util.jar.Attributes

class LifeCycleView : LinearLayout, LifeCycleListener {

    private var mContext: Context? = null;
    private var mLifeCycleKey: String? = null;
    private lateinit var mBanner: Banner<BannerBean, BannerImageAdapter<BannerBean>>

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView(context);
    }

    private fun initView(context: Context) {
        mContext = getContext()
        var view = LayoutInflater.from(mContext).inflate(R.layout.life_cycle_view, this, true)
        if (view != null) {
            mBanner = view.findViewById(R.id.banner);
            mBanner.adapter = object : BannerImageAdapter<BannerBean>(BannerBean.bannerList) {
                override fun onBindView(
                    holder: BannerImageHolder?,
                    data: BannerBean?,
                    position: Int,
                    size: Int
                ) {
                    if (data != null) {
                        if (holder != null) {
                            Glide.with(holder.itemView)
                                .load(data.imageUrl)
                                .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
                                .into(holder.imageView)
                        }
                    }
                }
            }
            mBanner.addOnPageChangeListener(object : OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {

                }

                override fun onPageSelected(position: Int) {
                    Log.e("zhang", "banner onPageSelected index [" + position + "]")
                }

                override fun onPageScrollStateChanged(state: Int) {

                }
            })
        }
    }

    fun setLifeCycleKye(key: String) {
        mLifeCycleKey = key;
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        mLifeCycleKey?.let { DyLifeCycleUtil.addLifeCycleListener(it, this) }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        mLifeCycleKey?.let { DyLifeCycleUtil.removeLifeCycleListener(it) }
    }

    override fun onResume() {
        Log.e("zhang", "View onResume")
        if (!mBanner.isActivated) {
            mBanner.start()
        }
    }

    override fun onPause() {
        Log.e("zhang", "View onPause")
        mBanner.stop()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        TODO("Fragment use")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        TODO("Fragment use")
    }
}
