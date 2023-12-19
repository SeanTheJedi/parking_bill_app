package com.rg.parking_richmond

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdaptor(var history:List<String>) : RecyclerView.Adapter<HistoryAdaptor.TodoViewHolder>() {
    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView)  {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_history_layout, parent, false)
        return TodoViewHolder(view)

    }

    override fun getItemCount(): Int {
        return history.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val tvLabel = holder.itemView.findViewById<TextView>(R.id.tv_row1)
        tvLabel.text = "${history.get(position)}"

    }

}