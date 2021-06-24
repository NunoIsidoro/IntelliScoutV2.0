package com.universe.intelliscout.Equipment

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.universe.intelliscout.Models.Equipment
import com.universe.intelliscout.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditEquipmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.equipment_edit_activity)

        var count = 0
        var id: Int? = null
        var name: String? = null
        var quantity: Int? = null
        var descr: String? = null

        val editTextQuantity = findViewById<EditText>(R.id.editTextQuantityEquipment)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextDescription = findViewById<EditText>(R.id.editTextDescription)
        val buttonPlus = findViewById<Button>(R.id.buttonPlus)
        val buttonDown = findViewById<Button>(R.id.buttonSubtract)
        val buttonSave = findViewById<Button>(R.id.buttonSave)
        val buttonDelete = findViewById<Button>(R.id.buttonDelete)

        val bundle = intent.extras
        bundle?.let {
            id = it.getInt("id")
            name = it.getString("name")
            quantity = it.getInt("quantity")
            descr = it.getString("descr")
        }

        count = quantity!!

        editTextName.setText(name)
        editTextDescription.setText(descr)
        editTextQuantity.setText(count.toString())

        buttonPlus.setOnClickListener {
            count = editTextQuantity.text.toString().toInt()
            // increment counter
            count++
            // writes counter position
            editTextQuantity.setText(count.toString())
        }


        buttonDown.setOnClickListener {
            count = editTextQuantity.text.toString().toInt()
            // decrement counter
            if (count > 0)
                count--
            //writes counter position
            editTextQuantity.setText(count.toString())

        }

        buttonDelete.setOnClickListener {

            GlobalScope.launch(Dispatchers.IO) {
                EquipmentRequest.removeEquipment(id!!)
            }

            Toast.makeText(this, "Equipamento removido com sucesso!", Toast.LENGTH_SHORT).show()
            finish()

        }

        buttonSave.setOnClickListener {

            val equipment = Equipment(
                id,
                editTextName.text.toString(),
                editTextQuantity.text.toString().toInt(),
                editTextDescription.text.toString(),
                ""
            )
            var resultRequest = true

            println("equipment = ${equipment.name}")

            GlobalScope.launch(Dispatchers.IO) {

                resultRequest = EquipmentRequest.editEquipment(equipment)

            }

            if (resultRequest) {
                Toast.makeText(this, "Equipamento alterado com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            } else
                Toast.makeText(
                    this,
                    "Problema no envio, por favor tente novamente!",
                    Toast.LENGTH_SHORT
                ).show()


        }

    }
}