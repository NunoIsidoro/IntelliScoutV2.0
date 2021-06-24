package com.universe.intelliscout.InstructionCatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.universe.intelliscout.Equipment.EquipmentRequest
import com.universe.intelliscout.Equipment.ListEquipmentActivity
import com.universe.intelliscout.Models.Equipment
import com.universe.intelliscout.Models.InstructionCatalog
import com.universe.intelliscout.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListInstruction : AppCompatActivity() {

    var instructions: MutableList<InstructionCatalog> = ArrayList()
    var instructionAdapter: InstructionAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instruction_list_activity)

        val ListViewInstructions: ListView = findViewById(R.id.ListViewInstructions)

        instructionAdapter = InstructionAdapter()
        ListViewInstructions.adapter = instructionAdapter

        GlobalScope.launch(Dispatchers.IO) {

            InstructionCatalogRequest.getAllInstCatalog {

                instructions.addAll(it)

            }

            GlobalScope.launch(Dispatchers.Main) {
                instructionAdapter?.notifyDataSetChanged()

            }
        }

    }

    inner class InstructionAdapter : BaseAdapter() {
        override fun getCount(): Int {

            //tamanho do array equipamneto
            return instructions.size
        }

        override fun getItem(position: Int): Any {

            //posicao do array equipamneto
            return instructions[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rowView = layoutInflater.inflate(R.layout.instruction_row, parent, false)

            //declaração das textViews e botões
            val textViewTitle = rowView.findViewById<TextView>(R.id.textViewTitle)
            val textViewDescription = rowView.findViewById<TextView>(R.id.textViewDescription)

            //enviar os dados da classe equipamento para as textViews
            textViewTitle.text = instructions[position].name
            textViewDescription.text = instructions[position].description

            return rowView
        }
    }

}