package com.marcinmejner.utrwalacznawykw.models

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.SyncStateContract.Helpers.insert
import android.system.Os.close
import android.util.Log
import com.marcinmejner.utrwalacznawykw.Habit
import java.io.ByteArrayOutputStream
import java.util.stream.Stream

class HabitDbTable(context: Context) {

    private val TAG = "HabitDbTable"

    private val dbHelper = HabitDb(context)

    fun store(habit: Habit): Long {
        val db = dbHelper.writableDatabase

        val values = ContentValues()
        with(values) {

            put(HabitEntry.TITLE_COLLUMN, habit.title)
            put(HabitEntry.DESCRIPTION_COLLUMN, habit.description)
            put(HabitEntry.IMAGE_COLLUMN, toByteArrat(habit.image))
        }

        val id = db.transaction {
            insert(HabitEntry.TABLE_NAME, null, values)

        }


        Log.d(TAG, "Zapisany nowy nawyk do bazy: $habit")

        db.close()

        return id
    }

    fun readAllHabits(): List<Habit> {

        val colums = arrayOf(
                HabitEntry._ID,
                HabitEntry.TITLE_COLLUMN,
                HabitEntry.DESCRIPTION_COLLUMN,
                HabitEntry.IMAGE_COLLUMN)

        val order = "${HabitEntry._ID} ASC"

        val db = dbHelper.readableDatabase

        val cursor = db.query(HabitEntry.TABLE_NAME, colums, null, null,
                null,null, order)

        val habits = mutableListOf<Habit>()

        while (cursor.moveToNext()){
            val title = cursor.getString(cursor.getColumnIndex(HabitEntry.TITLE_COLLUMN))
            val description = cursor.getString(cursor.getColumnIndex(HabitEntry.DESCRIPTION_COLLUMN))
            val byteArray = cursor.getBlob(cursor.getColumnIndex(HabitEntry.IMAGE_COLLUMN))
            val bimap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

            habits.add(Habit(title, description, bimap))
        }
        cursor.close()
        return habits

    }

    private fun toByteArrat(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()

        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream)
        return stream.toByteArray()
    }
}

private inline fun <T> SQLiteDatabase.transaction(function: SQLiteDatabase.() -> T): T {
    beginTransaction()
    val result = try {
        val returnValue = function()
        setTransactionSuccessful()

        returnValue
    } finally {
        endTransaction()
    }
    close()

    return result

}