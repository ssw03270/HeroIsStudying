package com.tistory.treenest.heroisstudying;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 MainDB이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, value 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */
        db.execSQL("CREATE TABLE MainDB (_id INTEGER PRIMARY KEY AUTOINCREMENT, item TEXT, value INTEGER);");
        db.execSQL("CREATE TABLE DayDB (_id INTEGER PRIMARY KEY AUTOINCREMENT, item TEXT, value TEXT);");
        db.execSQL("CREATE TABLE LogDB (_id INTEGER PRIMARY KEY AUTOINCREMENT, type TEXT, day TEXT, time INTEGER);");
        //value = 0 구매 안함, value = 1 구매 완료, value = 2 \uC7A5\uCC29 완료
        db.execSQL("CREATE TABLE WeoponDB (_id INTEGER PRIMARY KEY AUTOINCREMENT, item INTEGER, value INTEGER, name TEXT, price INTEGER, str INTEGER, int INTEGER , hp INTEGER, dex INTEGER);");
        db.execSQL("CREATE TABLE HeadDB (_id INTEGER PRIMARY KEY AUTOINCREMENT, item INTEGER, value INTEGER, name TEXT, price INTEGER, str INTEGER, int INTEGER , hp INTEGER, dex INTEGER);");
        db.execSQL("CREATE TABLE TopDB (_id INTEGER PRIMARY KEY AUTOINCREMENT, item INTEGER, value INTEGER, name TEXT, price INTEGER, str INTEGER, int INTEGER , hp INTEGER, dex INTEGER);");
        db.execSQL("CREATE TABLE BottomDB (_id INTEGER PRIMARY KEY AUTOINCREMENT, item INTEGER, value INTEGER, name TEXT, price INTEGER, str INTEGER, int INTEGER , hp INTEGER, dex INTEGER);");
        db.execSQL("CREATE TABLE ShoesDB (_id INTEGER PRIMARY KEY AUTOINCREMENT, item INTEGER, value INTEGER, name TEXT, price INTEGER, str INTEGER, int INTEGER , hp INTEGER, dex INTEGER);");

    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////

    public void insert(String item, int value) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO MainDB VALUES(null, '" + item + "', " + value + ");");
        db.close();
    }
    public void insert_Day(String item, String value) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO DayDB VALUES(null, '" + item + "', '" + value + "');");
        db.close();
    }

    public void insert_Log(String type, String day, int time) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO LogDB VALUES(null, '" + type + "', '" + day + "' ," + time + ");");
        db.close();
    }
    public void insert_Weopon(int item, int value, String name, int price, int str, int INT, int hp, int dex) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO WeoponDB VALUES(null, " + item + ", " + value + " , '" + name + "' , "+ price +" ,"+ str +" ,"+ INT +" ,"+ hp +", " + dex +");");
        db.close();
    }
    public void insert_Head(int item, int value, String name, int price, int str, int INT, int hp, int dex) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO HeadDB VALUES(null, " + item + ", " + value + " , '" + name + "' , "+ price +" ,"+ str +" ,"+ INT +" ,"+ hp +", " + dex +");");
        db.close();
    }
    public void insert_Top(int item, int value, String name, int price, int str, int INT, int hp, int dex) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO TopDB VALUES(null, " + item + ", " + value + " , '" + name + "' , "+ price +" ,"+ str +" ,"+ INT +" ,"+ hp +", " + dex +");");
        db.close();
    }
    public void insert_Bottom(int item, int value, String name, int price, int str, int INT, int hp, int dex) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO BottomDB VALUES(null, " + item + ", " + value + " , '" + name + "' , "+ price +" ,"+ str +" ,"+ INT +" ,"+ hp +", " + dex +");");
        db.close();
    }
    public void insert_Shoes(int item, int value, String name, int price, int str, int INT, int hp, int dex) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO ShoesDB VALUES(null, " + item + ", " + value + " , '" + name + "' , "+ price +" ,"+ str +" ,"+ INT +" ,"+ hp +", " + dex +");");
        db.close();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void update(String item, int value) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE MainDB SET value=" + value + " WHERE item='" + item + "';");
        db.close();
    }
    public void update_Day(String item, String value) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE DayDB SET value='" + value + "' WHERE item='" + item + "';");
        db.close();
    }
    public void update_Weopon(int item, int value) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE WeoponDB SET value=" + value + " WHERE item='" + item + "';");
        db.close();
    }
    public void update_Head(int item, int value) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE HeadDB SET value=" + value + " WHERE item='" + item + "';");
        db.close();
    }
    public void update_Top(int item, int value) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE TopDB SET value=" + value + " WHERE item='" + item + "';");
        db.close();
    }
    public void update_Bottom(int item, int value) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE BottomDB SET value=" + value + " WHERE item='" + item + "';");
        db.close();
    }
    public void update_Shoes(int item, int value) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE ShoesDB SET value=" + value + " WHERE item='" + item + "';");
        db.close();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void delete_Log() {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM LogDB");
        db.close();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Boolean getCheck(String item) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        Boolean result = false;
        Cursor cursor = db.rawQuery("SELECT * FROM MainDB", null);
        while (cursor.moveToNext()) {
            if(cursor.getString(1).equals(item)){
                result = true;
                break;
            }

        }

        return result;
    }
    public Boolean getCheck_Day(String item) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        Boolean result = false;
        Cursor cursor = db.rawQuery("SELECT * FROM DayDB", null);
        while (cursor.moveToNext()) {
            if(cursor.getString(1).equals(item)){
                result = true;
                break;
            }

        }

        return result;
    }
    public Boolean getCheck_Weopon(int item) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        Boolean result = false;
        Cursor cursor = db.rawQuery("SELECT * FROM WeoponDB", null);
        while (cursor.moveToNext()) {
            if(cursor.getInt(1) == item){
                result = true;
                break;
            }

        }

        return result;
    }
    public Boolean getCheck_Head(int item) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        Boolean result = false;
        Cursor cursor = db.rawQuery("SELECT * FROM HeadDB", null);
        while (cursor.moveToNext()) {
            if(cursor.getInt(1) == item){
                result = true;
                break;
            }

        }

        return result;
    }
    public Boolean getCheck_Top(int item) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        Boolean result = false;
        Cursor cursor = db.rawQuery("SELECT * FROM TopDB", null);
        while (cursor.moveToNext()) {
            if(cursor.getInt(1) == item){
                result = true;
                break;
            }

        }

        return result;
    }
    public Boolean getCheck_Bottom(int item) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        Boolean result = false;
        Cursor cursor = db.rawQuery("SELECT * FROM BottomDB", null);
        while (cursor.moveToNext()) {
            if(cursor.getInt(1) == item){
                result = true;
                break;
            }

        }

        return result;
    }
    public Boolean getCheck_Shoes(int item) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        Boolean result = false;
        Cursor cursor = db.rawQuery("SELECT * FROM ShoesDB", null);
        while (cursor.moveToNext()) {
            if(cursor.getInt(1) == item){
                result = true;
                break;
            }

        }

        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int getValue(String item) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        int result = 0;

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM MainDB", null);

        while (cursor.moveToNext()) {
            if(cursor.getString(1).equals(item)){
                result = cursor.getInt(2);
                break;
            }
        }
        db.close();
        return result;
    }
    public String getValue_Day(String item) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM DayDB", null);

        while (cursor.moveToNext()) {
            if(cursor.getString(1).equals(item)){
                result = cursor.getString(2);
                break;
            }
        }
        db.close();
        return result;
    }
    public int getInt_Weopon(int item, int i) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        int result = 0;

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM WeoponDB", null);

        while (cursor.moveToNext()) {
            if(cursor.getInt(1) == item){
                result = cursor.getInt(i);
                break;
            }
        }
        db.close();
        return result;
    }
    public int getInt_Head(int item, int i) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        int result = 0;

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM HeadDB", null);

        while (cursor.moveToNext()) {
            if(cursor.getInt(1) == item){
                result = cursor.getInt(i);
                break;
            }
        }
        db.close();
        return result;
    }
    public int getInt_Top(int item, int i) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        int result = 0;

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM TopDB", null);

        while (cursor.moveToNext()) {
            if(cursor.getInt(1) == item){
                result = cursor.getInt(i);
                break;
            }
        }
        db.close();
        return result;
    }
    public int getInt_Bottom(int item, int i) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        int result = 0;

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM BottomDB", null);

        while (cursor.moveToNext()) {
            if(cursor.getInt(1) == item){
                result = cursor.getInt(i);
                break;
            }
        }
        db.close();
        return result;
    }
    public int getInt_Shoes(int item, int i) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        int result = 0;

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM ShoesDB", null);

        while (cursor.moveToNext()) {
            if(cursor.getInt(1) == item){
                result = cursor.getInt(i);
                break;
            }
        }
        db.close();
        return result;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getString_Weopon(int item, int i) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM WeoponDB", null);

        while (cursor.moveToNext()) {
            if(cursor.getInt(1) == item){
                result = cursor.getString(i);
                break;
            }
        }
        db.close();
        return result;
    }
    public String getString_Head(int item, int i) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM HeadDB", null);

        while (cursor.moveToNext()) {
            if(cursor.getInt(1) == item){
                result = cursor.getString(i);
                break;
            }
        }
        db.close();
        return result;
    }
    public String getString_Top(int item, int i) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM TopDB", null);

        while (cursor.moveToNext()) {
            if(cursor.getInt(1) == item){
                result = cursor.getString(i);
                break;
            }
        }
        db.close();
        return result;
    }
    public String getString_Bottom(int item, int i) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM BottomDB", null);

        while (cursor.moveToNext()) {
            if(cursor.getInt(1) == item){
                result = cursor.getString(i);
                break;
            }
        }
        db.close();
        return result;
    }
    public String getString_Shoes(int item, int i) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM ShoesDB", null);

        while (cursor.moveToNext()) {
            if(cursor.getInt(1) == item){
                result = cursor.getString(i);
                break;
            }
        }
        db.close();
        return result;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM MainDB", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0)
                    + " : "
                    + cursor.getString(1)
                    + " | "
                    + cursor.getInt(2)
                    + "\n";
        }
        db.close();
        return result;
    }
    public String getResult_Log() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result[] = new String[999999];

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM LogDB", null);

        int i = 1;
        while (cursor.moveToNext()) {
            result[i] = cursor.getString(0)
                    + " : "
                    + cursor.getString(1)
                    + " | "
                    + cursor.getString(2)
                    + " | "
                    + cursor.getInt(3)/3600000 + "시간 "+ cursor.getInt(3)%3600000/60000 + "분 "+ cursor.getInt(3)%3600000%60000/1000 + "초"
                    + "\n";
            i++;
        }
        db.close();
        String result_back = "";
        for(int j = i; j >= 1;j--){
            if(result[j] != null)
                result_back += result[j];
        }
        return result_back;
    }

}
