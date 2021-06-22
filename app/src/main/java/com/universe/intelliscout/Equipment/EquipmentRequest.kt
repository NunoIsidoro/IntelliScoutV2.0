package com.universe.intelliscout.Equipment

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.universe.intelliscout.Models.Login
import com.universe.intelliscout.Models.Instruction
import com.universe.intelliscout.Models.Equipment
import ipca.example.projetosemestre.Models.*
import org.jetbrains.anko.doAsync
import org.json.JSONArray

class EquipmentRequest {

    private var queue: RequestQueue? = null

    //Function responsible for the account register: POST
    fun register(context: Context, user: Login, registerEvent: ((Boolean) -> Unit)) {

        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObjectRequest = object : JsonObjectRequest(
                Method.POST,
                BASE_API + AUTHENTIC + REGISTER,
                user.toJson(),
                Response.Listener {
                    Log.d("Requests", it.toString())

                    Log.d("auth", it.toString())


                    if (it.getBoolean("auth"))
                        registerEvent.invoke(true)
                    else
                        registerEvent.invoke(false)


                }, Response.ErrorListener {

                    registerEvent.invoke(false)
                    Log.d("Requests", it.toString())

                }


            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(jsonObjectRequest)
        }
    }

    //Function responsible for the account login: POST
    fun login(context: Context, user: Login, loginEvent: ((Boolean) -> Unit)) {

        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObjectRequest = object : JsonObjectRequest(
                Method.POST,
                BASE_API + AUTHENTIC + LOGIN,
                user.toJson(),
                Response.Listener {

                    Log.d("Requests", it.toString())

                    Log.d("auth", it.toString())


                    if (it.getBoolean("auth"))
                        loginEvent.invoke(true)
                    else
                        loginEvent.invoke(false)


                }, Response.ErrorListener {

                    Log.d("Requests", it.toString())
                    loginEvent.invoke(false)
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(jsonObjectRequest)
        }
    }

    //Function responsible for receive the login account with the gmail selected: GET
    fun getLoginByGmail(
        context: Context,
        gmail: String,
        getLoginByGmailEvent: ((JSONArray?) -> Unit)
    ) {

        println(gmail)
        doAsync {
            queue = Volley.newRequestQueue(context)
            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + AUTHENTIC + "/gmail/$gmail",
                Response.Listener {

                    Log.d("Request", it.toString())
                    getLoginByGmailEvent.invoke(JSONArray(it))

                }, Response.ErrorListener {

                    Log.d("Request", it.toString())
                    getLoginByGmailEvent.invoke(null)
                }
            ) {
                override fun getHeaders() = mutableMapOf("Content-Type" to "application/json")
            }
            queue!!.add(stringRequest)
        }
    }

    //Function responsible for receive all the login accounts: GET
    fun getUserById(context: Context, id: Int, getUserByIdEvent: ((JSONArray?) -> Unit)) {

        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + SCOUT_USER + "/${id}",
                Response.Listener {

                    getUserByIdEvent.invoke(JSONArray(it))

                }, Response.ErrorListener {

                    Log.d("VolleyHelper", it.toString())
                    getUserByIdEvent.invoke(null)
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    //Function responsible for receive all the login accounts: GET
    fun getAllLogin(context: Context, getAllLoginEvent: ((JSONArray?) -> Unit)) {

        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + AUTHENTIC,
                Response.Listener {

                    getAllLoginEvent.invoke(JSONArray(it))

                }, Response.ErrorListener {

                    Log.d("VolleyHelper", it.toString())
                    getAllLoginEvent.invoke(null)
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    //Function responsible for delete any login account: DELETE
    fun deleteLogin(context: Context, id: Int, deleteLoginEvent: ((Boolean) -> Unit)) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.DELETE,
                BASE_API + AUTHENTIC + id,
                Response.Listener<String> {
                    deleteLoginEvent.invoke(true)
                    Log.d("VolleyHelper", it.toString())
                }, Response.ErrorListener {
                    deleteLoginEvent.invoke(false)
                    Log.d("VolleyHelper", it.toString())
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }

            queue!!.add(stringRequest)
        }
    }

    //Function responsible for receive all the scout accounts: GET
    fun getAllScoutUsers(context: Context, getAllScoutUsersEvent: ((JSONArray?) -> Unit)) {

        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + SCOUT_USER,
                Response.Listener {

                    getAllScoutUsersEvent.invoke(JSONArray(it))

                }, Response.ErrorListener {

                    Log.d("VolleyHelper", it.toString())
                    getAllScoutUsersEvent.invoke(null)
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    //Function responsible for the account creation: POST
    fun createScout(context: Context, user: ScoutUser, createScoutEvent: ((Boolean) -> Unit)) {

        doAsync {
            queue = Volley.newRequestQueue(context)

            println(user.toJson())

            val jsonObjectRequest = object : JsonObjectRequest(
                Method.POST,
                BASE_API + SCOUT_USER,
                user.toJson(),
                Response.Listener {

                    createScoutEvent.invoke(true)
                    Log.d("Requests", it.toString())

                }, Response.ErrorListener {

                    createScoutEvent.invoke(false)
                    Log.d("Requests", it.toString())
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(jsonObjectRequest)
        }
    }

    fun updateScoutUser(
        context: Context,
        newUser: ScoutUser,
        updateScoutUserEvent: ((Boolean) -> Unit)
    ) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            println("newUser.tojson() = " + newUser.toJson())


            val jsonObjectRequest = object : JsonObjectRequest(
                Method.PUT,
                BASE_API + SCOUT_USER + "/" + newUser.id,
                newUser.toJson(),
                Response.Listener {

                    updateScoutUserEvent.invoke(true)
                    Log.d("VolleyHelper", it.toString())

                }, Response.ErrorListener {

                    updateScoutUserEvent.invoke(false)
                    Log.d("VolleyHelper", it.toString())

                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(jsonObjectRequest)
        }
    }

    //Function responsible for receive all the scout accounts: GET
    fun getAllEquipments(context: Context, getAllEquipmentsEvent: ((JSONArray?) -> Unit)) {

        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + EQUIPMENT,
                Response.Listener {

                    getAllEquipmentsEvent.invoke(JSONArray(it))

                }, Response.ErrorListener {

                    Log.d("VolleyHelper", it.toString())
                    getAllEquipmentsEvent.invoke(null)
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

    fun updateEquipment(
        context: Context,
        equipment: Equipment,
        updateEquipmentEvent: ((Boolean) -> Unit)
    ) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObjectRequest = object : JsonObjectRequest(
                Method.PUT,
                BASE_API + EQUIPMENT,
                equipment.toJson(),
                Response.Listener {

                    updateEquipmentEvent.invoke(true)

                }, Response.ErrorListener {

                    updateEquipmentEvent.invoke(false)


                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
        }
    }


    //Function responsible for delete any login account: DELETE
    fun deleteEquipment(context: Context, id: Int, deleteEquipmentEvent: ((Boolean) -> Unit)) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.DELETE,
                BASE_API + EQUIPMENT + "/$id",
                Response.Listener {
                    deleteEquipmentEvent.invoke(true)
                }, Response.ErrorListener {
                    deleteEquipmentEvent.invoke(false)
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }

            queue!!.add(stringRequest)
        }
    }


    //Function responsible for the equipment creation: POST
    fun createEquipment(context: Context, equipment: Equipment, createEquipmentEvent: ((Boolean)->Unit)){

        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObjectRequest = object : JsonObjectRequest(
                Method.POST,
                BASE_API + EQUIPMENT,
                equipment.toJson(),
                Response.Listener {

                    if(it.getBoolean("auth"))
                        createEquipmentEvent.invoke(true)
                    else
                        createEquipmentEvent.invoke(false)

                    Log.d("Requests", it.toString())

                },Response.ErrorListener {
                    createEquipmentEvent.invoke(false)
                    Log.d("Requests", it.toString())
                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type","application/json")
                    return map
                }
            }
            queue!!.add(jsonObjectRequest)
        }
    }

    //Function responsible for the instruction creation: POST
    fun createInstruction(context: Context, instruction: Instruction, createInstructionEvent: ((Boolean)->Unit)){

        doAsync {
            queue = Volley.newRequestQueue(context)
            println("instruction.toJson() = ${instruction.toJson()}")

            val jsonObjectRequest = object : JsonObjectRequest(
                Method.POST,
                BASE_API + INSTRUCTION,
                instruction.toJson(),
                Response.Listener {

                    createInstructionEvent.invoke(true)
                    Log.d("Requests", it.toString())

                },Response.ErrorListener {
                    createInstructionEvent.invoke(false)
                    Log.d("Requests", it.toString())
                }
            ){
                override fun getHeaders(): MutableMap<String, String> {
                    val map : MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type","application/json")
                    return map
                }
            }
            queue!!.add(jsonObjectRequest)
        }
    }

    //Function responsible for receive all the scout accounts: GET
    fun getAllInstructions(context: Context, getAllInstructionsEvent: ((JSONArray?)->Unit)) {

        doAsync {
            queue = Volley.newRequestQueue(context)

            val stringRequest = object : StringRequest(
                Method.GET,
                BASE_API + INSTRUCTION,
                Response.Listener {

                    getAllInstructionsEvent.invoke(JSONArray(it))

                }, Response.ErrorListener {

                    getAllInstructionsEvent.invoke(null)
                }
            ) {
                override fun getHeaders(): MutableMap<String, String> {
                    val map: MutableMap<String, String> = mutableMapOf()
                    map.put("Content-Type", "application/json")
                    return map
                }
            }
            queue!!.add(stringRequest)
        }
    }

            //Function responsible for receive all the login accounts: GET
            fun getAllActivities(context: Context, gettAllActivities: ((JSONArray?)->Unit)){

                doAsync {
                    queue = Volley.newRequestQueue(context)

                    val stringRequest = object : StringRequest(
                        Method.GET,
                        BASE_API + ACTIVITIES,
                        Response.Listener {

                            gettAllActivities.invoke(JSONArray(it))

                        },Response.ErrorListener {

                            Log.d("VolleyHelper", it.toString())
                            gettAllActivities.invoke(null)}
                    ) {
                        override fun getHeaders(): MutableMap<String, String> {
                            val map: MutableMap<String, String> = mutableMapOf()
                            map.put("Content-Type", "application/json")
                            return map
                        }
                    }
                    queue!!.add(stringRequest)
                }
            }


            //Function responsible for receive all the login accounts: GET
            fun getLocalById(context: Context, id: Int, getLocalByIdEvent: ((JSONArray?)->Unit)){
                println("passou aqui request 1")
                doAsync {
                    queue = Volley.newRequestQueue(context)
                    println("passou aqui antes do link")
                    val stringRequest = object : StringRequest(
                        Method.GET,
                        BASE_API + DISTRICT + "/1",
                        Response.Listener {
                            println("passou aqui response 1")

                            getLocalByIdEvent.invoke(JSONArray(it))

                        },Response.ErrorListener {
                            println("passou aqui response erro 2")
                            Log.d("VolleyHelper", it.toString())
                            getLocalByIdEvent.invoke(null)}
                    ) {
                        override fun getHeaders(): MutableMap<String, String> {
                            val map: MutableMap<String, String> = mutableMapOf()
                            map.put("Content-Type", "application/json")
                            return map
                        }
                    }
                    queue!!.add(stringRequest)
                }
            }

    companion object{
        //const val BASE_API = "intelliscout.amipca.xyz:60000"
        //const val BASE_API = "http://192.168.1.82:60000"
        const val BASE_API = "http://intelliscout.ml:60000"
        const val AUTHENTIC = "/authentic"
        const val REGISTER = "/register"
        const val LOGIN = "/login"
        const val SCOUT_USER = "/scoutUser"
        const val EQUIPMENT = "/equipment"
        const val INSTRUCTION = "/instruction"
        const val LOCAL = "/locality"
        const val ACTIVITIES = "/activity"
        const val DISTRICT = "/activity/district"


        private var mInstance : Requests? = Requests()

        val instance : Requests
            @Synchronized get(){
                if(null == mInstance){
                    mInstance = Requests()
                }
                return mInstance!!
            }

    }

}