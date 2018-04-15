package com.marcinmejner.utrwalacznawykw

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import com.marcinmejner.utrwalacznawykw.models.HabitDbTable
import kotlinx.android.synthetic.main.activity_create_habit.*
import kotlinx.android.synthetic.main.single_card.*
import java.io.IOException

class CreateHabitActivity : AppCompatActivity() {

    private val TAG = "CreateHabitActivity"

    private val REQ_CODE = 10
    private var imageBitmap: Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_habit)
    }

    fun chooseImage(view: View) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        val chooser = Intent.createChooser(intent, "Wybierz obrazek dla nawyku")
        startActivityForResult(chooser, REQ_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_CODE && resultCode == Activity.RESULT_OK
                && data != null
                && data.data != null) {
            Log.d(TAG, "bitmapa wybrana")

            val bitmap = tryReadBitmap(data.data)

            Log.d(TAG, "Bitmapa to: " + bitmap.toString())
            bitmap?.let {
                this.imageBitmap = bitmap
                iv_image.setImageBitmap(bitmap)
            }
        }
    }

    private fun tryReadBitmap(data: Uri): Bitmap? {
        return try {
            MediaStore.Images.Media.getBitmap(contentResolver, data)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun storeHabit(view: View) {

        if (et_title.isBlank() || et_description.isBlank()) {
            displayError("Musisz podać tytuł i opis")
            return
        } else if (imageBitmap == null) {
            Log.d(TAG, "brakuje obrazka")
            displayError("Wybierz obrazek dla nawyku")
            return
        }

        val title = et_title.text.toString()
        val description = et_description.text.toString()

        val habit = Habit(title, description, imageBitmap!!)

        val id = HabitDbTable(this).store(habit)

        if(id == -1L) {
            displayError("Nawyk nie moze zostać zapisany")
        }else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    private fun displayError(s: String) {
        tv_error.text = s
        tv_error.visibility = View.VISIBLE

    }

    private fun EditText.isBlank() = this.text.toString().isBlank()


}
