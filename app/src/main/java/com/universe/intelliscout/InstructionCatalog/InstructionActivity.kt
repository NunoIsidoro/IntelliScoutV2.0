package com.universe.intelliscout.InstructionCatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textfield.TextInputEditText
import com.universe.intelliscout.Models.InstructionCatalog
import com.universe.intelliscout.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class InstructionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instruction_activity)

        val textInputEditTextName: TextInputEditText = findViewById(R.id.textInputEditTextName)
        val textInputEditTextDescription: TextInputEditText = findViewById(R.id.textInputEditTextDescription)

        val bundle = intent.extras

        var name: String? = null
        var description: String? = null


        bundle?.let {

            name = it.getString("name")
            description = it.getString("description")

        }

        textInputEditTextName.setText(name)
        textInputEditTextDescription.setText(description)




    }
}