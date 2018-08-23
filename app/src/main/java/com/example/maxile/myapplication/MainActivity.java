package com.example.maxile.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermission();
    }
    public final String[] listPermission = new String[]{
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.INTERNET,
            android.Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.WAKE_LOCK,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.ACCESS_NETWORK_STATE,
            android.Manifest.permission.VIBRATE,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
    };
    private final static int MY_PERMISSIONS_REQUEST_PHONE_STATE = 999;//any number
    public List<String> listRequest;
    private void requestPermission(){
        listRequest = new ArrayList<String>();
        for(String permission : listPermission){
            if (ContextCompat.checkSelfPermission(this,
                    permission)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    listRequest.add(permission);
                }
            }
        }
        Log.d("request permission", listRequest.size()+"");
        if (listRequest.size()>0){
            ActivityCompat.requestPermissions(this,
                    listRequest.toArray(new String[0]),
                    MY_PERMISSIONS_REQUEST_PHONE_STATE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_PHONE_STATE: {
                boolean isPass = true;
                if (grantResults.length == permissions.length) {
                    for (int g : grantResults){
                        if(g != PackageManager.PERMISSION_GRANTED){
                            isPass = false;
                        }
                    }
                }
                if (!isPass){
                    //permission not pass
                }else {
                    //permission pass
                }
                break;
            }
        }
    }
}
