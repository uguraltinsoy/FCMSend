package com.deeplabstudio.fcm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.deeplabstudio.fcmsend.FCMSend;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private EditText mTitle, mMessage;

    private static String serverKey = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // FCMSend Initialization
        FCMSend.SetServerKey(serverKey);

        // Get Device Token
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        String token = task.getResult();
                    }
                });

        // Subscribe To Topic
        FirebaseMessaging.getInstance().subscribeToTopic("<Topic Name>").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                System.out.println("Subscription successful");
            }
        });

        HashMap<String, String> data = new HashMap<>();
        data.put("key1", "data 1");
        data.put("key2", "data 2");
        data.put("key3", "data 3");

        FCMSend.Builder build1 = new FCMSend.Builder("<To Device Token>")
                .setTitle("<Title>")
                .setBody("<Message>")
                .setClickAction("<Action>") // Optional
                .setData(data); // Optional
        build1.send();

        FCMSend.Builder build2 = new FCMSend.Builder("<To Device Token>")
                .setTitle("<Title>")
                .setBody("<Message>")
                .setClickAction("<Action>"); // Optional;
        String result1 = build2.send().Result();

        FCMSend.Builder build3 = new FCMSend.Builder("<Topic Name>", true)
                .setTitle("<Title>")
                .setBody("<Message>")
                .setClickAction("<Action>"); // Optional
        build3.send();

        FCMSend.Builder build4 = new FCMSend.Builder("<Topic Name>", true)
                .setTitle("<Title>")
                .setBody("<Message>")
                .setClickAction("<Action>"); // Optional
        String result2 = build4.send().Result();
    }
}