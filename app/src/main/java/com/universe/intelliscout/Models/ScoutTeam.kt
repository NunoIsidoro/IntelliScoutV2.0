package ipca.example.projetosemestre.Models

import org.json.JSONObject

class ScoutTeam {

    var id : Int? = null
    var idSection : Int? = null
    var name : String? = null

    constructor(){

    }

    constructor(id: Int?, idSection: Int?, name: String){

        this.id = id
        this.idSection = idSection
        this.name = name

    }

    fun toJson () : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("id_scout_section", idSection)
        jsonObject.put("name", name)
        return  jsonObject
    }

    companion object {

        fun fromJson(jsonArticle: JSONObject) : ScoutTeam {
            val scoutTeam = ScoutTeam()

            scoutTeam.id = jsonArticle.getInt("id")
            scoutTeam.idSection = jsonArticle.getInt("id_scout_section")
            scoutTeam.name = jsonArticle.getString("name")
            return scoutTeam
        }
    }

}