package com.universe.intelliscout.Activities

import com.universe.intelliscout.Models.Activity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject


object ActivitiesRequest {

    const val url = "http://intelliscout.ml:60000/activity/"

    /*
        This function return a List of activities
     */
    fun getAllActivities(callBack: (List<Activity>) -> Unit) {

        val activities: MutableList<Activity> = arrayListOf()

        val request = Request.Builder().url(url).get().build()


        OkHttpClient().newCall(request).execute().use { response ->

            val activityJsonArrayStr: String = response.body!!.string()
            val activityJsonArray = JSONArray(activityJsonArrayStr)

            for (index in 0 until activityJsonArray.length()) {
                val jsonArticle = activityJsonArray.get(index) as JSONObject
                val activity = Activity.fromJson(jsonArticle)
                activities.add(activity)
            }

            callBack(activities)
        }
    }

    fun getActivity(id: Int): Activity {

        var activity: Activity? = null
        val request = Request.Builder().url(url + "$id").build()

        OkHttpClient().newCall(request).execute().use { response ->

            val jsonArrayStr: String = response.body!!.string()
            val jsonArray = JSONArray(jsonArrayStr)

            for (index in 0 until jsonArray.length()) {
                val jsonArticle = jsonArray.get(index) as JSONObject
                activity = Activity.fromJson(jsonArticle)
            }

            return activity!!
        }
    }


    fun addActivity(activity: Activity) {

        val requestBody = activity.toJson().toString()
            .toRequestBody("application/json".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        OkHttpClient().newCall(request).execute().use {}

    }

    fun editActivity(activity: Activity) {

        val requestBody = activity.toJson().toString()
            .toRequestBody("application/json".toMediaTypeOrNull())

        val request = Request.Builder()
            .url(url + "{activity.id}")
            .put(requestBody)
            .build()

        OkHttpClient().newCall(request).execute().use {}

    }

    fun removeActivity(idActivity: Int) {

        val request = Request.Builder()
            .url(url + "$idActivity")
            .delete()
            .build()

        OkHttpClient().newCall(request).execute().use { }

    }
}

