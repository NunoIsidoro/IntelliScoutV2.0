package com.universe.intelliscout.Profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.universe.intelliscout.R
import com.universe.intelliscout.Utils.UtilFunctions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)
        //Variables

        var idScout : Int? = null
        var gmail: String? = null

        val imageViewUser = findViewById<ImageView>(R.id.imageViewUser)
        val textViewEditProfile = findViewById<TextView>(R.id.textViewEditProfile)
        val textInputEditTextName = findViewById<TextInputEditText>(R.id.textInputEditTextName)
        val textInputEditTextNIN = findViewById<TextInputEditText>(R.id.textInputEditTextNIN)
        val textInputEditTextBirth = findViewById<TextInputEditText>(R.id.textInputEditTextBirth)
        val textInputEditTextGender = findViewById<TextInputEditText>(R.id.textInputEditTextGender)
        val textInputEditTextContact = findViewById<TextInputEditText>(R.id.textInputEditTextContact)
        val textInputEditTextContactEE = findViewById<TextInputEditText>(R.id.textInputEditTextContactEE)
        val textInputEditTextAddress = findViewById<TextInputEditText>(R.id.textInputEditTextAddress)
        val textInputEditTextDistrict = findViewById<TextInputEditText>(R.id.textInputEditTextDistrict)
        val textInputEditTextEmail = findViewById<TextInputEditText>(R.id.textInputEditTextEmail)
        val textInputEditTextTeam = findViewById<TextInputEditText>(R.id.textInputEditTextTeam)

        val bundle = intent.extras

        bundle?.let {

            idScout = it.getInt("idScout")
            gmail = it.getString("gmail").toString()

        }


        GlobalScope.launch(Dispatchers.IO){

            val user = ProfileRequest.getScoutUser(idScout!!)

            GlobalScope.launch(Dispatchers.Main){

            }

            val local = ProfileRequest.getLocal(user.idLocal!!)

            val team = ProfileRequest.getTeam(user.idScoutTeam!!)

                GlobalScope.launch(Dispatchers.Main){

                    println(user.idLocal)

                val birthString = UtilFunctions().receiveBirthFromDatabaseToString(user.birth!!)

                textInputEditTextName.setText(user.name)
                textInputEditTextNIN.setText(user.nin)
                textInputEditTextBirth.setText(birthString)
                textInputEditTextGender.setText(if (user.gender == 1) "Feminino" else "Masculino")
                textInputEditTextContact.setText(user.phone)
                textInputEditTextContactEE.setText(user.phoneEE)
                textInputEditTextAddress.setText(user.address)
                textInputEditTextDistrict.setText(local)
                textInputEditTextEmail.setText(gmail)
                textInputEditTextTeam.setText(team)

            }

        }

        //Both of this setOnClickListeners start the activity
        textViewEditProfile.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

    }

}