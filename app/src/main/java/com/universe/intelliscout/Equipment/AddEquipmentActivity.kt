package com.universe.intelliscout.Equipment

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.universe.intelliscout.Models.Equipment
import com.universe.intelliscout.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddEquipmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.equipment_add_activity)

        var count = 0
        var resultRequest = true

        val editTextQuantityEquipment = findViewById<TextView>(R.id.editTextQuantityEquipment)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextDescription = findViewById<EditText>(R.id.editTextDescription)
        val buttonPlus = findViewById<Button>(R.id.buttonPlus)
        val buttonSubtract = findViewById<Button>(R.id.buttonSubtract)
        val buttonSave = findViewById<Button>(R.id.buttonSave)


        editTextQuantityEquipment.text = count.toString()

        buttonPlus.setOnClickListener {
            count = editTextQuantityEquipment.text.toString().toInt()
            // increment counter
            count++
            // writes counter position
            editTextQuantityEquipment.text = count.toString()
        }


        buttonSubtract.setOnClickListener {
            count = editTextQuantityEquipment.text.toString().toInt()
            // decrement counter
            if (count > 0)
                count--
            //writes counter position
            editTextQuantityEquipment.text = count.toString()

        }

        buttonSave.setOnClickListener {

            val equipment = Equipment(
                null,
                editTextName.text.toString(),
                editTextQuantityEquipment.text.toString().toInt(),
                editTextDescription.text.toString(),
                ""
            )

            GlobalScope.launch(Dispatchers.IO) {

                resultRequest = EquipmentRequest.addEquipment(equipment)

            }

            if (resultRequest) {

                Toast.makeText(this, "Equipamento adicionado com sucesso!", Toast.LENGTH_SHORT)
                    .show()
                finish()

            } else {

                Toast.makeText(
                    this,
                    "Problema no pedido, por favor tente novamente!",
                    Toast.LENGTH_SHORT
                ).show()

            }


        }

    }
}