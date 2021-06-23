package com.universe.intelliscout.Profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.universe.intelliscout.R

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        //Variables
        val imageViewUser = findViewById<ImageView>(R.id.imageViewUser)
        val textViewEditProfile = findViewById<TextView>(R.id.textViewEditProfile)
        val buttonEditProfile = findViewById<Button>(R.id.buttonEditProfile)
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


        //Both of this setOnClickListeners start the activity
        textViewEditProfile.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

        buttonEditProfile.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }


    }

}