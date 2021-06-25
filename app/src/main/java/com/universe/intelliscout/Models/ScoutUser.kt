package ipca.example.projetosemestre.Models

import org.json.JSONObject

class ScoutUser {

    var id: Int? = null
    var name: String? = null
    var birth: String? = null //Analisar
    var gender: Int? = null  //Analisar
    var phone: String? = null
    var address: String? = null
    var active: Int? = null //Mudar para Int na base de dados
    var nin: String? = null
    var phoneEE: String? = null
    var urlImg: String? = null
    var idScoutLogin: Int? = null
    var idScoutTeam: Int? = null
    var idLocal: Int? = null

    constructor()

    constructor(
        id: Int?,
        name: String?,
        birth: String?,
        gender: Int?,
        phone: String?,
        address: String?,
        active: Int?,
        nin: String?,
        phoneEE: String?,
        urlImg: String?,
        idScoutLogin: Int?,
        idScoutTeam: Int?,
        idLocal: Int?
    ) {

        this.id = id
        this.name = name
        this.birth = birth
        this.gender = gender
        this.phone = phone
        this.address = address
        this.active = active
        this.nin = nin
        this.phoneEE = phoneEE
        this.urlImg = urlImg
        this.idScoutLogin = idScoutLogin
        this.idScoutTeam = idScoutTeam
        this.idLocal = idLocal

    }

    fun toJson(): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("name", name)
        jsonObject.put("birth", birth)
        jsonObject.put("gender", gender)
        jsonObject.put("phone", phone)
        jsonObject.put("address", address)
        jsonObject.put("active", active)
        jsonObject.put("nin", nin)
        jsonObject.put("phone_ee", phoneEE)
        jsonObject.put("url_img", urlImg)
        jsonObject.put("scout_login", idScoutLogin)
        jsonObject.put("scout_team", idScoutTeam)
        jsonObject.put("id_local", idLocal)

        return jsonObject
    }

    companion object {

        fun fromJson(jsonArticle: JSONObject): ScoutUser {
            val scoutUser = ScoutUser()

            scoutUser.id = jsonArticle.getInt("id_scout_user")
            scoutUser.name = jsonArticle.getString("name_scout_user")
            scoutUser.birth = jsonArticle.getString("birth_scout_user")
            scoutUser.gender = jsonArticle.getInt("gender_scout_user")
            scoutUser.phone = jsonArticle.getString("phone_scout_user")
            scoutUser.address = jsonArticle.getString("address_scout_user")
            scoutUser.active = jsonArticle.getInt("active_scout_user")
            scoutUser.nin = jsonArticle.getString("nin_scout_user")
            scoutUser.phoneEE = jsonArticle.getString("phone_ee_scout_user")
            scoutUser.urlImg = jsonArticle.getString("url_img_scout_user")
            scoutUser.idScoutLogin = jsonArticle.getInt("id_scout_login")
            scoutUser.idScoutTeam = jsonArticle.getInt("id_scout_section")
            scoutUser.idLocal = jsonArticle.getInt("id_local")

            return scoutUser
        }
    }

}