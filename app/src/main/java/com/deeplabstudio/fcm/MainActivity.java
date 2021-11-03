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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Device Token

        /*FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }

                        String token = task.getResult();
                        System.out.println("TOKEN " + token);
                    }
                });*/


        mTitle = findViewById(R.id.mTitle);
        mMessage = findViewById(R.id.mMessage);

        String toDeviceToken = "f6Jkc8IbR1SddDxaQET3Um:APA91bEkK4MqVZ2nl0CGqX5VPdsa_YltiEZda-IVTQdcx_gE-e8TvYV0NztIhjH-y4BXoGL5Xuu_0CLUq89Ilm0ITxY6yFt-41wEswRocVnd0zJPZUEde5NgwT94OEUVUkMZDIGAgYyc";
        String serverKey = "AAAA0mTPblU:APA91bGbOBbo2zCSUxkc68CukNgqoX_36PoSiIKqOjZoz3mo-BLvpAeLYRT0ror50Zws3ZoUlKklJdNbbsUtc6ACEM1L18HdDsGCtVrh3pytnXr4T1i9XLynZtvul1LWjoG0BrIb5u49";
        findViewById(R.id.mSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = mTitle.getText().toString().trim();
                String message = mMessage.getText().toString().trim();
                if (!title.equals("") && !message.equals("")){
                    FCMSend.SetServerKey(MainActivity.this, serverKey).pushNotification(
                            toDeviceToken,
                            title,
                            message
                    );
                }
            }
        });

    }
}