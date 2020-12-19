package com.example.weatherforyou.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.preference.PreferenceManager;

import com.example.weatherforyou.App;
import com.example.weatherforyou.R;
import com.example.weatherforyou.di.module.MainActivityModule;
import com.example.weatherforyou.utils.permission.PermissionManager;
import com.example.weatherforyou.viewmodel.PermissionViewModel;
import com.facebook.stetho.Stetho;

import javax.inject.Inject;


//стартовая activity
public class MainActivity extends AppCompatActivity {

    @Inject
    PermissionManager permissionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setDefaultNightMode();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inject();

        Stetho.initializeWithDefaults(this);

    }

    private void inject(){
        App.AppInstance.getInstance().getMainActivityComponent(this).inject(this);
        App.AppInstance.getInstance().getForecastComponent();
    }



    //переключение темы приложения(с темной на светлую и наоборот)
    private void setDefaultNightMode() {

        getPreferences(MODE_PRIVATE);
        final boolean tgpref = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("enable_night_theme", false);

        if (tgpref) {
            Log.i("MYLOG", "TRUE");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            Log.i("MYLOG", "FALSE2");
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        permissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}