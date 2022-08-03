package com.faith.androidphpmysql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import java.net.URL
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val queue = Volley.newRequestQueue(this)
       /*  val url = "https://android.emobilis.ac.ke/fetch.php"

         val request = JsonObjectRequest(Request.Method.GET, url, null,
             { responseJson ->
                 Log.d("FETCHING", "onCreate: $responseJson")
            val jsonArray=responseJson.getJSONArray("users")
                 for (i in 0 until jsonArray.length())
                 {
                     val userJsonObject=jsonArray.getJSONObject(i)
                     val name = userJsonObject.get("name")
                     val phone=userJsonObject.get("phone")
                     Log.d("USER", "onCreate:$name $phone ")
                 }
             },
             { error ->
                 Log.e("FETCHING", "onCreate:Error while fetching ", error)

             }
         )
         queue.add(request)

*/
         val weatherurl = "https://api.weatherapi.com/v1/current.json?key=f269d6ac5ca5477896375924220208&q=Machakos"
         val weatherRequest = JsonObjectRequest(Request.Method.GET,weatherurl,null,
             {
             mainJsonObject ->
                 val locationObject = mainJsonObject.getJSONObject("location")
                 val city =locationObject.get("name")
                 val region =locationObject.get("region")
                 val country =locationObject.get("country")
                 val updated = mainJsonObject.getJSONObject("current").get("lastly updated")
               //  Log.d("WEATHER", "onCreate: $city $region in $country ")
           //PARSE JOIN
                 
                 val temp = mainJsonObject.getJSONObject("current").get("temp")
                 val windspeed = mainJsonObject.getJSONObject("current").get("wind_kph")
                 val visibility=mainJsonObject.getJSONObject("current").get("vis_km")
                 val humidity=mainJsonObject.getJSONObject("current").get("humidity")


                 /*Log.d("WEATHER", "onCreate: Temperature is $temp C,windspeed is $windspeed,visibility is $visibility,humidity is $humidity")

                 val condition=mainJsonObject.getJSONObject("current").getJSONObject("condition").get("text")
                 Log.d("WEATHER", "onCreate:Condition is $condition ")*/

                 findViewById<TextView>(R.id.address).text=city.toString().trim()
                 findViewById<TextView>(R.id.region).text=region.toString()
                 findViewById<TextView>(R.id.location).text=country.toString()
                 findViewById<TextView>(R.id.updated_at).text=updated.toString()
                 findViewById<TextView>(R.id.status).text=temp.toString()
                 findViewById<TextView>(R.id.wind_kph).text=windspeed.toString()
                 findViewById<TextView>(R.id.vis_km).text=visibility.toString()
                 findViewById<TextView>(R.id.humidity).text=humidity.toString()
             },
             {
                 error ->
                 Log.e("WEATHER", "onCreate: Error while fetching weather data", )
             })
         queue.add((weatherRequest))


    }
}