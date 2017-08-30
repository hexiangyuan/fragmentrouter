package io.github.hexiangyuan.fragmentrouter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.github.hexiangyuan.fragmentrouter.modal.APPModal
import kotlinx.android.synthetic.main.item_layout_home.view.*
import kotlinx.android.synthetic.main.item_layout_home_simple_app.view.*

/**
 * Created by hexiangyuan on 17-8-30.
 */
class HomeAppAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder> (){
    private var data = ArrayList<APPModal>()

        fun changData(value:ArrayList<APPModal>) {
            data = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(parent!!.context).inflate(R.layout.item_layout_home_simple_app,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        return (holder as MyViewHolder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(app:APPModal) {
            itemView.tv_name.text = app.name
            itemView.iv_icon.setImageResource(R.mipmap.ic_launcher_round)
            itemView.tv_star.text = (app.star.toFloat()/100).toString()
            if(app.price.toFloat()/100 == 0f ){
                itemView.tv_price.text = "Free"
            }else{
                itemView.tv_price.text = (app.price.toFloat()/100).toString()
            }
        }
    }
}