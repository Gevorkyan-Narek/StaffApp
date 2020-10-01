package com.cyclone.staffapp.presentation.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.cyclone.staffapp.R
import com.cyclone.staffapp.domain.entities.EmployeeDB
import com.cyclone.staffapp.getAge
import com.cyclone.staffapp.presentation.ui.EmployeeFragment
import com.cyclone.staffapp.setImage
import kotlinx.android.synthetic.main.workers_item_adapter.view.*

class WorkersAdapter(private val workers: List<EmployeeDB>) :
    RecyclerView.Adapter<WorkersAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.workerName
        val age: TextView = itemView.age
        val avatar: ImageView = itemView.avatar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.workers_item_adapter, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val worker = workers[position]
        val resources = holder.age.context.resources
        holder.name.text = resources.getString(R.string.name, worker.firstName, worker.lastName)
        holder.age.text = resources.getString(R.string.age, worker.birthday.getAge())
        holder.avatar.setImage(holder.avatar.context, workers[position].avatarURI)

        holder.itemView.setOnClickListener {
            val fragment = EmployeeFragment()
            Bundle().apply {
                putLong("id", worker.id)
            }.let { bundle ->
                fragment.arguments = bundle
            }

            (holder.itemView.context as FragmentActivity).supportFragmentManager.beginTransaction()
                .addToBackStack("workers").replace(R.id.fragment, fragment).commit()
        }
    }

    override fun getItemCount(): Int = workers.size
}