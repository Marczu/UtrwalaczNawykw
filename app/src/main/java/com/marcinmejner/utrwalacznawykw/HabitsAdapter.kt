package com.marcinmejner.utrwalacznawykw

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.marcinmejner.utrwalacznawykw.HabitsAdapter.HabitViewHolder

class HabitsAdapter(val habit: List<Habit>) : RecyclerView.Adapter<HabitsAdapter.HabitViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {



    }

    override fun getItemCount(): Int {
      return habit.size
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {


    }


    class HabitViewHolder(val iv: View) : RecyclerView.ViewHolder(iv) {

    }

}
