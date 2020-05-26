package com.tistory.treenest.heroisstudying;

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

public class BottomStore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_store);

        AdView mAdView;

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.FULL_BANNER);
        adView.setAdUnitId("ca-app-pub-9082043936834188/6919972628");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final DBHelper MainDB = new DBHelper(getApplicationContext(), "Main.db", null, 1);
        final DBHelper BottomDB = new DBHelper(getApplicationContext(), "Bottom.db", null, 1);

        final TextView Stat_Text = (TextView)findViewById(R.id.Stat_Text);
        final TextView Stat_Text2 = (TextView)findViewById(R.id.Stat_Text2);

        final ImageButton Dungeon_Btn = (ImageButton) findViewById(R.id.Dungeon_Btn);
        final ImageButton Store_Btn = (ImageButton)findViewById(R.id.Store_Btn);
        final ImageButton Main_Btn = (ImageButton)findViewById(R.id.Main_btn);
        final ImageButton Log_Btn = (ImageButton)findViewById(R.id.Log_Btn);
        final ImageButton Setting_Btn = (ImageButton )findViewById(R.id.Setting_Btn);

        final Button Enter_Btn1 = (Button)findViewById(R.id.Enter_Btn1);
        final Button Enter_Btn2 = (Button)findViewById(R.id.Enter_Btn2);
        final Button Enter_Btn3 = (Button)findViewById(R.id.Enter_Btn3);
        final Button Enter_Btn4 = (Button)findViewById(R.id.Enter_Btn4);
        final Button Enter_Btn5 = (Button)findViewById(R.id.Enter_Btn5);
        final Button Enter_Btn6 = (Button)findViewById(R.id.Enter_Btn6);

        final Button Clear_Btn = (Button)findViewById(R.id.Clear_Btn);

        Stat_Text.setText("보유 스탯 : " + MainDB.getValue("STAT"));
        Stat_Text2.setText("사용 스탯 : " + MainDB.getValue("STAT_USE"));

        Set_Text();

        if(BottomDB.getInt_Bottom(1,2) == 0){

        }

        Clear_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clear_Btn.setVisibility(View.INVISIBLE);
            }
        });

        Enter_Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                int i = 1;
                Bottom(i);
                Set_Text();
            }
        });
        Enter_Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                int i = 2;
                Bottom(i);
                Set_Text();
            }

        });
        Enter_Btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                int i = 3;
                Bottom(i);
                Set_Text();
            }
        });
        Enter_Btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                int i = 4;
                Bottom(i);
                Set_Text();
            }
        });
        Enter_Btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                int i = 5;
                Bottom(i);
                Set_Text();
            }
        });
        Enter_Btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                int i = 6;
                Bottom(i);
                Set_Text();
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
    public void Set_Text(){
        final DBHelper BottomDB = new DBHelper(getApplicationContext(), "Bottom.db", null, 1);

        final Button Enter_Btn1 = (Button)findViewById(R.id.Enter_Btn1);
        final Button Enter_Btn2 = (Button)findViewById(R.id.Enter_Btn2);
        final Button Enter_Btn3 = (Button)findViewById(R.id.Enter_Btn3);
        final Button Enter_Btn4 = (Button)findViewById(R.id.Enter_Btn4);
        final Button Enter_Btn5 = (Button)findViewById(R.id.Enter_Btn5);
        final Button Enter_Btn6 = (Button)findViewById(R.id.Enter_Btn6);

        Enter_Btn1.setText(Enter_Text(BottomDB.getInt_Bottom(1,2)));
        Enter_Btn2.setText(Enter_Text(BottomDB.getInt_Bottom(2,2)));
        Enter_Btn3.setText(Enter_Text(BottomDB.getInt_Bottom(3,2)));
        Enter_Btn4.setText(Enter_Text(BottomDB.getInt_Bottom(4,2)));
        Enter_Btn5.setText(Enter_Text(BottomDB.getInt_Bottom(5,2)));
        Enter_Btn6.setText(Enter_Text(BottomDB.getInt_Bottom(6,2)));
    }

    public String Enter_Text(int i){
        String result = "";
        switch (i){
            case 0:
                result = "구매";
                break;
            case 1:
                result = "장착";
                break;
            case 2:
                result = "해제";
                break;
        }
        return result;
    }

    public void Bottom(int i){

        final DBHelper MainDB = new DBHelper(getApplicationContext(), "Main.db", null, 1);
        final DBHelper BottomDB = new DBHelper(getApplicationContext(), "Bottom.db", null, 1);

        final TextView Stat_Text = (TextView)findViewById(R.id.Stat_Text);
        final TextView Stat_Text2 = (TextView)findViewById(R.id.Stat_Text2);

        final Button Clear_Btn = (Button)findViewById(R.id.Clear_Btn);

        if(BottomDB.getInt_Bottom(i,2) == 0){
            if(BottomDB.getInt_Bottom(i,4) <= MainDB.getValue("STAT")){

                String name = BottomDB.getString_Bottom(i,3);

                Clear_Btn.setVisibility(View.VISIBLE);
                Clear_Btn.setText("구매 성공!!\n" + name);

                BottomDB.update_Bottom(i,1);

                MainDB.update("STAT",MainDB.getValue("STAT") - BottomDB.getInt_Bottom(i,4));
                MainDB.update("STAT_USE",MainDB.getValue("STAT_USE") + BottomDB.getInt_Bottom(i,4));

                Stat_Text.setText("보유 스탯 : " + MainDB.getValue("STAT"));
                Stat_Text2.setText("사용 스탯 : " + MainDB.getValue("STAT_USE"));

            }else{
                Clear_Btn.setVisibility(View.VISIBLE);
                Clear_Btn.setText("보유 스탯이\n부족합니다.");

            }

        }else if(BottomDB.getInt_Bottom(i,2) == 1){
            if(MainDB.getValue("BOTTOM") == 0){
                MainDB.update("BOTTOM",i);

                MainDB.update("STR",MainDB.getValue("STR") + BottomDB.getInt_Bottom(i,5));
                MainDB.update("INT",MainDB.getValue("INT") + BottomDB.getInt_Bottom(i,6));
                MainDB.update("HP",MainDB.getValue("HP") + BottomDB.getInt_Bottom(i,7));
                MainDB.update("DEX",MainDB.getValue("DEX") + BottomDB.getInt_Bottom(i,8));

                BottomDB.update_Bottom(i,2);

            }else{
                int k = MainDB.getValue("BOTTOM");

                MainDB.update("STR",MainDB.getValue("STR") - BottomDB.getInt_Bottom(k,5));
                MainDB.update("INT",MainDB.getValue("INT") - BottomDB.getInt_Bottom(k,6));
                MainDB.update("HP",MainDB.getValue("HP") - BottomDB.getInt_Bottom(k,7));
                MainDB.update("DEX",MainDB.getValue("DEX") - BottomDB.getInt_Bottom(k,8));

                BottomDB.update_Bottom(k,1);

                MainDB.update("BOTTOM",i);

                MainDB.update("STR",MainDB.getValue("STR") + BottomDB.getInt_Bottom(i,5));
                MainDB.update("INT",MainDB.getValue("INT") + BottomDB.getInt_Bottom(i,6));
                MainDB.update("HP",MainDB.getValue("HP") + BottomDB.getInt_Bottom(i,7));
                MainDB.update("DEX",MainDB.getValue("DEX") + BottomDB.getInt_Bottom(i,8));

                BottomDB.update_Bottom(i,2);

            }
        }else if(BottomDB.getInt_Bottom(i,2) == 2){

            MainDB.update("STR",MainDB.getValue("STR") - BottomDB.getInt_Bottom(i,5));
            MainDB.update("INT",MainDB.getValue("INT") - BottomDB.getInt_Bottom(i,6));
            MainDB.update("HP",MainDB.getValue("HP") - BottomDB.getInt_Bottom(i,7));
            MainDB.update("DEX",MainDB.getValue("DEX") - BottomDB.getInt_Bottom(i,8));

            MainDB.update("BOTTOM",0);

            BottomDB.update_Bottom(i,1);

        }
    }
}
