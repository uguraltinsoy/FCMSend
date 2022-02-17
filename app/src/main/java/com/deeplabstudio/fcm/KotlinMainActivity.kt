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

        val build = FCMSend.Builder("<To Device Token>")
                .setTitle("<Title>")
                .setBody("<Message>")
                .setClickAction("<Action>") // Optional
        build.send()

        val build2 = FCMSend.Builder("<To Device Token>")
                .setTitle("<Title>")
                .setBody("<Message>")
                .setClickAction("<Action>") // Optional;
        val result = build2.send().Result()

        val build3 = FCMSend.Builder("<Topic Name>")
                .setTitle("<Title>")
                .setBody("<Message>")
                .setClickAction("<Action>") // Optional
                .isTopic(true)
        build3.send()

        val build4 = FCMSend.Builder("<Topic Name>")
                .setTitle("<Title>")
                .setBody("<Message>")
                .setClickAction("<Action>") // Optional
                .isTopic(true)
        val result2 = build4.send().Result()
    }
}