package com.example.gymapp.Adaper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gymapp.Model.Plan
import com.example.gymapp.R
import kotlinx.android.synthetic.main.item_plan.view.*

class PlanAdapter(
    private val plans: ArrayList<Plan>,

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(plans: Plan) {
            itemView.tv_name.text =plans.name
            itemView.tv_time.text =plans.time
            Glide.with(itemView.context).load(plans.img).into(itemView.imv_bg);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_plan, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as MyViewHolder).bind(plans[position])

    override fun getItemCount(): Int = plans.size

    fun addData(list: List<Plan>) {
        plans.clear()
        plans.addAll(list)
        notifyDataSetChanged()
    }
}