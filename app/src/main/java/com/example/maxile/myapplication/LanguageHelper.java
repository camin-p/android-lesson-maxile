package com.example.maxile.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

public class LanguageHelper {

    public static void SwitchLanguage(Context c){

        SharedPreferences p = c.getSharedPreferences("com.example.maxile.myapplication", Context.MODE_PRIVATE);
        String currentLanguage = p.getString("CURRENT_LANGUAGE","");

        if (currentLanguage.equals("")){

            Locale current = c.getResources().getConfiguration().locale;
            currentLanguage = current.getLanguage();

        }

        if (currentLanguage.equals("th")){
            currentLanguage = "en";
        } else {
            currentLanguage = "th";
        }

        SharedPreferences.Editor editor = p.edit();
        editor.putString("CURRENT_LANGUAGE", currentLanguage);
        editor.commit();

        Resources res = c.getResources();

        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(currentLanguage));

        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(c,MainActivity.class);
        refresh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        c.startActivity(refresh);

    }
}
