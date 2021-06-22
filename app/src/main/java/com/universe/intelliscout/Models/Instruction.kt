package com.universe.intelliscout.Models

import org.json.JSONObject

class Instruction {

    var id : Int? = null
    var title : String? = null
    var descri : String? = null
    var img : String? = null

    constructor(){

    }

    constructor(id: Int?, title: String?, descri: String?, img: String?){

        this.id = id
        this.title = title
        this.descri = descri
        this.img = img

    }

    fun toJson () : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("title", title)
        jsonObject.put("descri", descri)
        jsonObject.put("img", img)
        return  jsonObject
    }

    companion object {

        fun fromJson(jsonArticle: JSONObject) : Instruction {
            val instruction = Instruction()

            instruction.id = jsonArticle.getInt("id_instruction")
            instruction.title = jsonArticle.getString("title_instruction")
            instruction.descri = jsonArticle.getString("descri_instruction")
            instruction.img = jsonArticle.getString("url_img_instruction")
            return instruction
        }
    }

}