package com.sysoutachieve.datehandletest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity {


    Date date;
    DateHandler dateHandler = new DateHandler();

    TextView txt_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_date = findViewById(R.id.txt_date);

        String datedumy = "2019-01-29T15:00:00.000+0000";
        String datedumy2 = "2019-01-31T15:00:00.000+0000";
        String datedumy3 = "2019-01-01T15:00:00.000+0000";

        // 날짜 문자열에서 원하는 정보만 꺼내오는 handler 구현 (ex. 년 정보, 월 정보, 일 정보...)
        Log.d("TAGdate : ", dateHandler.dayToString(dateHandler.stringToDate(datedumy))+"" );

        txt_date.setText(dateHandler.dayToString(dateHandler.stringToDate(datedumy))+ "  "+ dateHandler.dayToString(dateHandler.stringToDate(datedumy2))+ "  "+  dateHandler.dayToString(dateHandler.stringToDate(datedumy3)));

    }
}
