package com.universe.intelliscout.InstructionCatalog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.universe.intelliscout.Models.InstructionCatalog
import com.universe.intelliscout.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListEditInstructionActivity : AppCompatActivity() {

    var instructions: MutableList<InstructionCatalog> = ArrayList()
    var instructionAdapter: EditInstructionAdapter? = null
    lateinit var listViewInstructions : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.instruction_list_edit_activity)

        listViewInstructions = findViewById(R.id.ListViewInstructions)

        instructionAdapter = EditInstructionAdapter()
        listViewInstructions.adapter = instructionAdapter

        GlobalScope.launch(Dispatchers.IO) {

            InstructionCatalogRequest.getAllInstCatalog {

                instructions.addAll(it)

            }

            GlobalScope.launch(Dispatchers.Main) {
                instructionAdapter?.notifyDataSetChanged()

            }
        }
    }

    inner class EditInstructionAdapter : BaseAdapter() {
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

            rowView.setOnClickListener{

                val intent = Intent(this@ListEditInstructionActivity, EditInstructionActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.putExtra("id", instructions[position].id)
                intent.putExtra("name", instructions[position].name)
                intent.putExtra("description", instructions[position].description)
                startActivity(intent)


            }

            GlobalScope.launch(Dispatchers.IO) {

                InstructionCatalogRequest.getAllInstCatalog {

                    instructions.clear()
                    instructions.addAll(it)

                }
            }


            instructionAdapter?.notifyDataSetChanged()



            return rowView
        }
    }
}