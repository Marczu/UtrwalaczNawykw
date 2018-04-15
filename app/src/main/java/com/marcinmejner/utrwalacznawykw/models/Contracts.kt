package com.marcinmejner.utrwalacznawykw.models

import android.provider.BaseColumns

val DATABASE_NAME = "utrwalacznawykow2.db"
val DATABASE_VERSION = 1

object HabitEntry : BaseColumns {
    val TABLE_NAME = "habit"
    val TITLE_COLLUMN = "title"
    val _ID = "id"
    val DESCRIPTION_COLLUMN = "description"
    val IMAGE_COLLUMN = "image"
}