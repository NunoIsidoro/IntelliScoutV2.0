package ipca.example.projetosemestre.Models

import org.json.JSONObject

class ScoutSection {

    var id : Int? = null
    var name : String? = null

    constructor(){

    }

    constructor(id: Int?, name: String?){

        this.id = id
        this.name = name

    }

    fun toJson () : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("name", name)
        return  jsonObject
    }

    companion object {

        fun fromJson(jsonArticle: JSONObject) : ScoutSection {
            val scoutSection = ScoutSection()

            scoutSection.id = jsonArticle.getInt("id")
            scoutSection.name = jsonArticle.getString("name")
            return scoutSection
        }
    }

}