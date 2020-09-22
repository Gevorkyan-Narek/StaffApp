package com.cyclone.staffapp.worker

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.staffapp.R
import com.cyclone.staffapp.network.Person
import kotlinx.android.synthetic.main.workers_item_adapter.view.*

class WorkersAdapter(private var workers: List<Person>): RecyclerView.Adapter<WorkersAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.workerName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.workers_item_adapter, parent, false)
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val worker = workers[position]
        holder.name.text = "${worker.firstName} ${worker.lastName}"
    }

    override fun getItemCount(): Int = workers.size
}