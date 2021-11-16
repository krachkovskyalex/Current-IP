package com.krachkovsky.mycurrentip.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.krachkovsky.mycurrentip.R
import com.krachkovsky.mycurrentip.api.IPApiRequerest
import com.krachkovsky.mycurrentip.util.Constants.BASE_URL
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        get_ip_bnt.setOnClickListener {
            getIP()
        }
    }

    private fun getIP() {
        ip_text.visibility = View.INVISIBLE
        progress_bar.visibility = View.VISIBLE


        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IPApiRequerest::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getIP().awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    Log.d("IPTAG", data.ip)

                    withContext(Dispatchers.Main) {
                        ip_text.visibility = View.VISIBLE
                        progress_bar.visibility = View.GONE

                        ip_text.text = data.ip
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "Something went wrong...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}