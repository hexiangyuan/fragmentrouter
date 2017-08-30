package io.github.hexiangyuan.fragmentrouter

import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by hexiangyuan on 17-8-30.
 */
class KeyWordsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
     var data = ArrayList<String>()

    fun changData(data:ArrayList<String>){
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_layout_home_keywords,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
         (holder as MyViewHolder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(str: String) {
            (itemView as TextView).text = str
        }
    }
}