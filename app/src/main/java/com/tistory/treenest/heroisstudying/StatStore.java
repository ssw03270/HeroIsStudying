package com.tistory.treenest.heroisstudying;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class StatStore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_store);

        final DBHelper MainDB = new DBHelper(getApplicationContext(), "Main.db", null, 1);

        final TextView Stat_Text = (TextView)findViewById(R.id.Stat_Text);
        final TextView Stat_Text2 = (TextView)findViewById(R.id.Stat_Text2);

        final TextView STR_USE_Text = (TextView)findViewById(R.id.STR_USE_Text);
        final TextView INT_USE_Text = (TextView)findViewById(R.id.INT_USE_Text);
        final TextView HP_USE_Text = (TextView)findViewById(R.id.HP_USE_Text);
        final TextView DEX_USE_Text = (TextView)findViewById(R.id.DEX_USE_Text);

        final ImageButton Dungeon_Btn = (ImageButton) findViewById(R.id.Dungeon_Btn);
        final ImageButton Store_Btn = (ImageButton)findViewById(R.id.Store_Btn);
        final ImageButton Main_Btn = (ImageButton)findViewById(R.id.Main_btn);
        final ImageButton Log_Btn = (ImageButton)findViewById(R.id.Log_Btn);
        final ImageButton Setting_Btn = (ImageButton )findViewById(R.id.Setting_Btn);

        final Button Enter_Btn1 = (Button)findViewById(R.id.Enter_Btn1);
        final Button Enter_Btn2 = (Button)findViewById(R.id.Enter_Btn2);
        final Button Enter_Btn3 = (Button)findViewById(R.id.Enter_Btn3);
        final Button Enter_Btn4 = (Button)findViewById(R.id.Enter_Btn4);

        final Button Clear_Btn = (Button)findViewById(R.id.Clear_Btn);

        AdView mAdView;

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.FULL_BANNER);
        adView.setAdUnitId("ca-app-pub-9082043936834188/6919972628");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        STR_USE_Text.setText("근력 (" + MainDB.getValue("STR_BUY") +" 스탯)");
        INT_USE_Text.setText("지력 (" + MainDB.getValue("INT_BUY") +" 스탯)");
        HP_USE_Text.setText("체력 (" + MainDB.getValue("HP_BUY") +" 스탯)");
        DEX_USE_Text.setText("민첩 (" + MainDB.getValue("DEX_BUY") +" 스탯)");

        Stat_Text.setText("보유 스탯 : " + MainDB.getValue("STAT"));
        Stat_Text2.setText("사용 스탯 : " + MainDB.getValue("STAT_USE"));

        Clear_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Clear_Btn.setVisibility(View.INVISIBLE);
            }
        });

        Enter_Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(MainDB.getValue("STAT") >= MainDB.getValue("STR_BUY")){
                    Clear_Btn.setVisibility(View.VISIBLE);
                    Clear_Btn.setText("구매 성공!!\n근력이 1 상승!!");

                    MainDB.update("STR", MainDB.getValue("STR") + 1);
                    MainDB.update("STAT", MainDB.getValue("STAT") - MainDB.getValue("STR_BUY"));
                    MainDB.update("STAT_USE",MainDB.getValue("STAT_USE") + MainDB.getValue("STR_BUY"));

                    Stat_Text.setText("보유 스탯 : " + MainDB.getValue("STAT"));
                    Stat_Text2.setText("사용 스탯 : " + MainDB.getValue("STAT_USE"));

                    MainDB.update("STR_BUY", MainDB.getValue("STR_BUY") + 1);
                    STR_USE_Text.setText("근력 (" + MainDB.getValue("STR_BUY") +" 스탯)");
                }else{
                    Clear_Btn.setVisibility(View.VISIBLE);
                    Clear_Btn.setText("보유 스탯이\n부족합니다.");

                }
            }
        });
        Enter_Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(MainDB.getValue("STAT") >= MainDB.getValue("INT_BUY")){
                    Clear_Btn.setVisibility(View.VISIBLE);
                    Clear_Btn.setText("구매 성공!!\n지력이 1 상승!!");

                    MainDB.update("INT", MainDB.getValue("INT") + 1);
                    MainDB.update("STAT", MainDB.getValue("STAT") - MainDB.getValue("INT_BUY"));
                    MainDB.update("STAT_USE",MainDB.getValue("STAT_USE") + MainDB.getValue("INT_BUY"));

                    Stat_Text.setText("보유 스탯 : " + MainDB.getValue("STAT"));
                    Stat_Text2.setText("사용 스탯 : " + MainDB.getValue("STAT_USE"));

                    MainDB.update("INT_BUY", MainDB.getValue("INT_BUY") + 1);
                    INT_USE_Text.setText("지력 (" + MainDB.getValue("INT_BUY") +" 스탯)");
                }else{
                    Clear_Btn.setVisibility(View.VISIBLE);
                    Clear_Btn.setText("보유 스탯이\n부족합니다.");

                }
            }
        });
        Enter_Btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(MainDB.getValue("STAT") >= MainDB.getValue("HP_BUY")){
                    Clear_Btn.setVisibility(View.VISIBLE);
                    Clear_Btn.setText("구매 성공!!\n체력이 1 상승!!");

                    MainDB.update("HP", MainDB.getValue("HP") + 1);
                    MainDB.update("STAT", MainDB.getValue("STAT") - MainDB.getValue("HP_BUY"));
                    MainDB.update("STAT_USE",MainDB.getValue("STAT_USE") + MainDB.getValue("HP_BUY"));

                    Stat_Text.setText("보유 스탯 : " + MainDB.getValue("STAT"));
                    Stat_Text2.setText("사용 스탯 : " + MainDB.getValue("STAT_USE"));

                    MainDB.update("HP_BUY", MainDB.getValue("HP_BUY") + 1);
                    HP_USE_Text.setText("체력 (" + MainDB.getValue("HP_BUY") +" 스탯)");
                }else{
                    Clear_Btn.setVisibility(View.VISIBLE);
                    Clear_Btn.setText("보유 스탯이\n부족합니다.");

                }
            }
        });
        Enter_Btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(MainDB.getValue("STAT") >= MainDB.getValue("DEX_BUY")){
                    Clear_Btn.setVisibility(View.VISIBLE);
                    Clear_Btn.setText("구매 성공!!\n민첩이 1 상승!!");

                    MainDB.update("DEX", MainDB.getValue("DEX") + 1);
                    MainDB.update("STAT", MainDB.getValue("STAT") - MainDB.getValue("DEX_BUY"));
                    MainDB.update("STAT_USE",MainDB.getValue("STAT_USE") + MainDB.getValue("DEX_BUY"));

                    Stat_Text.setText("보유 스탯 : " + MainDB.getValue("STAT"));
                    Stat_Text2.setText("사용 스탯 : " + MainDB.getValue("STAT_USE"));

                    MainDB.update("DEX_BUY", MainDB.getValue("DEX_BUY") + 1);
                    DEX_USE_Text.setText("민첩 (" + MainDB.getValue("DEX_BUY") +" 스탯)");
                }else{
                    Clear_Btn.setVisibility(View.VISIBLE);
                    Clear_Btn.setText("보유 스탯이\n부족합니다.");

                }
            }
        });

        Dungeon_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),Dungeon.class);
                Store.Store.finish();finish();                 startActivity(intent);                 overridePendingTransition(0,0);
            }
        });

        Store_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),Store.class);
                Store.Store.finish();finish();                 startActivity(intent);                 overridePendingTransition(0,0);
            }
        });
        Main_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                Store.Store.finish();finish();                 startActivity(intent);                 overridePendingTransition(0,0);
            }
        });
        Log_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), com.tistory.treenest.heroisstudying.Log.class);
                Store.Store.finish();finish();                 startActivity(intent);                 overridePendingTransition(0,0);
            }
        });
        Setting_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),Setting.class);
                Store.Store.finish();finish();                 startActivity(intent);                 overridePendingTransition(0,0);
            }
        });

    }
    @Override
    protected void onPause(){
        super.onPause();

        final Button Clear_Btn = (Button)findViewById(R.id.Clear_Btn);
        Clear_Btn.setVisibility(View.INVISIBLE);
    }
}
