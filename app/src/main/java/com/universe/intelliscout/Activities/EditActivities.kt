package com.universe.intelliscout.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.universe.intelliscout.Authentic.LoginRequest
import com.universe.intelliscout.HomeActivity
import com.universe.intelliscout.Models.Login
import com.universe.intelliscout.Profile.ProfileRequest
import com.universe.intelliscout.R
import com.universe.intelliscout.Utils.GlobalRequests
import com.universe.intelliscout.Utils.UtilFunctions
import ipca.example.projetosemestre.Models.ScoutUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class EditActivities  : AppCompatActivity() {
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_edit_activity)

        //Variables

        var idScout : Int? = null
        var gmail: String? = null
        var birthString: String? = null
        var gender: Int? = null
        var idLocal: Int? = null
        var newUserBool = false
        val listDistricts: MutableList<String> = ArrayList()
        val loginList: MutableList<Login> = ArrayList()

        val imageViewUser = findViewById<ImageView>(R.id.imageViewUser)
        val textViewEditImage = findViewById<TextView>(R.id.textViewEditImage)
        val textInputEditTextName = findViewById<TextInputEditText>(R.id.textInputEditTextName)
        val textInputEditTextNIN = findViewById<TextInputEditText>(R.id.textInputEditTextNIN)
        val textInputEditTextContact = findViewById<TextInputEditText>(R.id.textInputEditTextContact)
        val textInputEditTextContactEE = findViewById<TextInputEditText>(R.id.textInputEditTextContactEE)
        val textInputEditTextAddress = findViewById<TextInputEditText>(R.id.textInputEditTextAddress)
        val textInputEditTextDistrict = findViewById<TextInputEditText>(R.id.textInputEditTextDistrict)

        val datePicker: DatePicker = findViewById(R.id.datePicker)
        val radioButtonMasculine: RadioButton = findViewById(R.id.radioButtonMasculine)
        val radioButtonFeminine: RadioButton = findViewById(R.id.radioButtonFeminine)
        val buttonCancel = findViewById<ImageButton>(R.id.buttonCancel)
        val buttonSave = findViewById<ImageButton>(R.id.buttonSave)

        val bundle = intent.extras

        bundle?.let {

            idScout = it.getInt("idScout")
            gmail = it.getString("gmail")

        }

        GlobalScope.launch(Dispatchers.IO) {
            val user = ProfileRequest.getScoutUser(idScout!!)

            GlobalScope.launch(Dispatchers.Main) {

                if(user.active == 0)
                    newUserBool = true

                idLocal = user.idLocal

                if (user.nin != "null") {
                    textInputEditTextNIN.setText(user.nin)
                }
                if (user.name != "null") {
                    textInputEditTextName.setText(user.name)
                }
                if (user.address != "null") {
                    textInputEditTextAddress.setText(user.address)
                }
                if (user.phone != "null") {
                    textInputEditTextContact.setText(user.phone)
                }
                if (user.phoneEE != "null") {
                    textInputEditTextContactEE.setText(user.phone)
                }

                if (user.gender == 0) {
                    radioButtonFeminine.isSelected = true
                } else {
                    radioButtonMasculine.isSelected = true
                }

                birthString = UtilFunctions().receiveBirthFromDatabaseToString(user.birth!!)

                //Receive the date from the database in Calendar
                var birth =
                    UtilFunctions().receiveBirthFromDatabaseToCalendar(user.birth.toString())

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

            }

        }

        buttonCancel.setOnClickListener { finish() }

        buttonSave.setOnClickListener {

            if (textInputEditTextName.text!!.isEmpty() || textInputEditTextAddress.text!!.isEmpty()
                || textInputEditTextContact.text!!.isEmpty() || textInputEditTextNIN.text!!.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Por favor preencha todos os campos obrigatórios!",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                println("idLocal = $idLocal")


                val newUser = ScoutUser(
                    idScout,
                    textInputEditTextName.text.toString(),
                    birthString,
                    gender,
                    textInputEditTextContact.text.toString(),
                    textInputEditTextAddress.text.toString(),
                    1,
                    textInputEditTextNIN.text.toString(),
                    textInputEditTextContactEE.text.toString(),
                    "null",
                    idScout,
                    1,
                    idLocal
                )


                GlobalScope.launch(Dispatchers.IO) {
                    ProfileRequest.editScoutUser(newUser)
                }


                Toast.makeText(this, "Perfil editado com sucesso!", Toast.LENGTH_SHORT).show()
                finish()


            }
        }
    }
    */
}
