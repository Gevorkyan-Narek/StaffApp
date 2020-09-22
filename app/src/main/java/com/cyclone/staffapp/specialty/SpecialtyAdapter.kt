package com.cyclone.staffapp.specialty

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.staffapp.R
import com.cyclone.staffapp.model.Specialty
import com.cyclone.staffapp.workers.WorkersFragment
import kotlinx.android.synthetic.main.specialty_item_adapter.view.*

class SpecialtyAdapter(private val list: List<Specialty>) :
    RecyclerView.Adapter<SpecialtyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val specialtyName: TextView = itemView.specialtyName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.specialty_item_adapter, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.specialtyName.text = list[position].name

        holder.itemView.setOnClickListener {
            val fragment = WorkersFragment()
            Log.d("Specialty", list[position].id.toString())
            Bundle().apply {
                putInt("id", list[position].id)
            }.let { bundle ->
                fragment.arguments = bundle
            }
            (it.context as FragmentActivity).supportFragmentManager.beginTransaction().addToBackStack("specialty")
                .replace(R.id.fragment, fragment).commit()
        }
    }

    override fun getItemCount(): Int = list.size
}