package com.tistory.treenest.heroisstudying;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class Store extends AppCompatActivity {

    public static Activity Store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        AdView mAdView;

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.FULL_BANNER);
        adView.setAdUnitId("ca-app-pub-9082043936834188/6919972628");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Store = Store.this;

        final DBHelper MainDB = new DBHelper(getApplicationContext(), "Main.db", null, 1);

        final TextView Stat_Text = findViewById(R.id.Stat_Text);
        final TextView Stat_Text2 = findViewById(R.id.Stat_Text2);

        final ImageButton Dungeon_Btn = findViewById(R.id.Dungeon_Btn);
        final ImageButton Store_Btn = findViewById(R.id.Store_Btn);
        final ImageButton Main_Btn = findViewById(R.id.Main_btn);
        final ImageButton Log_Btn = findViewById(R.id.Log_Btn);
        final ImageButton Setting_Btn = findViewById(R.id.Setting_Btn);

        final Button Enter_Btn1 = findViewById(R.id.Enter_Btn1);
        final Button Enter_Btn2 = findViewById(R.id.Enter_Btn2);
        final Button Enter_Btn3 = findViewById(R.id.Enter_Btn3);
        final Button Enter_Btn4 = findViewById(R.id.Enter_Btn4);
        final Button Enter_Btn5 = findViewById(R.id.Enter_Btn5);
        final Button Enter_Btn6 = findViewById(R.id.Enter_Btn6);

        final Button Clear_Btn = findViewById(R.id.Clear_Btn);

        Stat_Text.setText("보유 스탯 : " + MainDB.getValue("STAT"));
        Stat_Text2.setText("사용 스탯 : " + MainDB.getValue("STAT_USE"));

        Enter_Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),StatStore.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
        Enter_Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),WeoponStore.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
        Enter_Btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),HeadStore.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
        Enter_Btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),TopStore.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
        Enter_Btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),BottomStore.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
        Enter_Btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),ShoesStore.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });


        Dungeon_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),Dungeon.class);finish();                 startActivity(intent);                 overridePendingTransition(0,0);
            }
        });
        Main_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);finish();                 startActivity(intent);                 overridePendingTransition(0,0);
            }
        });
        Log_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), com.tistory.treenest.heroisstudying.Log.class);finish();                 startActivity(intent);                 overridePendingTransition(0,0);
            }
        });
        Setting_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),Setting.class);finish();                 startActivity(intent);                 overridePendingTransition(0,0);
            }
        });

    }
    @Override
    protected void onResume(){
        final TextView Stat_Text = findViewById(R.id.Stat_Text);
        final TextView Stat_Text2 = findViewById(R.id.Stat_Text2);

        final DBHelper MainDB = new DBHelper(getApplicationContext(), "Main.db", null, 1);

        Stat_Text.setText("보유 스탯 : " + MainDB.getValue("STAT"));
        Stat_Text2.setText("사용 스탯 : " + MainDB.getValue("STAT_USE"));

        super.onResume();
    }
}
