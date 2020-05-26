package com.tistory.treenest.heroisstudying;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
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
import java.util.Timer;
import java.util.TimerTask;

public class Tutorial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        AdView mAdView;

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.FULL_BANNER);
        adView.setAdUnitId("ca-app-pub-9082043936834188/6919972628");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final DBHelper MainDB = new DBHelper(getApplicationContext(), "Main.db", null, 1);
        final DBHelper LogDB = new DBHelper(getApplicationContext(), "Log.db", null, 1);

        final TextView Count_Text = (TextView)findViewById(R.id.Count_Text);
        final TextView Count_Text2 = (TextView)findViewById(R.id.CountSum_Text);

        final ImageButton Dungeon_Btn = (ImageButton) findViewById(R.id.Dungeon_Btn);
        final ImageButton Store_Btn = (ImageButton)findViewById(R.id.Store_Btn);
        final ImageButton Main_Btn = (ImageButton)findViewById(R.id.Main_btn);
        final ImageButton Log_Btn = (ImageButton)findViewById(R.id.Log_Btn);
        final ImageButton Setting_Btn = (ImageButton )findViewById(R.id.Setting_Btn);

        final Button Enter_Btn0 = (Button)findViewById(R.id.Enter_Btn0);
        final Button Enter_Btn1 = (Button)findViewById(R.id.Enter_Btn1);
        final Button Enter_Btn2 = (Button)findViewById(R.id.Enter_Btn2);
        final Button Enter_Btn3 = (Button)findViewById(R.id.Enter_Btn3);
        final Button Enter_Btn4 = (Button)findViewById(R.id.Enter_Btn4);
        final Button Enter_Btn5 = (Button)findViewById(R.id.Enter_Btn5);
        final Button Clear_Btn = (Button)findViewById(R.id.Clear_Btn_tutorial);

        final Handler handler = new Handler(){

            public void handleMessage(Message msg){
                if(MainDB.getValue("TIME_CHECK") != 0 && MainDB.getValue("TIME_CHECK") > 10) {

                    if(MainActivity.Timer < 0){
                        Toast.makeText(Tutorial.this, "용사가 훈련(공부)을 성공적으로 마쳤습니다!!!", Toast.LENGTH_SHORT).show();

                        Bitmap mLargeIconForNoti = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round);

                        if(MainDB.getValue("NOTIFICATION") == 0){
                            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                            String channelId = "channel";
                            String channelName = "Channel Name";
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                int importance = NotificationManager.IMPORTANCE_HIGH;
                                NotificationChannel mChannel = new NotificationChannel(
                                        channelId, channelName, importance);
                                notificationManager.createNotificationChannel(mChannel);
                            }

                            NotificationCompat.Builder builder = new NotificationCompat.Builder(Tutorial.this,channelId)
                                    .setSmallIcon(R.mipmap.ic_launcher_round)
                                    .setLargeIcon(mLargeIconForNoti)
                                    .setAutoCancel(true)
                                    .setContentTitle("훈련 완료!!")
                                    .setContentText("보상을 확인하세요~")
                                    .setDefaults(Notification.DEFAULT_ALL);

                            notificationManager.notify(0,builder.build());
                        }else if(MainDB.getValue("NOTIFICATION") == 1){

                            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                            String channelId = "channel";
                            String channelName = "Channel Name";
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                int importance = NotificationManager.IMPORTANCE_HIGH;
                                NotificationChannel mChannel = new NotificationChannel(
                                        channelId, channelName, importance);
                                notificationManager.createNotificationChannel(mChannel);
                            }
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(Tutorial.this,channelId)
                                    .setSmallIcon(R.mipmap.ic_launcher_round)
                                    .setLargeIcon(mLargeIconForNoti)
                                    .setAutoCancel(true)
                                    .setContentTitle("훈련 완료!!")
                                    .setContentText("보상을 확인하세요~")
                                    .setDefaults(Notification.DEFAULT_LIGHTS);

                            notificationManager.notify(0, builder.build());
                        }

                        Clear_Btn.setVisibility(View.VISIBLE);

                        if(MainDB.getValue("TIME_CHECK") == 11){
                            MainDB.update("STAT",MainDB.getValue("STAT") + 1);
                            MainDB.update("TIME_SAVE_DAY",MainDB.getValue("TIME_SAVE_DAY") + 600000);
                            Clear_Btn.setText("훈련 완료!!\n보상 : 1 스탯");
                            LogDB.insert_Log("달리기 (성공)",Now(),600000);
                        }
                        if(MainDB.getValue("TIME_CHECK") == 12){
                            MainDB.update("STAT",MainDB.getValue("STAT") + 2);
                            MainDB.update("TIME_SAVE_DAY",MainDB.getValue("TIME_SAVE_DAY") + 1200000);
                            Clear_Btn.setText("훈련 완료!!\n보상 : 2 스탯");
                            LogDB.insert_Log("팔굽혀펴기 (성공)",Now(),1200000);
                        }
                        if(MainDB.getValue("TIME_CHECK") == 13){
                            MainDB.update("STAT",MainDB.getValue("STAT") + 4);
                            MainDB.update("TIME_SAVE_DAY",MainDB.getValue("TIME_SAVE_DAY") + 1800000);
                            Clear_Btn.setText("훈련 완료!!\n보상 : 3 스탯");
                            LogDB.insert_Log("윗몸일으키기 (성공)",Now(),1800000);
                        }
                        if(MainDB.getValue("TIME_CHECK") == 14){
                            MainDB.update("STAT",MainDB.getValue("STAT") + 8);
                            MainDB.update("TIME_SAVE_DAY",MainDB.getValue("TIME_SAVE_DAY") + 2400000);
                            Clear_Btn.setText("훈련 완료!!\n보상 : 4 스탯");
                            LogDB.insert_Log("허수아비 베기 (성공)",Now(),2400000);
                        }
                        if(MainDB.getValue("TIME_CHECK") == 15){
                            MainDB.update("STAT",MainDB.getValue("STAT") + 16);
                            MainDB.update("TIME_SAVE_DAY",MainDB.getValue("TIME_SAVE_DAY") + 3000000);
                            Clear_Btn.setText("훈련 완료!!\n보상 : 5 스탯");
                            LogDB.insert_Log("1대 1 대련 (성공)",Now(),3000000);
                        }
                        if(MainDB.getValue("TIME_CHECK") == 16){
                            MainDB.update("STAT",MainDB.getValue("STAT") + 32);
                            MainDB.update("TIME_SAVE_DAY",MainDB.getValue("TIME_SAVE_DAY") + 3600000);
                            Clear_Btn.setText("훈련 완료!!\n보상 : 6 스탯");
                            LogDB.insert_Log("명상 (성공)",Now(),3600000);
                        }

                        Count_Text2.setText(MainDB.getValue("TIME_SAVE_DAY")/3600000 + "시간 " + MainDB.getValue("TIME_SAVE_DAY")%3600000/60000 + "분");

                        MainActivity.Timer = 0;
                        MainDB.update("TIME_CHECK",0);

                        Enter_Btn0.setText("도전");
                        Enter_Btn1.setText("도전");
                        Enter_Btn2.setText("도전");
                        Enter_Btn3.setText("도전");
                        Enter_Btn4.setText("도전");
                        Enter_Btn5.setText("도전");
                    }
                    Count_Text.setText(MainActivity.Timer/60000 + "분 " + MainActivity.Timer%60000/1000 + "초");
                }else{
                    Count_Text.setText("0분 0초");

                    Enter_Btn0.setText("도전");
                    Enter_Btn1.setText("도전");
                    Enter_Btn2.setText("도전");
                    Enter_Btn3.setText("도전");
                    Enter_Btn4.setText("도전");
                    Enter_Btn5.setText("도전");
                }

            }

        };

        final TimerTask tt = new TimerTask() {
            @Override
                public void run() {
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
            }
        };
        final Timer timer = new Timer();

        handler.removeMessages(0);
        timer.schedule(tt, 0 , 1000);

        Count_Text.setText(MainActivity.Timer/60000 + "분" + MainActivity.Timer%60000/1000 + "초");
        Count_Text2.setText(MainDB.getValue("TIME_SAVE_DAY")/3600000 + "시간 " + MainDB.getValue("TIME_SAVE_DAY")%3600000/60000 + "분");

        Clear_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clear_Btn.setVisibility(View.INVISIBLE);
            }
        });

        Enter_Btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(MainDB.getValue("TIME_CHECK") == 0) {
                    Toast.makeText(Tutorial.this, "용사가 달리기(공부)를 시작했습니다.\n               어서 핸드폰을 꺼주세요!!", Toast.LENGTH_SHORT).show();

                    MainActivity.Timer = 600000;
                    MainActivity.imsi = MainActivity.Timer;
                    MainActivity.Timer_set = System.currentTimeMillis();
                    MainDB.update("TIME_CHECK",11);

                    Enter_Btn0.setText("포기");

                }else if(MainDB.getValue("TIME_CHECK") == 11){
                    Enter_Btn0.setText("도전");

                    Toast.makeText(Tutorial.this, "용사가 달리기(공부)를 포기했습니다...\n               능력치가 감소했습니다.", Toast.LENGTH_SHORT).show();
                    MainDB.update("STR",MainDB.getValue("STR") - 1);
                    MainDB.update("INT",MainDB.getValue("INT") - 1);
                    MainDB.update("HP",MainDB.getValue("HP") - 1);
                    MainDB.update("DEX",MainDB.getValue("DEX") - 1);

                    int sum = MainDB.getValue("STR")
                            + MainDB.getValue("INT")
                            + MainDB.getValue("HP")
                            + MainDB.getValue("DEX");

                    MainDB.update("SUM",sum);

                    LogDB.insert_Log("달리기 (포기)",Now(),600000 - MainActivity.Timer);

                    MainActivity.Timer = 0;
                    MainDB.update("TIME_CHECK",0);
                }
            }
        });

        Enter_Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                if(MainDB.getValue("TIME_CHECK") == 0) {
                    Toast.makeText(Tutorial.this, "용사가 팔굽혀펴기(공부)를 시작했습니다.\n               어서 핸드폰을 꺼주세요!!", Toast.LENGTH_SHORT).show();

                    MainActivity.Timer = 1200000;
                    MainActivity.imsi = MainActivity.Timer;
                    MainActivity.Timer_set = System.currentTimeMillis();
                    MainDB.update("TIME_CHECK",12);

                    Enter_Btn1.setText("포기");


                }else if(MainDB.getValue("TIME_CHECK") == 12){
                    Enter_Btn1.setText("도전");

                    Toast.makeText(Tutorial.this, "용사가 팔굽혀펴기(공부)를 포기했습니다...\n               능력치가 감소했습니다.", Toast.LENGTH_SHORT).show();

                    MainDB.update("STR",MainDB.getValue("STR") - 2);
                    MainDB.update("INT",MainDB.getValue("INT") - 2);
                    MainDB.update("HP",MainDB.getValue("HP") - 2);
                    MainDB.update("DEX",MainDB.getValue("DEX") - 2);

                    int sum = MainDB.getValue("STR")
                            + MainDB.getValue("INT")
                            + MainDB.getValue("HP")
                            + MainDB.getValue("DEX");

                    MainDB.update("SUM",sum);

                    LogDB.insert_Log("팔굽혀펴기 (포기)",Now(),1200000 - MainActivity.Timer);

                    MainActivity.Timer = 0;
                    MainDB.update("TIME_CHECK",0);
                }
            }
        });
        Enter_Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                if(MainDB.getValue("TIME_CHECK") == 0) {
                    Toast.makeText(Tutorial.this, "용사가 윗몸일으키기(공부)를 시작했습니다.\n               어서 핸드폰을 꺼주세요!!", Toast.LENGTH_SHORT).show();

                    MainActivity.Timer = 1800000;
                    MainActivity.imsi = MainActivity.Timer;
                    MainActivity.Timer_set = System.currentTimeMillis();
                    MainDB.update("TIME_CHECK",13);

                    Enter_Btn2.setText("포기");

                }else if(MainDB.getValue("TIME_CHECK") == 13){
                    Enter_Btn2.setText("도전");

                    Toast.makeText(Tutorial.this, "용사가 윗몸일으키기(공부)를 포기했습니다...\n               능력치가 감소했습니다.", Toast.LENGTH_SHORT).show();

                    MainDB.update("STR",MainDB.getValue("STR") - 3);
                    MainDB.update("INT",MainDB.getValue("INT") - 3);
                    MainDB.update("HP",MainDB.getValue("HP") - 3);
                    MainDB.update("DEX",MainDB.getValue("DEX") - 3);

                    int sum = MainDB.getValue("STR")
                            + MainDB.getValue("INT")
                            + MainDB.getValue("HP")
                            + MainDB.getValue("DEX");

                    MainDB.update("SUM",sum);

                    LogDB.insert_Log("윗몸일으키기 (포기)",Now(),1800000 - MainActivity.Timer);

                    MainActivity.Timer = 0;
                    MainDB.update("TIME_CHECK",0);
                }
            }
        });
        Enter_Btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                if(MainDB.getValue("TIME_CHECK") == 0) {
                    Toast.makeText(Tutorial.this, "용사가 허수아비 베기(공부)를 시작했습니다.\n               어서 핸드폰을 꺼주세요!!", Toast.LENGTH_SHORT).show();

                    MainActivity.Timer = 2400000;
                    MainActivity.imsi = MainActivity.Timer;
                    MainActivity.Timer_set = System.currentTimeMillis();
                    MainDB.update("TIME_CHECK",14);

                    Enter_Btn3.setText("포기");


                }else if(MainDB.getValue("TIME_CHECK") == 14){
                    Enter_Btn3.setText("도전");

                    Toast.makeText(Tutorial.this, "용사가 허수아비 베기(공부)를 포기했습니다...\n               능력치가 감소했습니다.", Toast.LENGTH_SHORT).show();

                    MainDB.update("STR",MainDB.getValue("STR") - 4);
                    MainDB.update("INT",MainDB.getValue("INT") - 4);
                    MainDB.update("HP",MainDB.getValue("HP") - 4);
                    MainDB.update("DEX",MainDB.getValue("DEX") - 4);

                    int sum = MainDB.getValue("STR")
                            + MainDB.getValue("INT")
                            + MainDB.getValue("HP")
                            + MainDB.getValue("DEX");

                    MainDB.update("SUM",sum);

                    LogDB.insert_Log("허수아비 베기 (포기)",Now(),2400000 - MainActivity.Timer);

                    MainActivity.Timer = 0;
                    MainDB.update("TIME_CHECK",0);
                }
            }
        });
        Enter_Btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                if(MainDB.getValue("TIME_CHECK") == 0) {
                    Toast.makeText(Tutorial.this, "용사가 1대 1 대련(공부)을 시작했습니다.\n               어서 핸드폰을 꺼주세요!!", Toast.LENGTH_SHORT).show();

                    MainActivity.Timer = 3000000;
                    MainActivity.imsi = MainActivity.Timer;
                    MainActivity.Timer_set = System.currentTimeMillis();
                    MainDB.update("TIME_CHECK",15);

                    Enter_Btn4.setText("포기");

                }else if(MainDB.getValue("TIME_CHECK") == 15){
                    Enter_Btn4.setText("도전");

                    Toast.makeText(Tutorial.this, "용사가 1대 1 대련(공부)을 포기했습니다...\n               능력치가 감소했습니다.", Toast.LENGTH_SHORT).show();

                    MainDB.update("STR",MainDB.getValue("STR") - 5);
                    MainDB.update("INT",MainDB.getValue("INT") - 5);
                    MainDB.update("HP",MainDB.getValue("HP") - 5);
                    MainDB.update("DEX",MainDB.getValue("DEX") - 5);

                    int sum = MainDB.getValue("STR")
                            + MainDB.getValue("INT")
                            + MainDB.getValue("HP")
                            + MainDB.getValue("DEX");

                    MainDB.update("SUM",sum);

                    LogDB.insert_Log("1대 1 대련 (포기)",Now(),3000000 - MainActivity.Timer);

                    MainActivity.Timer = 0;
                    MainDB.update("TIME_CHECK",0);
                }
            }
        });
        Enter_Btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                if(MainDB.getValue("TIME_CHECK") == 0) {
                    Toast.makeText(Tutorial.this, "용사가 명상(공부)을 시작했습니다.\n               어서 핸드폰을 꺼주세요!!", Toast.LENGTH_SHORT).show();

                    MainActivity.Timer = 3600000;
                    MainActivity.imsi = MainActivity.Timer;
                    MainActivity.Timer_set = System.currentTimeMillis();
                    MainDB.update("TIME_CHECK",16);

                    Enter_Btn5.setText("포기");


                }else if(MainDB.getValue("TIME_CHECK") == 16){
                    Enter_Btn5.setText("도전");

                    Toast.makeText(Tutorial.this, "용사가 명상(공부)을 포기했습니다...\n               능력치가 감소했습니다.", Toast.LENGTH_SHORT).show();

                    MainDB.update("STR",MainDB.getValue("STR") - 6);
                    MainDB.update("INT",MainDB.getValue("INT") - 6);
                    MainDB.update("HP",MainDB.getValue("HP") - 6);
                    MainDB.update("DEX",MainDB.getValue("DEX") - 6);

                    int sum = MainDB.getValue("STR")
                            + MainDB.getValue("INT")
                            + MainDB.getValue("HP")
                            + MainDB.getValue("DEX");

                    MainDB.update("SUM",sum);

                    LogDB.insert_Log("명상 (포기)",Now(),3600000 - MainActivity.Timer);

                    MainActivity.Timer = 0;
                    MainDB.update("TIME_CHECK",0);
                }
            }
        });

        Dungeon_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                if(MainDB.getValue("TIME_CHECK") == 0) {
                    Intent intent = new Intent(getApplicationContext(), Dungeon.class);
                    Dungeon.Dungeon.finish();finish();                 startActivity(intent);                 overridePendingTransition(0,0);
                }
            }
        });

        Store_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(MainDB.getValue("TIME_CHECK") == 0) {
                    Intent intent = new Intent(getApplicationContext(), Store.class);
                    Dungeon.Dungeon.finish();finish();                 startActivity(intent);                 overridePendingTransition(0,0);
                }
            }
        });
        Main_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(MainDB.getValue("TIME_CHECK") == 0) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    Dungeon.Dungeon.finish();finish();                 startActivity(intent);                 overridePendingTransition(0,0);
                }
            }
        });
        Log_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(MainDB.getValue("TIME_CHECK") == 0) {
                    Intent intent = new Intent(getApplicationContext(), com.tistory.treenest.heroisstudying.Log.class);
                    finish();
                    Dungeon.Dungeon.finish();
                    startActivity(intent);
                }
            }
        });
        Setting_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(MainDB.getValue("TIME_CHECK") == 0){
                    Intent intent = new Intent(getApplicationContext(),Setting.class);
                    Dungeon.Dungeon.finish();finish();                 startActivity(intent);                 overridePendingTransition(0,0);
                }
            }
        });

    }

    @Override
    protected void onUserLeaveHint() {
        final DBHelper MainDB = new DBHelper(getApplicationContext(), "Main.db", null, 1);
        final DBHelper LogDB = new DBHelper(getApplicationContext(), "Log.db", null, 1);

        final Button Clear_Btn = (Button)findViewById(R.id.Clear_Btn_tutorial);

        if(MainDB.getValue("TIME_CHECK") != 0 && MainDB.getValue("TIME_CHECK") > 10) {
            Toast.makeText(Tutorial.this, "용사가 훈련(공부)을 포기했습니다...\n               능력치가 감소했습니다.", Toast.LENGTH_SHORT).show();

            MainDB.update("STR",MainDB.getValue("STR") - MainDB.getValue("TIME_CHECK"));
            MainDB.update("INT",MainDB.getValue("INT") - MainDB.getValue("TIME_CHECK"));
            MainDB.update("HP",MainDB.getValue("HP") - MainDB.getValue("TIME_CHECK"));
            MainDB.update("DEX",MainDB.getValue("DEX") - MainDB.getValue("TIME_CHECK"));

            int sum = MainDB.getValue("STR")
                    + MainDB.getValue("INT")
                    + MainDB.getValue("HP")
                    + MainDB.getValue("DEX");

            MainDB.update("SUM",sum);
        }

        if(MainDB.getValue("TIME_CHECK") == 11){
            LogDB.insert_Log("달리기 (포기)",Now(),600000 - MainActivity.Timer);
        }else if(MainDB.getValue("TIME_CHECK") == 12){
            LogDB.insert_Log("팔굽혀펴기 (포기)",Now(),1200000 - MainActivity.Timer);
        }else if(MainDB.getValue("TIME_CHECK") == 13){
            LogDB.insert_Log("윗몸일으키기 (포기)",Now(),1800000 - MainActivity.Timer);
        }else if(MainDB.getValue("TIME_CHECK") == 14){
            LogDB.insert_Log("허수아비 베기 (포기)",Now(),2400000 - MainActivity.Timer);
        }else if(MainDB.getValue("TIME_CHECK") == 15){
            LogDB.insert_Log("1대 1 대련 (포기)",Now(),3000000 - MainActivity.Timer);
        }else if(MainDB.getValue("TIME_CHECK") == 16){
            LogDB.insert_Log("명상 (포기)",Now(),3600000 - MainActivity.Timer);
        }

        MainActivity.Timer = 0;
        MainDB.update("TIME_CHECK",0);

        Clear_Btn.setVisibility(View.INVISIBLE);
        super.onUserLeaveHint();

    }
    @Override
    public void onBackPressed() {
        final DBHelper MainDB = new DBHelper(getApplicationContext(), "Main.db", null, 1);
        final DBHelper LogDB = new DBHelper(getApplicationContext(), "Log.db", null, 1);

        final Button Clear_Btn = (Button)findViewById(R.id.Clear_Btn_tutorial);

        if(MainDB.getValue("TIME_CHECK") != 0  && MainDB.getValue("TIME_CHECK") > 10) {
            Toast.makeText(Tutorial.this, "용사가 훈련(공부)을 포기했습니다...\n               능력치가 감소했습니다.", Toast.LENGTH_SHORT).show();

            MainDB.update("STR",MainDB.getValue("STR") - MainDB.getValue("TIME_CHECK"));
            MainDB.update("INT",MainDB.getValue("INT") - MainDB.getValue("TIME_CHECK"));
            MainDB.update("HP",MainDB.getValue("HP") - MainDB.getValue("TIME_CHECK"));
            MainDB.update("DEX",MainDB.getValue("DEX") - MainDB.getValue("TIME_CHECK"));

            int sum = MainDB.getValue("STR")
                    + MainDB.getValue("INT")
                    + MainDB.getValue("HP")
                    + MainDB.getValue("DEX");

            MainDB.update("SUM",sum);
        }

        if(MainDB.getValue("TIME_CHECK") == 11){
            LogDB.insert_Log("달리기 (포기)",Now(),600000 - MainActivity.Timer);
        }else if(MainDB.getValue("TIME_CHECK") == 12){
            LogDB.insert_Log("팔굽혀펴기 (포기)",Now(),1200000 - MainActivity.Timer);
        }else if(MainDB.getValue("TIME_CHECK") == 13){
            LogDB.insert_Log("윗몸일으키기 (포기)",Now(),1800000 - MainActivity.Timer);
        }else if(MainDB.getValue("TIME_CHECK") == 14){
            LogDB.insert_Log("허수아비 베기 (포기)",Now(),2400000 - MainActivity.Timer);
        }else if(MainDB.getValue("TIME_CHECK") == 15){
            LogDB.insert_Log("1대 1 대련 (포기)",Now(),3000000 - MainActivity.Timer);
        }else if(MainDB.getValue("TIME_CHECK") == 16){
            LogDB.insert_Log("명상 (포기)",Now(),3600000 - MainActivity.Timer);
        }

        MainActivity.Timer = 0;
        MainDB.update("TIME_CHECK",0);

        Clear_Btn.setVisibility(View.INVISIBLE);
        super.onBackPressed();
    }

    public String Now(){
        long now = System.currentTimeMillis();

        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd hh:mm");

        String getTime = sdf.format(date);
        return getTime;
    }
}
