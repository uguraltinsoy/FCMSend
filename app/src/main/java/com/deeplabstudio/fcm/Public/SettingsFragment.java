package com.deeplabstudio.fcm.Public;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.deeplabstudio.fcm.R;

public class SettingsFragment extends DialogFragment {
    private Context mContext;
    public SettingsFragmentListener settingsFragmentListener;
    private EditText mServerKey;
    private Button mSave;

    public SettingsFragment(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        mServerKey = rootView.findViewById(R.id.mServerKey);
        mSave = rootView.findViewById(R.id.mSave);

        mSave.setOnClickListener(view -> {
            if (!mServerKey.getText().toString().equals("")){
                settingsFragmentListener.onDismis(mServerKey.getText().toString().trim());
                dismiss();
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        settingsFragmentListener = (SettingsFragmentListener) context;
    }

    public interface SettingsFragmentListener {
        void onDismis(String serverKey);
    }

    @Override
    public int getTheme() {
        return R.style.DialogTheme;
    }
}