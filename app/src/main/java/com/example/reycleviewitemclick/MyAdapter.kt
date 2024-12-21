package com.example.reycleviewitemclick

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

class MyAdapter(val activity:Activity,val data:ArrayList<data>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    lateinit var Mylistner:onitemclicklistner

    interface onitemclicklistner{
        fun onclick(position:Int)
    }
    fun onclicklistner(listner:onitemclicklistner){
        Mylistner=listner
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val inflat = LayoutInflater.from(activity)
        val itemview=inflat.inflate(R.layout.item,parent,false)
        return MyViewHolder(itemview,Mylistner)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        holder.title.text=data[position].title
        Glide.with(activity)
            .load(data[position].imageUrl)
            .placeholder(R.drawable.baseline_image_24)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return data.size
    }
    class MyViewHolder(itemview: View, listner: onitemclicklistner) : ViewHolder(itemview) {
        val title = itemview.findViewById<TextView>(R.id.textView)
        val image = itemview.findViewById<ImageView>(R.id.imageView)

        init{
            itemview.setOnClickListener{
                listner.onclick(adapterPosition)
            }
        }

    }
}