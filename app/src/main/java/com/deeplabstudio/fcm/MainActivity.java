package com.deeplabstudio.fcm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.deeplabstudio.fcmsend.FCMSend;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

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
                        System.out.println("TOKEN " + token);
                    }
                });


        mTitle = findViewById(R.id.mTitle);
        mMessage = findViewById(R.id.mMessage);

        String toDeviceToken = "";

        findViewById(R.id.mSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mTitle.getText().toString().trim();
                String message = mMessage.getText().toString().trim();
                if (!title.equals("") && !message.equals("")) {
                    String result = FCMSend.pushNotification(
                            toDeviceToken,
                            title,
                            message
                    );
                    System.out.println("RESULT " + result);
                }
            }
        });

    }
}