package com.deeplabstudio.fcm.Public;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.deeplabstudio.fcm.R;
import com.deeplabstudio.fcmsend.FCMSend;

import org.json.JSONException;
import org.json.JSONObject;

public class DeviceSendFragment extends Fragment {
    private EditText mToken, mTitle, mBody;
    private Button mSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_device_send, container, false);

        mToken = rootView.findViewById(R.id.mToken);
        mTitle = rootView.findViewById(R.id.mTitle);
        mBody = rootView.findViewById(R.id.mBody);
        mSend = rootView.findViewById(R.id.mSend);

        mSend.setOnClickListener(view -> {
            String token = mToken.getText().toString().trim();
            String title = mTitle.getText().toString().trim();
            String body = mBody.getText().toString().trim();
            if (!token.equals("") && !title.equals("") && !body.equals("")) {
                FCMSend.Builder build = new FCMSend.Builder(token)
                        .setTitle(title)
                        .setBody(body);
                String result = build.send().Result();
                Toast.makeText(getActivity(), ChekSuccess(result), Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }

    private String ChekSuccess(String result) {
        try {
            JSONObject object = new JSONObject(result);
            String success = object.getString("success");
            if (success.equals("1")) {
                return "Success";
            } else if (success.equals("0")) {
                return "Unsuccessful";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "Unsuccessful";
    }
}