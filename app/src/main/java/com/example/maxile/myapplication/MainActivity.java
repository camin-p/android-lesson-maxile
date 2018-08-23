package com.example.maxile.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.change_language).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Locale current = getResources().getConfiguration().locale;
                String currentL = current.getLanguage();
                String lang;
                if (current.getLanguage()=="th")
                    lang = "en";
                else
                    lang = "th";
                MainActivity.this.setLanguage(lang);
            }
        });
    }
    public void setLanguage(String language){
        Resources res = MainActivity.this.getResources();

        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(language));

        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(MainActivity.this,MainActivity.class);
        refresh.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(refresh);
        finish();
    }
}
