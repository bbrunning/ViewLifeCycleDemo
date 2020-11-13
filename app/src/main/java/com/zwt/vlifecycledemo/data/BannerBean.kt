package com.zwt.vlifecycledemo.data

class BannerBean {
    var imageRes: Int? = null
    var imageUrl: String? = null
    var title: String? = null
    var viewTpe: Int = 0

    constructor(imageRes: Int?, title: String?, viewTpe: Int) {
        this.imageRes = imageRes
        this.title = title
        this.viewTpe = viewTpe
    }

    constructor(imageUrl: String?, title: String?, viewTpe: Int) {
        this.imageUrl = imageUrl
        this.title = title
        this.viewTpe = viewTpe
    }

    companion object{
        val bannerList: List<BannerBean>
            get() {
                val list: MutableList<BannerBean> = ArrayList()
                 list.add(
                     BannerBean( "https://img.zcool.cn/community/013de756fb63036ac7257948747896.jpg",
                         null,
                         1
                     )
                 )
                list.add(
                    BannerBean( "https://img.zcool.cn/community/01639a56fb62ff6ac725794891960d.jpg",
                        null,
                        1
                    )
                )
                return  list
            }
    }

}