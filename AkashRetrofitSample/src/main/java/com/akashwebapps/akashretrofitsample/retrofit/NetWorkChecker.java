package com.akashwebapps.akashretrofitsample.retrofit;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class NetWorkChecker {
    static NetworkInfo wifi,mobile;
    public static Boolean check(Context c){

        ConnectivityManager cm=(ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);
        try{
            wifi=cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            mobile=cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        if(wifi!=null && wifi.isConnected() && wifi.isAvailable()){

            // Toast.makeText(c, "wifi on", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(mobile!=null && mobile.isAvailable() && mobile.isConnected()){

            // Toast.makeText(c, "mobile data", Toast.LENGTH_SHORT).show();
            return true;
        }

        else{

            Toast.makeText(c, "No Network Connection", Toast.LENGTH_SHORT).show();

            return false;
        }

    }


}
