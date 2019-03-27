package com.sysoutachieve.testsqllite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private MySQLiteOpenHelper helper;
    String dbName = "st_file.db";
    int dbVersion = 3; // 데이터베이스 버전
    private SQLiteDatabase db;
    String tag = "SQLite"; // Log 에 사용할 tag

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new MySQLiteOpenHelper(
                this,  // 현재 화면의 제어권자
                dbName,// db 이름
                null,  // 커서팩토리-null : 표준커서가 사용됨
                dbVersion);
        try {
//         // 데이터베이스 객체를 얻어오는 다른 간단한 방법
//         db = openOrCreateDatabase(dbName,  // 데이터베이스파일 이름
//                          Context.MODE_PRIVATE, // 파일 모드
//                          null);    // 커서 팩토리
//
//         String sql = "create table mytable(id integer primary key autoincrement, name text);";
//        db.execSQL(sql);


            db = helper.getWritableDatabase(); // 읽고 쓸수 있는 DB

            String sqlDropTbl = "DELETE FROM mytable" ;
            db.execSQL(sqlDropTbl) ;        //table 삭제
            db.execSQL("VACUUM");       //table 비우기

            //db = helper.getReadableDatabase(); // 읽기 전용 DB select문
        } catch (SQLiteException e) {
            e.printStackTrace();
            Log.e(tag, "데이터베이스를 얻어올 수 없음");
            finish(); // 액티비티 종료
        }
        insert (); // insert 문 - 삽입추가
        select(); // select 문 - 조회
        update(); // update 문 - 수정변경
        delete(); // delete 문 - 삭제 행제거
        select();
    }   //onCreate fin.

    void delete() {
        db.execSQL("delete from mytable where id=2;");
        Log.d(tag, "delete 완료");
    }

    void update() {
        db.execSQL("update mytable set name='Park' where id=5;");
        Log.d(tag, "update 완료");
    }

    void select() {
        Cursor c = db.rawQuery("select * from mytable;", null);
        while(c.moveToNext()) {
            int id = c.getInt(0);
            String name = c.getString(c.getColumnIndex("name"));
            String job = c.getString(c.getColumnIndex("job"));
            Log.d(tag,"id:"+id+",name:"+name +",job:"+job);
        }
    }

    void insert () {
        db.execSQL("insert into mytable (name, job) values('Seo', 'student');");
        db.execSQL("insert into mytable (name, job) values('Choi', 'teacher');");
        db.execSQL("insert into mytable (name, job) values('Park', 'none');");
        db.execSQL("insert into mytable (name, job) values('Heo', 'driver');");
        db.execSQL("insert into mytable (name, job) values('Kim', 'de');");
        Log.d(tag, "insert 성공~!");
    }
}
