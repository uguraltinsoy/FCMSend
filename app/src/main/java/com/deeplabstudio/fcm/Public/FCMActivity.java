package com.deeplabstudio.fcm.Public;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.deeplabstudio.fcm.R;
import com.deeplabstudio.fcmsend.FCMSend;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

public class FCMActivity extends AppCompatActivity implements SettingsFragment.SettingsFragmentListener {
    private static final String PROFILE_PREFERENCES = "APP_PREFERENCES";
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private ImageView mSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcmactivity);

        sharedPref = getSharedPreferences(PROFILE_PREFERENCES, MODE_PRIVATE);
        editor = sharedPref.edit();

        mSetting = findViewById(R.id.mSetting);
        mSetting.setOnClickListener(view -> {
            SettingsFragment settingsFragment = new SettingsFragment(this);
            settingsFragment.setCancelable(false);
            settingsFragment.show(getSupportFragmentManager(), "Show");
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        if (sharedPref.contains("SERVER_KEY")) {
            FCMSend.SetServerKey(sharedPref.getString("SERVER_KEY", null));
            getSupportFragmentManager().beginTransaction().replace(R.id.mFragment_container, new DeviceSendFragment()).commit();
        } else {
            SettingsFragment settingsFragment = new SettingsFragment(this);
            settingsFragment.setCancelable(false);
            settingsFragment.show(getSupportFragmentManager(), "Show");
        }
    }

    @Override
    public void onDismis(String serverKey) {
        FCMSend.SetServerKey(serverKey);
        editor.putString("SERVER_KEY", serverKey);
        editor.apply();
        getSupportFragmentManager().beginTransaction().replace(R.id.mFragment_container, new DeviceSendFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_profit:
                            selectedFragment = new DeviceSendFragment();
                            break;
                        case R.id.nav_profit_live:
                            selectedFragment = new TopicSendFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.mFragment_container, selectedFragment).commit();
                    return true;
                }
            };

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}