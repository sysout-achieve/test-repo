package com.example.msi.handlertest;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.txt);
        Button btn = (Button) findViewById(R.id.btn);

        tv = (TextView) findViewById(R.id.tv);
        tv.setText("바뀐다");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAsyncCalculation();
            }
        });

        final Handler handler = new Handler();
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(2000);
//
//                } catch (Exception ex){
//
//                }
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("안녕");
                    }
                }, 2000);
            }
        });
        th.start();
    }

    private void startAsyncCalculation() {
        AsyncCal task = new AsyncCal();
        task.execute(1, Integer.MAX_VALUE);
    }

    class AsyncCal extends AsyncTask<Integer, Integer, Integer>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            int number = integers[0];
            int count = integers[1];
            int result = 0;
            int per = count /100;

            for(int i = 0; i <count; i++){
                result +=number;
                if(result % per == 0){
                    publishProgress(result/per);
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            txt.setText("result : "+    integer);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tv.setText(values[0]+"percent");
        }
    }


}