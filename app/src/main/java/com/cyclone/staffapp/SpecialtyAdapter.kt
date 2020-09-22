package com.cyclone.staffapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.staffapp.network.Specialty
import kotlinx.android.synthetic.main.specialty_item_adapter.view.*

class SpecialtyAdapter(private val list: List<Specialty>): RecyclerView.Adapter<SpecialtyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val specialtyName: TextView = itemView.specialtyName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.specialty_item_adapter, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.specialtyName.text = list[position].name
    }

    override fun getItemCount(): Int = list.size
}