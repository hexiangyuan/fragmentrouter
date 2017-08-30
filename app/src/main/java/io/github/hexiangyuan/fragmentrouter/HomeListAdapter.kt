package io.github.hexiangyuan.fragmentrouter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.hexiangyuan.fragmentrouter.modal.HomeItemModal
import io.github.hexiangyuan.fragmentrouter.modal.HomeListModal
import kotlinx.android.synthetic.main.item_layout_home.view.*

/**
 * Created by hexiangyuan on 17-8-30.
 */
class HomeListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var data = HomeListModal()
    private val TYPE_KEYWORDS = 0;
    private val TYPE_APP_LIST = 1;

    fun changeData(data:HomeListModal){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is KeyWordsViewHolder) {
            holder.bind(data.hotKeyWords)
        } else if (holder is AppListViewHolder) {
            holder.bind(data.apps[position - 1])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            TYPE_KEYWORDS -> {
                val recyclerView = RecyclerView(parent.context)
                return KeyWordsViewHolder(recyclerView)
            }
            else -> {
                return AppListViewHolder(inflater.inflate(R.layout.item_layout_home, parent, false))
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return TYPE_KEYWORDS
        else
            return TYPE_APP_LIST
    }

    override fun getItemCount(): Int {
        return data.apps.size + 1
    }

    class KeyWordsViewHolder(itemView: RecyclerView) : RecyclerView.ViewHolder(itemView) {
        val recyclverView: RecyclerView

        init {
            recyclverView = itemView
        }

        fun bind(keywords: ArrayList<String>) {
            val adapter = KeyWordsAdapter()
            recyclverView.layoutManager = LinearLayoutManager(itemView.context,
                    LinearLayoutManager.HORIZONTAL, false)
            recyclverView.adapter = adapter
            adapter.changData(keywords)
        }
    }

    class AppListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(apps: HomeItemModal) {
            itemView.tv_title.text = apps.title
            itemView.recyclerView_apps.layoutManager = LinearLayoutManager(itemView.context,
                    LinearLayoutManager.HORIZONTAL, false)
            val homeAppAdapter = HomeAppAdapter()
            itemView.recyclerView_apps.adapter = homeAppAdapter
            homeAppAdapter.changData(apps.content)
        }
    }
}