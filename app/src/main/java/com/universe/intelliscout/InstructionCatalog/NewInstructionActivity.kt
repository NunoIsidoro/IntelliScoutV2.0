package com.universe.intelliscout.InstructionCatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.universe.intelliscout.Models.InstructionCatalog
import com.universe.intelliscout.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewInstructionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instruction_new_activity)

        val textInputEditTextName : TextInputEditText = findViewById(R.id.textInputEditTextName)
        val textInputEditTextInstructionDescription : TextInputEditText = findViewById(R.id.textInputEditTextInstructionDescription)
        val buttonSave : Button = findViewById(R.id.buttonSave)
        val buttonCancel : Button = findViewById(R.id.buttonCancel)

        buttonCancel.setOnClickListener{ finish() }

        buttonSave.setOnClickListener {

            if(textInputEditTextName.text!!.isEmpty() || textInputEditTextInstructionDescription.text!!.isEmpty())
                Toast.makeText(this, "Por favor, preencha os dados todos!", Toast.LENGTH_SHORT).show()
            else{

                val instruction = InstructionCatalog(null, textInputEditTextName.text.toString(),
                    textInputEditTextInstructionDescription.text.toString())


                GlobalScope.launch(Dispatchers.IO){

                    InstructionCatalogRequest.addInstCatalog(instruction)

                }

                Toast.makeText(this, "Instrução adicionada com sucesso!", Toast.LENGTH_SHORT).show()
                finish()


            }

        }

    }
}