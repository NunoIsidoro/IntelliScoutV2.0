package ipca.example.projetosemestre.Models

import org.json.JSONObject

class InstructionCatalog {

    var id : Int? = null
    var name : String? = null
    var idInstruction : Int? = null
    var idEquipment : Int? = null

    constructor(){

    }

    constructor(id: Int?, name: String?, idInstruction: Int?, idEquipment: Int?){

        this.id = id
        this.name = name
        this.idInstruction = idInstruction
        this.idEquipment = idEquipment

    }

    fun toJson () : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("name", name)
        jsonObject.put("idInstruction", idInstruction)
        jsonObject.put("idEquipment", idEquipment)
        return  jsonObject
    }

    companion object {

        fun fromJson(jsonArticle: JSONObject) : InstructionCatalog {
            val instructionCatalog = InstructionCatalog()

            instructionCatalog.id = jsonArticle.getInt("id")
            instructionCatalog.name = jsonArticle.getString("name")
            instructionCatalog.idInstruction = jsonArticle.getInt("idInstruction")
            instructionCatalog.idEquipment = jsonArticle.getInt("idEquipment")
            return instructionCatalog
        }
    }

}