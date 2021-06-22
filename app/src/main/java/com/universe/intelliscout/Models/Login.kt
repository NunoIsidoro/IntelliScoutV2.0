package ipca.example.projetosemestre.Models

import org.json.JSONObject
import kotlin.math.log

class Login {

    var gmail : String? = null
    var password : String? = null
    var role : Int? = null
    var id : Int? = null

    constructor(){

    }

    constructor(gmail: String?, password: String?, id: Int?, role: Int?){

        this.role = role
        this.gmail = gmail
        this.password = password
        this.id = id
    }

    fun toJson () : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("gmail", gmail)
        jsonObject.put("password", password)
        jsonObject.put("role", role)
        return  jsonObject
    }

    companion object {

        fun fromJson(jsonArticle: JSONObject) : Login {
            val login = Login()

            login.id = jsonArticle.getInt("id_scout_login")
            login.gmail = jsonArticle.getString("email_scout_login")
            login.password = jsonArticle.getString("password_scout_login")
            login.role = jsonArticle.getInt("id_scout_role")
            return login
        }
    }

}