package com.universe.intelliscout.Utils

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject

object GlobalRequests {

    const val URL = "http://intelliscout.ml:60000/local"

    fun getAllDistricts(callBack: (List<String>) -> Unit) {

        val locals: MutableList<String> = arrayListOf()
        val request = Request.Builder().url(URL).get().build()

        OkHttpClient().newCall(request).execute().use { response ->

            val localJsonArrayStr: String = response.body!!.string()
            val localJsonArray = JSONArray(localJsonArrayStr)

            for (index in 0 until localJsonArray.length()) {
                val jsonArticle = localJsonArray.get(index) as JSONObject
                val local = jsonArticle.getString("district_local").toString()
                locals.add(local)
            }

            callBack(locals)
        }
    }

}