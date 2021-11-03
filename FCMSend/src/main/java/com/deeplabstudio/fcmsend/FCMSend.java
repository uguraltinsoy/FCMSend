package com.deeplabstudio.fcmsend;

import android.os.StrictMode;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FCMSend {
    private static String BASE_URL = "https://fcm.googleapis.com/fcm/send";
    private static String SERVER_KEY = null;

    public static void SetServerKey(String serverKey){
        FCMSend.SERVER_KEY = "key=" + serverKey;
    }

    public static String pushNotification(String token, String title, String message){
        if (SERVER_KEY == null) return "No Server Key";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            JSONObject json = new JSONObject();
            json.put("to", token);
            JSONObject notification = new JSONObject();
            notification.put("title", title);
            notification.put("body", message);
            json.put("notification", notification);

            HttpURLConnection conn = (HttpURLConnection) new URL(BASE_URL).openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json;");
            conn.setRequestProperty("Authorization", SERVER_KEY);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            OutputStream os = conn.getOutputStream();
            os.write(json.toString().getBytes("UTF-8"));
            os.close();
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            in.close();
            conn.disconnect();
            return result;
        } catch (JSONException | IOException e) {
            return e.getMessage();
        }
    }
}
