package ipca.example.projetosemestre.Models

import org.json.JSONObject

class Locality {

    var id : Int? = null
    var district : String? = null
    var abbr : String? = null

    constructor(){

    }

    constructor(id: Int?, district: String?, abbr: String?){

        this.id = id
        this.district = district
        this.abbr = abbr

    }

    fun toJson () : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("district", district)
        jsonObject.put("abbr", abbr)
        return  jsonObject
    }

    companion object {

        fun fromJson(jsonArticle: JSONObject) : Locality {
            val zipCode = Locality()

            zipCode.id = jsonArticle.getInt("id_local")
            zipCode.district = jsonArticle.getString("district_local")
            zipCode.abbr = jsonArticle.getString("abbr_local")

            return zipCode
        }
    }

}