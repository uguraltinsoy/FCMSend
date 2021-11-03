package com.deeplabstudio.fcm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.deeplabstudio.fcmsend.FCMSend

class KotlinMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)

        FCMSend.SetServerKey(this, "<Server Key>").pushNotification(
            "<To Device Token>",
            "<Title>",
            "<Message>"
        )
    }
}