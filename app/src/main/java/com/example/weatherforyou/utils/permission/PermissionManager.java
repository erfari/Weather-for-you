package com.example.weatherforyou.utils.permission;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.example.weatherforyou.ui.MainActivity;
import com.example.weatherforyou.viewmodel.PermissionViewModel;

public class PermissionManager {

    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    Activity activity;


    public PermissionManager(Activity activity) {
        this.activity = activity;
    }

    public void startGetPermission(){
        if (isPermissionGranted()) {

            PermissionViewModel.getInstance().changePermission(true);
        } else {

            PermissionViewModel.getInstance().changePermission(false);
            requestPermission();
        }
    }

    public boolean isPermissionGranted() {
        boolean isPermissionGranted = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        Log.e("permission", " is permission already granted: " + isPermissionGranted);
        return isPermissionGranted;
    }

    public void requestPermission() {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                Toast.makeText(activity, " Permission  granted", Toast.LENGTH_LONG).show();
                PermissionViewModel.getInstance().changePermission(true);

            }else {

                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    new AlertDialog.Builder(activity)
                            .setTitle("Permission request")
                            .setMessage("i need your boots, clothes and motorcycle")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
                                }
                            })
                            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    PermissionViewModel.getInstance().changePermission(false);
                                }
                            })
                            .create()
                            .show();



                } else {
                    PermissionViewModel.getInstance().changePermission(false);
                    PermissionViewModel.getInstance().isPermanentlyDenied = true;
                }


            }
        }
    }
}
