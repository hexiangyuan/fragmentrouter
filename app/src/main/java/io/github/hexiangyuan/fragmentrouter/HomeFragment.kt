package io.github.hexiangyuan.fragmentrouter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.hexiangyuan.fragmentrouter.modal.APPModal
import io.github.hexiangyuan.fragmentrouter.modal.HomeItemModal
import io.github.hexiangyuan.fragmentrouter.modal.HomeListModal
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by hexiangyuan on 17-8-30.
 * This home Fragment for Google play Example.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        initData()
    }

    private fun initData() {
        var keywords: ArrayList<String> = ArrayList()
        for (i in 0..10) {
            keywords.add("KeyWords ${i}")
        }
        val data = HomeListModal()
        data.hotKeyWords = keywords
        var items = ArrayList<HomeItemModal>()
        for (i in 0..10) {
            val homeItem = HomeItemModal()
            val itemss = ArrayList<APPModal>()
            homeItem.title = "title $i"
            for (j in 0..10) {
                val item = APPModal()
                item.name = "app-$i-$j"
                item.icon =""
                item.price = 0
                item.star = 500
                itemss.add(item)
            }
            homeItem.content = itemss
            items.add(homeItem)
        }
        data.apps = items
        val homeListAdapter = HomeListAdapter()
        recyclerView.adapter = homeListAdapter
        homeListAdapter.changeData(data)

    }

}