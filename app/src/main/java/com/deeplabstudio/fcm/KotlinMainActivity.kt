package com.deeplabstudio.fcm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.deeplabstudio.fcmsend.FCMSend
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging


class KotlinMainActivity : AppCompatActivity() {
    private val serverKey = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)

        FCMSend.SetServerKey(serverKey)

        // Get Device Token
        FirebaseMessaging.getInstance().token
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        return@OnCompleteListener
                    }
                    val token = task.result
                })

        // Subscribe To Topic
        FirebaseMessaging.getInstance().subscribeToTopic("<Topic Name>").addOnSuccessListener { println("Subscription successful") }

        val data = HashMap<String, String>()
        data["key1"] = "data 1"
        data["key2"] = "data 2"
        data["key3"] = "data 3"

        val build = FCMSend.Builder("<To Device Token>")
                .setTitle("<Title>")
                .setBody("<Message>")
                .setImage("<Image Url>") // Optional
                .setData(data)
        build.send()

        val build2 = FCMSend.Builder("<To Device Token>")
                .setTitle("<Title>")
                .setBody("<Message>")
                .setImage("<Image Url>") // Optional
                .setData(data)
        val result = build2.send().Result()

        val build3 = FCMSend.Builder("<Topic Name>", true)
                .setTitle("<Title>")
                .setBody("<Message>")
                .setImage("<Image Url>") // Optional
                .setData(data) // Optional
        build3.send()

        val build4 = FCMSend.Builder("<Topic Name>", true)
                .setTitle("<Title>")
                .setBody("<Message>")
                .setImage("<Image Url>") // Optional
                .setData(data) // Optional
        val result2 = build4.send().Result()

        val bundle = intent.extras
        if (bundle != null) {
            val url = bundle.getString("url")
        }
    }
}