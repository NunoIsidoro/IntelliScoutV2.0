package com.universe.intelliscout.Activities

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.universe.intelliscout.Profile.ProfileRequest
import com.universe.intelliscout.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import com.universe.intelliscout.Models.Activity
import com.universe.intelliscout.Utils.GlobalRequests
import com.universe.intelliscout.Utils.UtilFunctions
import kotlin.collections.ArrayList

class EditActivities  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activities_edit_activity)

        //Variables

        var idActivity: Int? = null
        var name: String? = null
        var dtStart: String? = null
        var local: Int? = null
        var idType: Int? = null
        var idSection: Int? = null
        var birthString: String? = null


        val textInputEditTextName = findViewById<TextInputEditText>(R.id.textInputEditTextActivityName)
        val textInputEditTextActivityType = findViewById<TextInputEditText>(R.id.textInputEditTextActivityType)
        val textInputEditTextDistrict = findViewById<TextInputEditText>(R.id.textInputEditTextActivityDistrict)
        val textInputEditTextSection = findViewById<TextInputEditText>(R.id.textInputEditTextActivitySection)

        val datePicker: DatePicker = findViewById(R.id.datePicker)


        val buttonDelete = findViewById<ImageButton>(R.id.buttonDelete)
        val buttonEdit = findViewById<ImageButton>(R.id.buttonEdit)

        val bundle = intent.extras


        bundle?.let {
            idActivity = it.getInt("id")
            name = it.getString("name")
            local = it.getInt("idLocal")
            dtStart = it.getString("dt_start")
            idType = it.getInt("idType")
        }

        println(idActivity!!)

        GlobalScope.launch(Dispatchers.IO) {
            val activity = ActivitiesRequest.getActivity(idActivity!!)

            val listTypes = arrayOf("Acampamento", "Caminhada", "Missa de Escuteiros", "Voluntariado",
                "Angariação de fundos", "Reunião", "Atividade de desporto", "Atividade dinâmica")

            val listDistricts = arrayOf("Não Selecionado", "Aveiro", "Beja", "Braga",
            "Bragança", "Castelo Branco", "Coimbra", "Évora", "Faro", "Guarda", "Leiria", "Lisboa",
            "Portalegre", "Porto", "Santarém", "Setúbal", "Viana do Castelo", "Vila Real",
            "Viseu", "Madeira", "Açores")

            val listSection = arrayOf("Não associado", "Lobito", "Explorador", "Pioneiro", "Caminheiro",
            "Gamela")


            GlobalScope.launch(Dispatchers.Main) {

                if (activity.name != "null") {
                    textInputEditTextName.setText(activity.name)
                }

                textInputEditTextActivityType.setText(listTypes[activity.idActivityType!! - 1])

                textInputEditTextDistrict.setText(listDistricts[activity.idLocal!! - 1])
                //textInputEditTextSection.setText()

                var checkedItem = (activity.idActivityType!! - 1)

                textInputEditTextActivityType.setOnClickListener {

                    MaterialAlertDialogBuilder(this@EditActivities)
                        .setTitle(resources.getString(R.string.type))
                        .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                            // Respond to neutral button press
                        }
                        .setPositiveButton(resources.getString(R.string.ok)) { dialog, which ->
                            // Respond to positive button press
                        }
                        // Single-choice items (initialized with checked item)
                        .setSingleChoiceItems(
                            listTypes,
                            checkedItem
                        ) { dialog, which ->
                            // Respond to item chosen
                            textInputEditTextActivityType.setText(listTypes[which])
                            idType = which + 1
                        }
                        .show()
                }

                checkedItem = (activity.idLocal!! - 1)

                textInputEditTextDistrict.setOnClickListener {

                    MaterialAlertDialogBuilder(this@EditActivities)
                        .setTitle(resources.getString(R.string.district))
                        .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                            // Respond to neutral button press
                        }
                        .setPositiveButton(resources.getString(R.string.ok)) { dialog, which ->
                            // Respond to positive button press
                        }
                        // Single-choice items (initialized with checked item)
                        .setSingleChoiceItems(
                            listDistricts,
                            checkedItem
                        ) { dialog, which ->
                            // Respond to item chosen
                            textInputEditTextDistrict.setText(listDistricts[which])
                            local = which + 1
                        }
                        .show()
                }

                textInputEditTextSection.setOnClickListener {

                    MaterialAlertDialogBuilder(this@EditActivities)
                        .setTitle(resources.getString(R.string.section))
                        .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                            // Respond to neutral button press
                        }
                        .setPositiveButton(resources.getString(R.string.ok)) { dialog, which ->
                            // Respond to positive button press
                        }
                        // Single-choice items (initialized with checked item)
                        .setSingleChoiceItems(
                            listSection,
                            checkedItem
                        ) { dialog, which ->
                            // Respond to item chosen
                            textInputEditTextSection.setText(listSection[which])
                            idSection = which + 1
                        }
                        .show()
                }


                //Receive the begin date from database
                birthString = UtilFunctions().receiveBirthFromDatabaseToString(activity.dtStart!!)

                //Receive the date from the database in Calendar
                var birth = UtilFunctions().receiveBirthFromDatabaseToCalendar(activity.dtStart.toString())

                //Validation that set birth to date format editable
                datePicker.init(
                        birth.get(Calendar.YEAR), birth.get(Calendar.MONTH),
                        birth.get(Calendar.DAY_OF_MONTH)
                ) { view, year, month, day ->

                    birth = UtilFunctions().datePickertoDateFormat(day, (month + 1), year)

                    birthString = UtilFunctions().dateToString(
                            birth.get(Calendar.DAY_OF_MONTH),
                            (birth.get(Calendar.MONTH) + 1),
                            birth.get(Calendar.YEAR)
                    )

                }

                buttonDelete.setOnClickListener {

                    ActivitiesRequest.removeActivity(activity.id!!)
                    Toast.makeText(this@EditActivities, "Atividade removida com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()

                }

                buttonEdit.setOnClickListener {

                    if (textInputEditTextName.text!!.isEmpty()) {

                        Toast.makeText(
                            this@EditActivities,
                            "Por favor preencha todos os campos obrigatórios!",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {

                        val newActivity = Activity(
                            idActivity,
                            textInputEditTextName.text.toString(),
                            birthString,
                            "12:30:00",
                            local,
                            idType,
                        )

                            ActivitiesRequest.editActivity(newActivity)


                        Toast.makeText(this@EditActivities, "Perfil editado com sucesso!", Toast.LENGTH_SHORT).show()
                        finish()



                    }
                }
            }
        }
    }
}
