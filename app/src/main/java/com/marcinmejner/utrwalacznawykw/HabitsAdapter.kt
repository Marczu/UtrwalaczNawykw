package com.marcinmejner.utrwalacznawykw

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcinmejner.utrwalacznawykw.HabitsAdapter.HabitViewHolder
import kotlinx.android.synthetic.main.single_card.view.*

class HabitsAdapter(val habit: List<Habit>) : RecyclerView.Adapter<HabitsAdapter.HabitViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_card, parent, false)
        return HabitViewHolder(view)
    }

    override fun getItemCount(): Int = habit.size

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habit[position]

        holder.view.tvTitle.text = habit.title
        holder.view.tvDescription.text = habit.description
        holder.view.ivIcon.setImageBitmap(habit.image)
    }

    class HabitViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
