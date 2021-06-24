package com.universe.intelliscout.Models

import org.json.JSONObject

class InstructionCatalog {

    var id: Int? = null
    var name: String? = null
    var description: String? = null

    constructor()

    constructor(id: Int?, name: String?, description: String?) {

        this.id = id
        this.name = name
        this.description = description

    }

    fun toJson(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("name", name)
        jsonObject.put("description", description)
        return jsonObject
    }

    companion object {

        fun fromJson(jsonArticle: JSONObject): InstructionCatalog {
            val instructionCatalog = InstructionCatalog()

            instructionCatalog.id = jsonArticle.getInt("id_instruction_catalog")
            instructionCatalog.name = jsonArticle.getString("name_instruction_catalog")
            instructionCatalog.description = jsonArticle.getString("descr_instruction_catalog")
            return instructionCatalog
        }
    }

}