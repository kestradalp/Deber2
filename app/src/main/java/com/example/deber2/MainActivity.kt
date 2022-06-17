package com.example.deber2

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun conServVoll(view: View) {
        /*Hago uso de la clase Volley (Como para este ejemplo solo haré una petición no creo un
        objeto) Creo una nueva cola de peticiones (RequestQueve) Agrego una petición a la cola .add */
        Volley.newRequestQueue(this).add(
            StringRequest(
                Request.Method.GET, "https://gorest.co.in/public/v1/users",
                { response ->
                    var dataString = JSONObject(response.toString()).optString("data")
                    findViewById<TextView>(R.id.lblUsuarios).text =
                        parcJson(JSONArray(dataString.toString()))
                },
                { findViewById<TextView>(R.id.lblUsuarios).text = "Se produjo un error" })
        )
    }

    fun parcJson(jsonArray: JSONArray): String {
        var finalString: String = ""
        for (i in 0 until jsonArray.length()) {
            var name = jsonArray.getJSONObject(i).getString("name").toString()
            var id = jsonArray.getJSONObject(i).getString("id").toString()
            var email = jsonArray.getJSONObject(i).getString("email").toString()
            var gender = jsonArray.getJSONObject(i).getString("gender").toString()
            var status = jsonArray.getJSONObject(i).getString("status").toString()
            finalString += " ID:$id \n Nombre:$name \n Email:$email \n Género: $gender \n Status:$status \n\n\n"
        }
        return finalString
    }

}