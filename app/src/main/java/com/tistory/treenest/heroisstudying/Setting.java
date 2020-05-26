package com.tistory.treenest.heroisstudying;

import android.content.Intent;
import android.net.Uri;
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

import java.text.SimpleDateFormat;
import java.util.Date;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        AdView mAdView;

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.FULL_BANNER);
        adView.setAdUnitId("ca-app-pub-9082043936834188/6919972628");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final DBHelper MainDB = new DBHelper(getApplicationContext(), "Main.db", null, 1);
        final DBHelper DayDB = new DBHelper(getApplicationContext(), "Day.db", null, 1);
        final DBHelper WeoponDB = new DBHelper(getApplicationContext(), "Weopon.db", null, 1);
        final DBHelper HeadDB = new DBHelper(getApplicationContext(), "Head.db", null, 1);
        final DBHelper TopDB = new DBHelper(getApplicationContext(), "Top.db", null, 1);
        final DBHelper BottomDB = new DBHelper(getApplicationContext(), "Bottom.db", null, 1);
        final DBHelper ShoesDB = new DBHelper(getApplicationContext(), "Shoes.db", null, 1);
        final DBHelper LogDB = new DBHelper(getApplicationContext(), "Log.db", null, 1);

        final ImageButton Dungeon_Btn = (ImageButton) findViewById(R.id.Dungeon_Btn);
        final ImageButton Store_Btn = (ImageButton) findViewById(R.id.Store_Btn);
        final ImageButton Main_Btn = (ImageButton) findViewById(R.id.Main_btn);
        final ImageButton Log_Btn = (ImageButton) findViewById(R.id.Log_Btn);
        final ImageButton Setting_Btn = (ImageButton) findViewById(R.id.Setting_Btn);

        final Button Enter_Btn1 = (Button)findViewById(R.id.Enter_Btn1);
        final Button Enter_Btn2 = (Button)findViewById(R.id.Enter_Btn2);
        final Button Enter_Btn4 = (Button)findViewById(R.id.Enter_Btn4);
        final Button Enter_Btn5 = (Button)findViewById(R.id.Enter_Btn5);

        final TextView Start_Text = (TextView)findViewById(R.id.Start_Text);

        Start_Text.setText(DayDB.getValue_Day("DAY"));

        if(MainDB.getValue("NOTIFICATION") == 1){
            Enter_Btn1.setText("꺼짐");
        }else{
            Enter_Btn1.setText("켜짐");
        }

        Enter_Btn1.setOnClickListener(new View.OnClickListener() {//알림 설정
            @Override
            public void onClick(View v){
                if(MainDB.getValue("NOTIFICATION") == 0){
                    Toast.makeText(Setting.this, "소리 꺼짐", Toast.LENGTH_SHORT).show();
                    Enter_Btn1.setText("꺼짐");
                    MainDB.update("NOTIFICATION",1);
                }else{
                    Toast.makeText(Setting.this, "소리 켜짐", Toast.LENGTH_SHORT).show();
                    Enter_Btn1.setText("켜짐");
                    MainDB.update("NOTIFICATION",0);
                }


            }
        });
        Enter_Btn2.setOnClickListener(new View.OnClickListener() {//기록 삭제
            @Override
            public void onClick(View v){
                Toast.makeText(Setting.this, "기록 삭제 완료", Toast.LENGTH_SHORT).show();
                LogDB.delete_Log();
            }
        });
        Enter_Btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse( "https://treenest.tistory.com/"));
                startActivity(intent);
            }
        });
        Enter_Btn5.setOnClickListener(new View.OnClickListener() {//기록 삭제
            @Override
            public void onClick(View v){
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"ttd8591@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "<용사는 공부중 - 버그신고>");
                email.putExtra(Intent.EXTRA_TEXT, "");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Send Mail Using :"));

            }
        });


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
        Log_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), Log.class);finish();                 startActivity(intent);                 overridePendingTransition(0,0);
            }
        });

    }
}//기록 삭제, 캐릭터 초기화, 알람 설정,
