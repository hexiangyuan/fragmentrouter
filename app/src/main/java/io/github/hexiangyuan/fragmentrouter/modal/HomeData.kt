package io.github.hexiangyuan.fragmentrouter.modal

/**
 * Created by hexiangyuan on 17-8-30.
 */
data class HomeListModal(var hotKeyWords: ArrayList<String> = ArrayList(),
                         var apps: ArrayList<HomeItemModal> = ArrayList())

data class HomeItemModal(var title: String = "",
                         var content: ArrayList<APPModal> = ArrayList())

data class APPModal(var icon: String = "",
                    var name: String = "",
                    var star: Int = 5,
                    var price: Int = 0)