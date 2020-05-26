package com.tistory.treenest.heroisstudying;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class Log extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        AdView mAdView;

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.FULL_BANNER);
        adView.setAdUnitId("ca-app-pub-9082043936834188/6919972628");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final DBHelper LogDB = new DBHelper(getApplicationContext(), "Log.db", null, 1);

        final TextView Log = findViewById(R.id.Log);

        final ImageButton Dungeon_Btn = findViewById(R.id.Dungeon_Btn);
        final ImageButton Store_Btn = findViewById(R.id.Store_Btn);
        final ImageButton Main_Btn = findViewById(R.id.Main_btn);
        final ImageButton Log_Btn = findViewById(R.id.Log_Btn);
        final ImageButton Setting_Btn = findViewById(R.id.Setting_Btn);

        Log.setText(LogDB.getResult_Log());

        Dungeon_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),Dungeon.class);finish();                 startActivity(intent);                 overridePendingTransition(0,0);
            }
        });

        Store_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),Store.class);finish();                 startActivity(intent);                 overridePendingTransition(0,0);
            }
        });
        Main_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);finish();                 startActivity(intent);                 overridePendingTransition(0,0);
            }
        });
        Setting_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),Setting.class);finish();                 startActivity(intent);                 overridePendingTransition(0,0);
            }
        });

    }
}
