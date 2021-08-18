package com.example.golftracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class RecyclerViewAdapter(private val holes: MutableList<Hole>,
                          private val onClickListener: OnCLickInterface)
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflate the custom view from xml layout file
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item,parent,false)

        // return the view holder
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.hole.text = holes[position].hole
        holder.shots.text = holes[position].numberOfShots.toString()

        holder.hole.setOnClickListener {
            onClickListener.onClickListener(holes, position)
        }
    }


    override fun getItemCount(): Int {
        // number of items in the data set held by the adapter
        return holes.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val hole: Button = itemView.item
        val shots: TextView = itemView.number_of_shots
    }
}