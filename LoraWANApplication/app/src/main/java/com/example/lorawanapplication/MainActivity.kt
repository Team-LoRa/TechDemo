package com.example.lorawanapplication

import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.teamlora.loralibrary.LoRaMessenger
import com.teamlora.loralibrary.LogcatStart



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        val IP = findViewById<EditText>(R.id.message)



        val threadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(threadPolicy)

        //Call to the Logcat saying the app has started
        LogcatStart()

        // Initialize the LoRaMessenger object
        button.setOnClickListener {
            /* TODO: Improve the implementation below so that the developer does not need to call readEncodingTable() themselves */

            // Read the encoding table from assets
            val jsonString: String =
                application.assets.open("encoding_table.json").bufferedReader().use {
                    it.readText()
                }

            // Pass the encoding table to the messenger
            val messenger = LoRaMessenger("OpenCellID", jsonString )

            val parameters: Array<Any> = arrayOf( 12.314, 13.564, 46, 2367, 85476, 1348493027 )

            messenger.sendLoRaMessage("measure/add", parameters )
        }

    }

}
