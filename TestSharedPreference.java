package com.sysoutachieve.testshared;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("1", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.putString("a", null).commit();

        String str = sharedPreferences.getString("a", "12");

        Log.d("SHARED", str+"");

    }
}
