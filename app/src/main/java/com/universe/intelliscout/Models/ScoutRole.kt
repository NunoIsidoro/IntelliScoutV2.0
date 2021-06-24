package ipca.example.projetosemestre.Models

import org.json.JSONObject

class ScoutRole {

    var id: Int? = null
    var name: String? = null
    var descr: String? = null

    constructor()

    constructor(id: Int?, name: String?, descr: String?) {

        this.id = id
        this.name = name
        this.descr = descr

    }

    fun toJson(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("name", name)
        jsonObject.put("descr", descr)
        return jsonObject
    }

    companion object {

        fun fromJson(jsonArticle: JSONObject): ScoutRole {
            val scoutRole = ScoutRole()

            scoutRole.id = jsonArticle.getInt("id")
            scoutRole.name = jsonArticle.getString("name")
            scoutRole.descr = jsonArticle.getString("descr")
            return scoutRole
        }
    }

}