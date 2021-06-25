package com.universe.intelliscout.Activities

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.universe.intelliscout.Profile.ProfileRequest
import com.universe.intelliscout.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import com.universe.intelliscout.Models.Activity

class EditActivities  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activities_edit_activity)

        //Variables

        var idActivity: Int? = null
        var name: String? = null
        var dtStart: String? = null
        var hourStart: String? = null
        var local: Int? = null
        var idType: Int? = null
        // val idActivity: MutableList<String> = ArrayList()
        // val loginList: MutableList<Login> = ArrayList()

        val activityname = findViewById<TextInputEditText>(R.id.textInputLayoutActivityName)
        val activityType = findViewById<TextInputEditText>(R.id.textInputEditTextActivityDescription)
        val ActivityAdress = findViewById<TextInputEditText>(R.id.textInputEditTextActivityAdress)
        
        val timePicker: TimePicker = findViewById(R.id.timePicker)
        val datePicker: DatePicker = findViewById(R.id.datePicker)


        val buttonCancel = findViewById<ImageButton>(R.id.buttonCancel)
        val buttonSave = findViewById<ImageButton>(R.id.buttonSave)

        val bundle = intent.extras

        bundle?.let {
            idActivity = it.getInt("id")
        }

        GlobalScope.launch(Dispatchers.IO) {
            val activity = ActivitiesRequest.getActivity(idActivity!!)

            GlobalScope.launch(Dispatchers.Main) {

                if (activity.name != "null") {
                    activityname.setText(activity.name)
                }
                //  if (activity.dtStart != "null") {
                //      datePicker.(activity.dtStart)
                //   }
                //   if (activity.hourStart != "null") {
                //       timePicker.setText(activity.hourStart)
                //   }
                if (activity.idLocal != null) {
                    ActivityAdress.setText(activity.idLocal.toString())
                }
                if (activity.idActivityType != null) {
                    activityType.setText(activity.idActivityType.toString())
                }
            }
        }

        buttonCancel.setOnClickListener { finish() }

        buttonSave.setOnClickListener {
            val year = Calendar.getInstance()[Calendar.YEAR]

            if (activityname.text!!.isEmpty() || activityType.text!!.isEmpty()
                || datePicker.year <= year || ActivityAdress.text!!.isEmpty()
            ) {
                Toast.makeText(
                    this,
                    "Por favor preencha todos os campos obrigatórios!",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val newActivity = Activity(
                    idActivity,
                    name,
                    dtStart,
                    hourStart,
                    idType,
                    local,
                )

                GlobalScope.launch(Dispatchers.IO) {
                    ActivitiesRequest.editActivity(newActivity)
                }

                Toast.makeText(this, "Perfil editado com sucesso!", Toast.LENGTH_SHORT).show()
                finish()


            }
        }
    }
}
