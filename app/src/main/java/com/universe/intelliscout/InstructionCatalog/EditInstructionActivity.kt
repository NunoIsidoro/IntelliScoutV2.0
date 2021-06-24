package com.universe.intelliscout.InstructionCatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.universe.intelliscout.Models.InstructionCatalog
import com.universe.intelliscout.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditInstructionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instruction_edit_activity)

        val textInputEditTextName : TextInputEditText = findViewById(R.id.textInputEditTextName)
        val textInputEditTextInstructionDescription : TextInputEditText = findViewById(R.id.textInputEditTextInstructionDescription)
        val buttonEdit : Button = findViewById(R.id.buttonEdit)
        val buttonDelete : Button = findViewById(R.id.buttonDelete)

        var id: Int? = null
        var name: String? = null
        var description: String? = null

        val bundle = intent.extras

        bundle?.let {

            id = it.getInt("id")
            name = it.getString("name")
            description = it.getString("description")

        }

        textInputEditTextName.setText(name)
        textInputEditTextInstructionDescription.setText(description)

        buttonDelete.setOnClickListener {

            println(id!!)

            GlobalScope.launch(Dispatchers.IO) {

                InstructionCatalogRequest.removeInstCatalog(id!!)

            }

            Toast.makeText(this, "Instrução eliminada com sucesso!", Toast.LENGTH_SHORT).show()
            finish()

        }

        buttonEdit.setOnClickListener {

            val instruction = InstructionCatalog(id, textInputEditTextName.text.toString(),
                textInputEditTextInstructionDescription.text.toString())

            GlobalScope.launch(Dispatchers.IO) {

                InstructionCatalogRequest.editInstCatalog(instruction)

            }

            Toast.makeText(this@EditInstructionActivity, "Instrução editada com sucesso!", Toast.LENGTH_SHORT).show()
            finish()


        }
    }
}