package com.example.msi.test_ratingbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView ratingtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingbar);
        ratingtxt = (TextView) findViewById(R.id.ratingtxt);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                //  만약 1개미만이면 강제로 1개를 넣었습니다.
                if (ratingBar.getRating() < 1.0f) {
                    ratingBar.setRating(1);
                }
                ratingtxt.setText(ratingBar.getRating()+"");
            }
        });
    }
}
