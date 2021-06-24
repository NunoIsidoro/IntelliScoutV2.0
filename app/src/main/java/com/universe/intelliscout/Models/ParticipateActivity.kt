package ipca.example.projetosemestre.Models

import org.json.JSONObject

class ParticipateActivity {

    var idScoutUser: Int? = null
    var idActivity: Int? = null

    constructor()

    constructor(idScoutUser: Int?, idActivity: Int?) {

        this.idScoutUser = idScoutUser
        this.idActivity = idActivity

    }

    fun toJson(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("idScoutUser", idScoutUser)
        jsonObject.put("idActivity", idActivity)

        return jsonObject
    }

    companion object {

        fun fromJson(jsonArticle: JSONObject): ParticipateActivity {
            val participateActivity = ParticipateActivity()

            participateActivity.idScoutUser = jsonArticle.getInt("idScoutUser")
            participateActivity.idActivity = jsonArticle.getInt("idActivity")
            return participateActivity
        }
    }

}