package com.tistory.treenest.heroisstudying;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class Dungeon extends AppCompatActivity {
    public static Activity Dungeon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon);

        AdView mAdView;

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.FULL_BANNER);
        adView.setAdUnitId("ca-app-pub-9082043936834188/6919972628");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Dungeon = Dungeon.this;

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

        final Button Clear_Btn = (Button)findViewById(R.id.Clear_Btn_Dungeon);

        Count_Text.setText(MainActivity.Timer/60000 + "분" + MainActivity.Timer%60000/1000 + "초");
        Count_Text2.setText(MainDB.getValue("TIME_SAVE_DAY")/3600000 + "시간 " + MainDB.getValue("TIME_SAVE_DAY")%3600000/60000 + "분");

        final Handler handler = new Handler(){

            public void handleMessage(Message msg){
                if(MainDB.getValue("TIME_CHECK") != 0) {

                    if(MainActivity.Timer <= 0 && MainDB.getValue("TIME_CHECK") < 10){
                        Toast.makeText(Dungeon.this, "용사가 공략(공부)을 성공적으로 마쳤습니다!!!", Toast.LENGTH_SHORT).show();

                        Bitmap mLargeIconForNoti = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round);


                        if(MainDB.getValue("NOTIFICATION") == 0) {

                            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                            String channelId = "channel";
                            String channelName = "Channel Name";
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                int importance = NotificationManager.IMPORTANCE_HIGH;
                                NotificationChannel mChannel = new NotificationChannel(
                                        channelId, channelName, importance);
                                notificationManager.createNotificationChannel(mChannel);
                            }

                            NotificationCompat.Builder builder = new NotificationCompat.Builder(Dungeon.this,channelId)
                                    .setSmallIcon(R.mipmap.ic_launcher_round)
                                    .setLargeIcon(mLargeIconForNoti)
                                    .setAutoCancel(true)
                                    .setContentTitle("공략 완료!!")
                                    .setContentText("보상을 확인하세요~")
                                    .setDefaults(Notification.DEFAULT_ALL);

                            notificationManager.notify(0, builder.build());
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

                            NotificationCompat.Builder builder = new NotificationCompat.Builder(Dungeon.this,channelId)
                                    .setSmallIcon(R.mipmap.ic_launcher_round)
                                    .setLargeIcon(mLargeIconForNoti)
                                    .setAutoCancel(true)
                                    .setContentTitle("공략 완료!!")
                                    .setContentText("보상을 확인하세요~")
                                    .setDefaults(Notification.DEFAULT_LIGHTS);

                            notificationManager.notify(0, builder.build());
                        }


                        Clear_Btn.setVisibility(View.VISIBLE);

                        if(MainDB.getValue("TIME_CHECK") == 1){
                            MainDB.update("STAT",MainDB.getValue("STAT") + 2);
                            MainDB.update("TIME_SAVE_DAY",MainDB.getValue("TIME_SAVE_DAY") + 600000);
                            Clear_Btn.setText("훈련 완료!!\n보상 : 2 스탯");
                            LogDB.insert_Log("지하 1층 (성공)",Now(),600000);
                        }
                        if(MainDB.getValue("TIME_CHECK") == 2){
                            MainDB.update("STAT",MainDB.getValue("STAT") + 6);
                            MainDB.update("TIME_SAVE_DAY",MainDB.getValue("TIME_SAVE_DAY") + 1800000);
                            Clear_Btn.setText("훈련 완료!!\n보상 : 6 스탯");
                            LogDB.insert_Log("지하 2층 (성공)",Now(),1800000);
                        }
                        if(MainDB.getValue("TIME_CHECK") == 3){
                            MainDB.update("STAT",MainDB.getValue("STAT") + 12);
                            MainDB.update("TIME_SAVE_DAY",MainDB.getValue("TIME_SAVE_DAY") + 3600000);
                            Clear_Btn.setText("훈련 완료!!\n보상 : 12 스탯");
                            LogDB.insert_Log("지하 3층 (성공)",Now(),3600000);
                        }
                        if(MainDB.getValue("TIME_CHECK") == 4){
                            MainDB.update("STAT",MainDB.getValue("STAT") + 24);
                            MainDB.update("TIME_SAVE_DAY",MainDB.getValue("TIME_SAVE_DAY") + 7200000);
                            Clear_Btn.setText("훈련 완료!!\n보상 : 24 스탯");
                            LogDB.insert_Log("지하 4층 (성공)",Now(),7200000);
                        }
                        if(MainDB.getValue("TIME_CHECK") == 5){
                            MainDB.update("STAT",MainDB.getValue("STAT") + 48);
                            MainDB.update("TIME_SAVE_DAY",MainDB.getValue("TIME_SAVE_DAY") + 14400000);
                            Clear_Btn.setText("훈련 완료!!\n보상 : 48 스탯");
                            LogDB.insert_Log("지하 5층 (성공)",Now(),14400000);
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
                    Intent intent = new Intent(getApplicationContext(), Tutorial.class);
                    startActivity(intent);
                    overridePendingTransition(0,0);
                }
            }
        });
        Enter_Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                if(MainDB.getValue("TIME_CHECK") == 0) {
                    if(MainDB.getValue("SUM") >= 50){
                        Toast.makeText(Dungeon.this, "용사가 지하 1층 공략(공부)을 시작했습니다.\n               어서 핸드폰을 꺼주세요!!", Toast.LENGTH_SHORT).show();

                        MainActivity.Timer = 600000;
                        MainActivity.imsi = MainActivity.Timer;
                        MainActivity.Timer_set = System.currentTimeMillis();

                        MainDB.update("TIME_CHECK",1);

                        Enter_Btn1.setText("포기");
                    }else{
                        Toast.makeText(Dungeon.this, "훈련장에서 수련을 해 능력치를 올려주세요.\n                         전투력 50이상", Toast.LENGTH_SHORT).show();

                    }


                }else if(MainDB.getValue("TIME_CHECK") == 1){
                    Enter_Btn1.setText("도전");

                    Toast.makeText(Dungeon.this, "용사가 공략(공부)을 포기했습니다...\n               능력치가 감소했습니다.", Toast.LENGTH_SHORT).show();

                    MainDB.update("STR",MainDB.getValue("STR") - MainDB.getValue("TIME_CHECK") * 2);
                    MainDB.update("INT",MainDB.getValue("INT") - MainDB.getValue("TIME_CHECK") * 2);
                    MainDB.update("HP",MainDB.getValue("HP") - MainDB.getValue("TIME_CHECK") * 2);
                    MainDB.update("DEX",MainDB.getValue("DEX") - MainDB.getValue("TIME_CHECK") * 2);

                    int sum = MainDB.getValue("STR")
                            + MainDB.getValue("INT")
                            + MainDB.getValue("HP")
                            + MainDB.getValue("DEX");

                    MainDB.update("SUM",sum);

                    LogDB.insert_Log("지하 1층 (포기)",Now(),600000 - MainActivity.Timer);

                    MainActivity.Timer = 0;
                    MainDB.update("TIME_CHECK",0);
                }
            }
        });
        Enter_Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                if(MainDB.getValue("TIME_CHECK") == 0) {
                    if(MainDB.getValue("SUM") >= 100){
                        Toast.makeText(Dungeon.this, "용사가 지하 2층 공략(공부)을 시작했습니다.\n               어서 핸드폰을 꺼주세요!!", Toast.LENGTH_SHORT).show();

                        MainActivity.Timer = 1800000;
                        MainActivity.imsi = MainActivity.Timer;
                        MainActivity.Timer_set = System.currentTimeMillis();
                        MainDB.update("TIME_CHECK",2);

                        Enter_Btn2.setText("포기");
                    }else{
                        Toast.makeText(Dungeon.this, "훈련장에서 수련을 해 능력치를 올려주세요.\n                         전투력 100이상", Toast.LENGTH_SHORT).show();
                    }


                }else if(MainDB.getValue("TIME_CHECK") == 2){
                    Enter_Btn2.setText("도전");

                    Toast.makeText(Dungeon.this, "용사가 공략(공부)을 포기했습니다...\n               능력치가 감소했습니다.", Toast.LENGTH_SHORT).show();

                    MainDB.update("STR",MainDB.getValue("STR") - MainDB.getValue("TIME_CHECK") * 2);
                    MainDB.update("INT",MainDB.getValue("INT") - MainDB.getValue("TIME_CHECK") * 2);
                    MainDB.update("HP",MainDB.getValue("HP") - MainDB.getValue("TIME_CHECK") * 2);
                    MainDB.update("DEX",MainDB.getValue("DEX") - MainDB.getValue("TIME_CHECK") * 2);

                    int sum = MainDB.getValue("STR")
                            + MainDB.getValue("INT")
                            + MainDB.getValue("HP")
                            + MainDB.getValue("DEX");

                    MainDB.update("SUM",sum);

                    LogDB.insert_Log("지하 2층 (포기)",Now(),1800000 - MainActivity.Timer);

                    MainActivity.Timer = 0;
                    MainDB.update("TIME_CHECK",0);
                }
            }
        });
        Enter_Btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                if(MainDB.getValue("TIME_CHECK") == 0) {
                    if(MainDB.getValue("SUM") >= 200){
                        Toast.makeText(Dungeon.this, "용사가 지하 3층 공략(공부)을 시작했습니다.\n               어서 핸드폰을 꺼주세요!!", Toast.LENGTH_SHORT).show();

                        MainActivity.Timer = 3600000;
                        MainActivity.imsi = MainActivity.Timer;
                        MainActivity.Timer_set = System.currentTimeMillis();
                        MainDB.update("TIME_CHECK",3);

                        Enter_Btn3.setText("포기");
                    }else{
                        Toast.makeText(Dungeon.this, "훈련장에서 수련을 해 능력치를 올려주세요.\n                         전투력 200이상", Toast.LENGTH_SHORT).show();
                    }


                }else if(MainDB.getValue("TIME_CHECK") == 3){
                    Enter_Btn3.setText("도전");

                    Toast.makeText(Dungeon.this, "용사가 공략(공부)을 포기했습니다...\n               능력치가 감소했습니다.", Toast.LENGTH_SHORT).show();

                    MainDB.update("STR",MainDB.getValue("STR") - MainDB.getValue("TIME_CHECK") * 2);
                    MainDB.update("INT",MainDB.getValue("INT") - MainDB.getValue("TIME_CHECK") * 2);
                    MainDB.update("HP",MainDB.getValue("HP") - MainDB.getValue("TIME_CHECK") * 2);
                    MainDB.update("DEX",MainDB.getValue("DEX") - MainDB.getValue("TIME_CHECK") * 2);

                    int sum = MainDB.getValue("STR")
                            + MainDB.getValue("INT")
                            + MainDB.getValue("HP")
                            + MainDB.getValue("DEX");

                    MainDB.update("SUM",sum);

                    LogDB.insert_Log("지하 3층 (포기)",Now(),3600000 - MainActivity.Timer);

                    MainActivity.Timer = 0;
                    MainDB.update("TIME_CHECK",0);
                }
            }
        });
        Enter_Btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                if(MainDB.getValue("TIME_CHECK") == 0) {
                    if(MainDB.getValue("SUM") >= 400){
                        Toast.makeText(Dungeon.this, "용사가 지하 4층 공략(공부)을 시작했습니다.\n               어서 핸드폰을 꺼주세요!!", Toast.LENGTH_SHORT).show();

                        MainActivity.Timer = 7200000;
                        MainActivity.imsi = MainActivity.Timer;
                        MainActivity.Timer_set = System.currentTimeMillis();
                        MainDB.update("TIME_CHECK",4);

                        Enter_Btn4.setText("포기");
                    }else{
                        Toast.makeText(Dungeon.this, "훈련장에서 수련을 해 능력치를 올려주세요.\n                         전투력 400이상", Toast.LENGTH_SHORT).show();
                    }


                }else if(MainDB.getValue("TIME_CHECK") == 4){
                    Enter_Btn4.setText("도전");

                    Toast.makeText(Dungeon.this, "용사가 공략(공부)을 포기했습니다...\n               능력치가 감소했습니다.", Toast.LENGTH_SHORT).show();

                    MainDB.update("STR",MainDB.getValue("STR") - MainDB.getValue("TIME_CHECK") * 2);
                    MainDB.update("INT",MainDB.getValue("INT") - MainDB.getValue("TIME_CHECK") * 2);
                    MainDB.update("HP",MainDB.getValue("HP") - MainDB.getValue("TIME_CHECK") * 2);
                    MainDB.update("DEX",MainDB.getValue("DEX") - MainDB.getValue("TIME_CHECK") * 2);

                    int sum = MainDB.getValue("STR")
                            + MainDB.getValue("INT")
                            + MainDB.getValue("HP")
                            + MainDB.getValue("DEX");

                    MainDB.update("SUM",sum);

                    LogDB.insert_Log("지하 4층 (포기)",Now(),7200000 - MainActivity.Timer);

                    MainActivity.Timer = 0;
                    MainDB.update("TIME_CHECK",0);
                }
            }
        });
        Enter_Btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                if(MainDB.getValue("TIME_CHECK") == 0) {
                    if(MainDB.getValue("SUM") >= 800){
                        Toast.makeText(Dungeon.this, "용사가 지하 5층 공략(공부)을 시작했습니다.\n               어서 핸드폰을 꺼주세요!!", Toast.LENGTH_SHORT).show();

                        MainActivity.Timer = 14400000;
                        MainActivity.imsi = MainActivity.Timer;
                        MainActivity.Timer_set = System.currentTimeMillis();
                        MainDB.update("TIME_CHECK",5);

                        Enter_Btn5.setText("포기");
                    }else{
                        Toast.makeText(Dungeon.this, "훈련장에서 수련을 해 능력치를 올려주세요.\n                         전투력 800이상", Toast.LENGTH_SHORT).show();
                    }


                }else if(MainDB.getValue("TIME_CHECK") == 5){
                    Enter_Btn5.setText("도전");

                    Toast.makeText(Dungeon.this, "용사가 공략(공부)을 포기했습니다...\n               능력치가 감소했습니다.", Toast.LENGTH_SHORT).show();

                    MainDB.update("STR",MainDB.getValue("STR") - MainDB.getValue("TIME_CHECK") * 2);
                    MainDB.update("INT",MainDB.getValue("INT") - MainDB.getValue("TIME_CHECK") * 2);
                    MainDB.update("HP",MainDB.getValue("HP") - MainDB.getValue("TIME_CHECK") * 2);
                    MainDB.update("DEX",MainDB.getValue("DEX") - MainDB.getValue("TIME_CHECK") * 2);

                    int sum = MainDB.getValue("STR")
                            + MainDB.getValue("INT")
                            + MainDB.getValue("HP")
                            + MainDB.getValue("DEX");

                    MainDB.update("SUM",sum);

                    LogDB.insert_Log("지하 5층 (포기)",Now(),14400000 - MainActivity.Timer);

                    MainActivity.Timer = 0;
                    MainDB.update("TIME_CHECK",0);
                }
            }
        });

        Store_Btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                if(MainDB.getValue("TIME_CHECK") == 0){
                    Intent intent = new Intent(getApplicationContext(),Store.class);finish();                 startActivity(intent);                 overridePendingTransition(0,0);
                }

            }
        });
        Main_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(MainDB.getValue("TIME_CHECK") == 0) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);finish();                 startActivity(intent);                 overridePendingTransition(0,0);
                }
            }
        });
        Log_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(MainDB.getValue("TIME_CHECK") == 0) {
                    Intent intent = new Intent(getApplicationContext(), com.tistory.treenest.heroisstudying.Log.class);finish();                 startActivity(intent);                 overridePendingTransition(0,0);
                }
            }
        });
        Setting_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                if(MainDB.getValue("TIME_CHECK") == 0){
                    Intent intent = new Intent(getApplicationContext(),Setting.class);finish();                 startActivity(intent);                 overridePendingTransition(0,0);
                }
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();

        final DBHelper MainDB = new DBHelper(getApplicationContext(), "Main.db", null, 1);

        final TextView Count_Text2 = (TextView)findViewById(R.id.CountSum_Text);
        Count_Text2.setText(MainDB.getValue("TIME_SAVE_DAY")/3600000 + "시간 " + MainDB.getValue("TIME_SAVE_DAY")%3600000/60000 + "분");

    }


    @Override
    protected void onUserLeaveHint() {
        final DBHelper MainDB = new DBHelper(getApplicationContext(), "Main.db", null, 1);
        final DBHelper LogDB = new DBHelper(getApplicationContext(), "Log.db", null, 1);

        final Button Clear_Btn = (Button)findViewById(R.id.Clear_Btn_Dungeon);

        if(MainDB.getValue("TIME_CHECK") != 0 && MainDB.getValue("TIME_CHECK") < 10) {
            Toast.makeText(Dungeon.this, "용사가 공략(공부)을 포기했습니다...\n               능력치가 감소했습니다.", Toast.LENGTH_SHORT).show();

            MainDB.update("STR",MainDB.getValue("STR") - MainDB.getValue("TIME_CHECK") * 2);
            MainDB.update("INT",MainDB.getValue("INT") - MainDB.getValue("TIME_CHECK") * 2);
            MainDB.update("HP",MainDB.getValue("HP") - MainDB.getValue("TIME_CHECK") * 2);
            MainDB.update("DEX",MainDB.getValue("DEX") - MainDB.getValue("TIME_CHECK") * 2);

            int sum = MainDB.getValue("STR")
                    + MainDB.getValue("INT")
                    + MainDB.getValue("HP")
                    + MainDB.getValue("DEX");

            MainDB.update("SUM",sum);
        }

        if(MainDB.getValue("TIME_CHECK") == 1){
            LogDB.insert_Log("지하 1층 (포기)",Now(),600000 - MainActivity.Timer);
        }else if(MainDB.getValue("TIME_CHECK") == 2){
            LogDB.insert_Log("지하 2층 (포기)",Now(),1800000 - MainActivity.Timer);
        }else if(MainDB.getValue("TIME_CHECK") == 3){
            LogDB.insert_Log("지하 3층 (포기)",Now(),3600000 - MainActivity.Timer);
        }else if(MainDB.getValue("TIME_CHECK") == 4){
            LogDB.insert_Log("지하 4층 (포기)",Now(),7200000 - MainActivity.Timer);
        }else if(MainDB.getValue("TIME_CHECK") == 5){
            LogDB.insert_Log("지하 5층 (포기)",Now(),14400000 - MainActivity.Timer);
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

        final Button Clear_Btn = (Button)findViewById(R.id.Clear_Btn_Dungeon);

        if(MainDB.getValue("TIME_CHECK") != 0  && MainDB.getValue("TIME_CHECK") < 10) {
            Toast.makeText(Dungeon.this, "용사가 공략(공부)을 포기했습니다...\n               능력치가 감소했습니다.", Toast.LENGTH_SHORT).show();

            MainDB.update("STR",MainDB.getValue("STR") - MainDB.getValue("TIME_CHECK") * 2);
            MainDB.update("INT",MainDB.getValue("INT") - MainDB.getValue("TIME_CHECK") * 2);
            MainDB.update("HP",MainDB.getValue("HP") - MainDB.getValue("TIME_CHECK") * 2);
            MainDB.update("DEX",MainDB.getValue("DEX") - MainDB.getValue("TIME_CHECK") * 2);

            int sum = MainDB.getValue("STR")
                    + MainDB.getValue("INT")
                    + MainDB.getValue("HP")
                    + MainDB.getValue("DEX");

            MainDB.update("SUM",sum);
        }

        if(MainDB.getValue("TIME_CHECK") == 1){
            LogDB.insert_Log("지하 1층 (포기)",Now(),600000 - MainActivity.Timer);
        }else if(MainDB.getValue("TIME_CHECK") == 2){
            LogDB.insert_Log("지하 2층 (포기)",Now(),1800000 - MainActivity.Timer);
        }else if(MainDB.getValue("TIME_CHECK") == 3){
            LogDB.insert_Log("지하 3층 (포기)",Now(),3600000 - MainActivity.Timer);
        }else if(MainDB.getValue("TIME_CHECK") == 4){
            LogDB.insert_Log("지하 4층 (포기)",Now(),7200000 - MainActivity.Timer);
        }else if(MainDB.getValue("TIME_CHECK") == 5){
            LogDB.insert_Log("지하 5층 (포기)",Now(),14400000 - MainActivity.Timer);
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
