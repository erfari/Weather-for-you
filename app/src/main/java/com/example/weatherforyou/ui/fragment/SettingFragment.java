package com.example.weatherforyou.ui.fragment;


import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.weatherforyou.App;
import com.example.weatherforyou.R;
import com.example.weatherforyou.ui.MainActivity;
import com.example.weatherforyou.ui.PushWorker;

import java.util.concurrent.TimeUnit;

public class SettingFragment extends PreferenceFragmentCompat {

    private static final int REQUEST_CODE_PERMISSION_READ_CONTACTS = 1;
    PeriodicWorkRequest myWorkRequest;

    private SwitchPreference switchPreference;
    private DropDownPreference pushPreference;
    private Preference geoPreference;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        switchPreference = (SwitchPreference) getPreferenceManager().findPreference("enable_night_theme");

        pushPreference = (DropDownPreference) getPreferenceManager().findPreference("dropdown");

        //new
        geoPreference = (Preference) getPreferenceManager().findPreference("geolocation");

        geoPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getActivity().getPackageName()));

                myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
                myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(myAppSettings);
                return false;
            }
        });

        switchPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (switchPreference.isChecked()) {
                    Log.i("MYLOG", "unchecked");
                    getActivity().recreate();

                } else {
                    Log.i("MYLOG", "checked");
                    getActivity().recreate();
                }
                return true;

            }
        });


        pushPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (newValue.toString().equals("0")) {
                    if (myWorkRequest != null) {
                        WorkManager.getInstance().cancelWorkById(myWorkRequest.getId());
                    }
                } else {
                    switch (newValue.toString()) {
                        case "60":
//                            myWorkRequest = new PeriodicWorkRequest.Builder(PushWorker.class, 30, TimeUnit.MINUTES).build();
                            myWorkRequest = new PeriodicWorkRequest.Builder(PushWorker.class, 60, TimeUnit.MINUTES, 55, TimeUnit.MINUTES).build();
                            break;
                        case "120":
                            myWorkRequest = new PeriodicWorkRequest.Builder(PushWorker.class, 120, TimeUnit.MINUTES, 115, TimeUnit.MINUTES).build();
                            break;
                        case "180":
                            myWorkRequest = new PeriodicWorkRequest.Builder(PushWorker.class, 180, TimeUnit.MINUTES, 175, TimeUnit.MINUTES).build();
                            break;
                        case "360":
                            myWorkRequest = new PeriodicWorkRequest.Builder(PushWorker.class, 360, TimeUnit.MINUTES, 355, TimeUnit.MINUTES).build();
                            break;
                        case "720":
                            myWorkRequest = new PeriodicWorkRequest.Builder(PushWorker.class, 720, TimeUnit.MINUTES, 715, TimeUnit.MINUTES).build();
                            break;
                    }
                    /**
                     * крашит блад
                     *  java.lang.NullPointerException: Attempt to invoke virtual method 'java.lang.String androidx.work.WorkRequest.getStringId()' on a null object reference
                     *
                     WorkManager.getInstance().enqueueUniquePeriodicWork(
                     "push",
                     ExistingPeriodicWorkPolicy.REPLACE,
                     myWorkRequest
                     );
                     **/
                }
                Log.i("MYLOG", newValue.toString());
                return true;
            }
        });

    }
}
