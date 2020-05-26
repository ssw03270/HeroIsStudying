package com.tistory.treenest.heroisstudying;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    private Handler handler;

    public static int Timer = 0;
    public static int Timer_go = 0;
    public static long Timer_set = 0;
    public static int Check_Handler = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(this, "ca-app-pub-9082043936834188~7986422537");

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

        final ImageButton Dungeon_Btn = findViewById(R.id.Dungeon_Btn);
        final ImageButton Store_Btn = findViewById(R.id.Store_Btn);
        final ImageButton Main_Btn = findViewById(R.id.Main_btn);
        final ImageButton Log_Btn = findViewById(R.id.Log_Btn);
        final ImageButton Setting_Btn = findViewById(R.id.Setting_Btn);

        final Button Notice = findViewById(R.id.Notice);

        final ImageButton Tutorial_1 = findViewById(R.id.Tutorial_Img_1);
        final ImageButton Tutorial_2 = findViewById(R.id.Tutorial_Img_2);
        final ImageButton Tutorial_3 = findViewById(R.id.Tutorial_Img_3);

        final TextView SUM_Text = findViewById(R.id.SUM_Text);
        final TextView STR_Text = findViewById(R.id.STR_Text);
        final TextView INT_Text = findViewById(R.id.INT_Text);
        final TextView HP_Text = findViewById(R.id.HP_Text);
        final TextView DEX_Text = findViewById(R.id.DEX_Text);
        final TextView STAT_Text = findViewById(R.id.STAT_Text);

        if(MainDB.getValue("FIRST") == 0){
            long now = System.currentTimeMillis();

            Date date = new Date(now);
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");

            String getTime = sdf.format(date);

            MainDB.update("FIRST",1);
            DayDB.update_Day("DAY",getTime);
        }

        if (DayDB.getCheck_Day("DAY") == false) DayDB.insert_Day("DAY","");

        if (MainDB.getCheck("NOTIFICATION") == false) MainDB.insert("NOTIFICATION",0);
        if (MainDB.getCheck("1.1") == false) MainDB.insert("1.1",1);

        if (MainDB.getCheck("FIRST") == false) MainDB.insert("FIRST",0);
        if (MainDB.getCheck("SUM") == false) MainDB.insert("SUM", 40);
        if (MainDB.getCheck("STR") == false) MainDB.insert("STR", 10);
        if (MainDB.getCheck("INT") == false) MainDB.insert("INT", 10);
        if (MainDB.getCheck("HP") == false) MainDB.insert("HP", 10);
        if (MainDB.getCheck("DEX") == false) MainDB.insert("DEX", 10);
        if (MainDB.getCheck("STR_BUY") == false) MainDB.insert("STR_BUY", 1);
        if (MainDB.getCheck("INT_BUY") == false) MainDB.insert("INT_BUY", 1);
        if (MainDB.getCheck("HP_BUY") == false) MainDB.insert("HP_BUY", 1);
        if (MainDB.getCheck("DEX_BUY") == false) MainDB.insert("DEX_BUY", 1);
        if (MainDB.getCheck("STAT") == false) MainDB.insert("STAT", 10);
        if (MainDB.getCheck("STAT_USE") == false) MainDB.insert("STAT_USE", 0);
        if (MainDB.getCheck("TIME") == false) MainDB.insert("TIME", 0);
        if (MainDB.getCheck("TIME_SAVE_DAY") == false) MainDB.insert("TIME_SAVE_DAY", 0);
        if (MainDB.getCheck("TIME_SAVE_MONTH") == false) MainDB.insert("TIME_SAVE_MONTH", 0);
        if (MainDB.getCheck("TIME_CHECK") == false) MainDB.insert("TIME_CHECK", 0);
        if (MainDB.getCheck("TIME_CHECK") == false) MainDB.insert("TIME_CHECK", 0);
        if (MainDB.getCheck("WEOPON") == false) MainDB.insert("WEOPON", 0);
        if (MainDB.getCheck("HEAD") == false) MainDB.insert("HEAD", 0);
        if (MainDB.getCheck("TOP") == false) MainDB.insert("TOP", 0);
        if (MainDB.getCheck("BOTTOM") == false) MainDB.insert("BOTTOM", 0);
        if (MainDB.getCheck("SHOES") == false) MainDB.insert("SHOES", 0);

        if (WeoponDB.getCheck_Weopon(1) == false) WeoponDB.insert_Weopon(1, 0, "초보자의 단검", 10, 1, 0, 0, 0);
        if (WeoponDB.getCheck_Weopon(2) == false) WeoponDB.insert_Weopon(2, 0, "미완성 스태프", 10, 0, 1, 0, 0);
        if (WeoponDB.getCheck_Weopon(3) == false) WeoponDB.insert_Weopon(3, 0, "부러진 몽둥이", 30, 3, 0, 0, 0);
        if (WeoponDB.getCheck_Weopon(4) == false) WeoponDB.insert_Weopon(4, 0, "버려진 보주", 30, 0, 3, 0, 0);
        if (WeoponDB.getCheck_Weopon(5) == false) WeoponDB.insert_Weopon(5, 0, "견습 닌자의 수리검", 50, 0, 0, 0, 5);
        if (WeoponDB.getCheck_Weopon(6) == false) WeoponDB.insert_Weopon(6, 0, "신입 성기사의 방패", 200, 5, 0, 5, 0);//5개 단위로 가격 2배
        if (WeoponDB.getCheck_Weopon(7) == false) WeoponDB.insert_Weopon(7, 0, "용사 지망생의 검집", 220, 11, 0, 0, 0);
        if (WeoponDB.getCheck_Weopon(8) == false) WeoponDB.insert_Weopon(8, 0, "수석 마법사의 책", 260, 0, 10, 0, 3);

        if (HeadDB.getCheck_Head(1) == false) HeadDB.insert_Head(1, 0, "낡은 밀집모자", 10, 0, 0, 1, 0);
        if (HeadDB.getCheck_Head(2) == false) HeadDB.insert_Head(2, 0, "검은색 비니", 20, 0, 0, 0, 2);
        if (HeadDB.getCheck_Head(3) == false) HeadDB.insert_Head(3, 0, "구겨진 투구", 30, 0, 0, 3, 0);
        if (HeadDB.getCheck_Head(4) == false) HeadDB.insert_Head(4, 0, "고깔 모자", 30, 0, 3, 0, 0);
        if (HeadDB.getCheck_Head(5) == false) HeadDB.insert_Head(5, 0, "누군가를 위한 가발", 40, 4, 0, 0, 0);
        if (HeadDB.getCheck_Head(6) == false) HeadDB.insert_Head(6, 0, "도적단의 두건", 200, 0, 0, 3, 7);
        if (HeadDB.getCheck_Head(7) == false) HeadDB.insert_Head(7, 0, "방랑자의 삿갓", 220, 7, 0, 0, 4);

        if (TopDB.getCheck_Top(1) == false) TopDB.insert_Top(1, 0, "찢어진 티셔츠", 10, 0, 0, 1, 0);
        if (TopDB.getCheck_Top(2) == false) TopDB.insert_Top(2, 0, "체크무늬 셔츠", 20, 0, 0, 0, 2);
        if (TopDB.getCheck_Top(3) == false) TopDB.insert_Top(3, 0, "구겨진 갑옷 상", 30, 0, 0, 3, 0);
        if (TopDB.getCheck_Top(4) == false) TopDB.insert_Top(4, 0, "백수의 후드티", 40, 0, 0, 0, 4);
        if (TopDB.getCheck_Top(5) == false) TopDB.insert_Top(5, 0, "견습 마법사의 망토", 50, 0, 5, 0, 0);
        if (TopDB.getCheck_Top(6) == false) TopDB.insert_Top(6, 0, "신입 성기사의 갑옷", 200, 3, 0, 7, 0);

        if (BottomDB.getCheck_Bottom(1) == false) BottomDB.insert_Bottom(1, 0, "낡은 청바지", 10, 0, 0, 1, 0);
        if (BottomDB.getCheck_Bottom(2) == false) BottomDB.insert_Bottom(2, 0, "삼선 츄리닝", 20, 0, 0, 0, 2);
        if (BottomDB.getCheck_Bottom(3) == false) BottomDB.insert_Bottom(3, 0, "구겨진 갑옷 하", 30, 3, 0, 0, 0);
        if (BottomDB.getCheck_Bottom(4) == false) BottomDB.insert_Bottom(4, 0, "백수의 반바지", 40, 0, 4, 0, 0);
        if (BottomDB.getCheck_Bottom(5) == false) BottomDB.insert_Bottom(5, 0, "꽃무늬 몸빼바지", 50, 0, 0, 5, 0);
        if (BottomDB.getCheck_Bottom(6) == false) BottomDB.insert_Bottom(6, 0, "도적단의 활동복", 220, 0, 0, 0, 11);

        if (ShoesDB.getCheck_Shoes(1) == false) ShoesDB.insert_Shoes(1, 0, "찢어진 운동화", 10, 1, 0, 0, 0);
        if (ShoesDB.getCheck_Shoes(2) == false) ShoesDB.insert_Shoes(2, 0, "삼선 슬리퍼", 20, 0, 0, 0, 2);
        if (ShoesDB.getCheck_Shoes(3) == false) ShoesDB.insert_Shoes(3, 0, "밑창 없는 구두", 30, 0, 3, 0, 0);
        if (ShoesDB.getCheck_Shoes(4) == false) ShoesDB.insert_Shoes(4, 0, "노란색 장화", 30, 0, 0, 3, 0);
        if (ShoesDB.getCheck_Shoes(5) == false) ShoesDB.insert_Shoes(5, 0, "롤러스케이트", 50, 0, 0, 0, 5);
        if (ShoesDB.getCheck_Shoes(6) == false) ShoesDB.insert_Shoes(6, 0, "신입 궁수의 신발", 220, 0, 0, 0, 11);

        int sum = MainDB.getValue("STR")
                + MainDB.getValue("INT")
                + MainDB.getValue("HP")
                + MainDB.getValue("DEX");
        MainDB.update("SUM", sum);

//        result.setText(MainDB.getResult());

        Dungeon_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Dungeon.class);finish();                 startActivity(intent);                 overridePendingTransition(0,0);
            }
        });

        Store_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Store.class);finish();                 startActivity(intent);                 overridePendingTransition(0,0);
            }
        });
        Log_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.tistory.treenest.heroisstudying.Log.class);finish();                 startActivity(intent);                 overridePendingTransition(0,0);
            }
        });
        Setting_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),Setting.class);finish();                 startActivity(intent);                 overridePendingTransition(0,0);
            }
        });
        Thread thread = new Thread();

        if(Check_Handler == 0){
            thread.start();
            Check_Handler = 1;
        }
        if(MainDB.getValue("FIRST") == 0) {
            Tutorial_1.setVisibility(View.VISIBLE);
            Tutorial_2.setVisibility(View.VISIBLE);
            Tutorial_3.setVisibility(View.VISIBLE);
        }
        if(MainDB.getValue("1.1") == 1){
            Notice.setVisibility(View.VISIBLE);
        }
        Notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Notice.setVisibility(View.INVISIBLE);
                MainDB.update("1.1", 0);
            }
        });

        Tutorial_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Tutorial_3.setVisibility(View.INVISIBLE);
            }
        });
        Tutorial_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Tutorial_2.setVisibility(View.INVISIBLE);
            }
        });
        Tutorial_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Tutorial_1.setVisibility(View.INVISIBLE);
            }
        });

    }




    @Override
    public void onResume() {

        final DBHelper MainDB = new DBHelper(getApplicationContext(), "Main.db", null, 1);
        final DBHelper WeoponDB = new DBHelper(getApplicationContext(), "Weopon.db", null, 1);
        final DBHelper HeadDB = new DBHelper(getApplicationContext(), "Head.db", null, 1);
        final DBHelper TopDB = new DBHelper(getApplicationContext(), "Top.db", null, 1);
        final DBHelper BottomDB = new DBHelper(getApplicationContext(), "Bottom.db", null, 1);
        final DBHelper ShoesDB = new DBHelper(getApplicationContext(), "Shoes.db", null, 1);

        final TextView SUM_Text = findViewById(R.id.SUM_Text);
        final TextView STR_Text = findViewById(R.id.STR_Text);
        final TextView INT_Text = findViewById(R.id.INT_Text);
        final TextView HP_Text = findViewById(R.id.HP_Text);
        final TextView DEX_Text = findViewById(R.id.DEX_Text);
        final TextView STAT_Text = findViewById(R.id.STAT_Text);

        int i = MainDB.getValue("WEOPON");
        int j = MainDB.getValue("HEAD");
        int k = MainDB.getValue("TOP");
        int q = MainDB.getValue("BOTTOM");
        int l = MainDB.getValue("SHOES");

        if (MainDB.getValue("STR") < 0) {
            MainDB.update("STR", WeoponDB.getInt_Weopon(i, 5) + HeadDB.getInt_Head(j, 5) + TopDB.getInt_Top(k, 5) + BottomDB.getInt_Bottom(q, 5) + ShoesDB.getInt_Shoes(l, 5));
        }
        if (MainDB.getValue("INT") < 0) {
            MainDB.update("INT", WeoponDB.getInt_Weopon(i, 6) + HeadDB.getInt_Head(j, 6) + TopDB.getInt_Top(k, 6) + BottomDB.getInt_Bottom(q, 6) + ShoesDB.getInt_Shoes(l, 6));
        }
        if (MainDB.getValue("HP") < 0) {
            MainDB.update("HP", WeoponDB.getInt_Weopon(i, 7) + HeadDB.getInt_Head(j, 7) + TopDB.getInt_Top(k, 7) + BottomDB.getInt_Bottom(q, 7) + ShoesDB.getInt_Shoes(l, 7));
        }
        if (MainDB.getValue("DEX") < 0) {
            MainDB.update("DEX", WeoponDB.getInt_Weopon(i, 8) + HeadDB.getInt_Head(j, 8) + TopDB.getInt_Top(k, 8) + BottomDB.getInt_Bottom(q, 8) + ShoesDB.getInt_Shoes(l, 8));
        }


        int sum = MainDB.getValue("STR")
                + MainDB.getValue("INT")
                + MainDB.getValue("HP")
                + MainDB.getValue("DEX");

        MainDB.update("SUM", sum);

        SUM_Text.setText("전투력 : " + MainDB.getValue("SUM"));
        STR_Text.setText("근력 : " + MainDB.getValue("STR"));
        INT_Text.setText("지력 : " + MainDB.getValue("INT"));
        HP_Text.setText("체력 : " + MainDB.getValue("HP"));
        DEX_Text.setText("민첩 : " + MainDB.getValue("DEX"));
        STAT_Text.setText("남은 스탯 : " + MainDB.getValue("STAT"));

        //result.setText(MainDB.getResult());


        super.onResume();
    }

    public static int imsi = 0;
    private class Thread extends java.lang.Thread {

        final DBHelper MainDB = new DBHelper(getApplicationContext(), "Main.db", null, 1);

        @Override
        public void run() {
            while (true) {
                Timer_go = (int)(long)(System.currentTimeMillis() - Timer_set);

                android.util.Log.i("a","imsi = " + imsi);
                Timer = imsi - Timer_go;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

