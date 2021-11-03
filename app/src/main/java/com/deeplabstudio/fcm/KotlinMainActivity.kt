package com.deeplabstudio.fcm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deeplabstudio.fcmsend.FCMSend

class KotlinMainActivity : AppCompatActivity() {
    private val serverKey = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)

        FCMSend.SetServerKey(serverKey)

        FCMSend.pushNotification(
            "<To Device Token>",
            "<Title>",
            "<Message>"
        )
    }
}