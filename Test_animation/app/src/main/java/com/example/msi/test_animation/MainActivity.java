package com.example.msi.test_animation;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
ImageView img;
    ImageView img_1;
    ImageView img_2;
    ImageView img_3;
    ImageView img_4;
    Handler handler;
private void openimg(final ImageView i1, final ImageView i2, final int time){
    handler = new Handler();
    Thread th2 = new Thread(new Runnable() {
        @Override
        public void run() {
            try{
                Thread.sleep(time);
            }catch (Exception e){

            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    i1.setImageResource(0);
                    i2.setImageResource(R.drawable.ic_launcher_foreground);
                }
            });
        }
    });
    th2.start();
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button a = (Button) findViewById(R.id.btn1);
        Button b = (Button) findViewById(R.id.btn2);
        Button c = (Button) findViewById(R.id.btn3);
        final ImageView img1 = (ImageView) findViewById(R.id.img1);
        final ImageView img2 = (ImageView) findViewById(R.id.img2);
        final ImageView img3 = (ImageView) findViewById(R.id.img3);
        img_1 = (ImageView) findViewById(R.id.img_tp1);
        img_2 = (ImageView) findViewById(R.id.img_tp2);
        img_3 = (ImageView) findViewById(R.id.img_tp3);
        img_4 = (ImageView) findViewById(R.id.img_tp4);

        img_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setImageResource(R.drawable.ic_launcher_background);
            }
        });
        img_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setImageResource(R.drawable.ic_launcher_background);
            }
        });
        img_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setImageResource(R.drawable.ic_launcher_background);
            }
        });
        img_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setImageResource(R.drawable.ic_launcher_background);
            }
        });



        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_run);
                img1.startAnimation(animation);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_alpha);
                img2.startAnimation(animation1);
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                img3.setImageAlpha(100);
//                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_rotate);
                final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_run);
                Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_alpha);
                img3.startAnimation(animation1);
//                img3.startAnimation(animation);


                            openimg(img_1, img_1,250);
                            openimg(img_1, img_2,500);
                            openimg(img_2, img_3,750);
                            openimg(img_3, img_4,1000);



                Thread th4 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Thread.sleep(250);
                        }catch (Exception e){

                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                img_1.setImageResource(R.drawable.transparent);
                            }
                        });
                    }
                });
                Thread th = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Thread.sleep(1000);
                        }catch (Exception e){
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                img3.setImageResource(0);
                                img= (ImageView) findViewById(R.id.img4);
                                img.setImageResource(R.drawable.point_img);
//                                img.setImageAlpha(1);
                                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim_rotate);
                                img.startAnimation(animation2);
                            }

                        });
                        try {
                            Thread.sleep(2000);
                        }catch (Exception e){

                        }handler.post(new Runnable() {
                            @Override
                            public void run() {
                                img.startAnimation(animation);

                            }
                        });
                    }
                });
             th.start();
            }
        });

    }
}
