package com.marcinmejner.utrwalacznawykw

data class Habit(val title: String, val description: String, val image: Int)


fun getSmampleHabit(): List<Habit> {
    return listOf(
            Habit("Ćwiczenia", "Codzienne ćwiczenia pomagają w utrzymaniu kondycji i zdrowia", R.drawable.fitness),
            Habit("Programowanie", "Programowanie usprawnia prace mózgu", R.drawable.programing)
    )
}
