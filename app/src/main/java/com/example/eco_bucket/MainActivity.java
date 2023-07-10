package com.example.eco_bucket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 6000;
    ImageView img , img2 ,img3;
    LottieAnimationView lottieAnimationView;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.image);
        lottieAnimationView = findViewById(R.id.lottie);
        t1 = findViewById(R.id.email);
        img2 = findViewById(R.id.imageView6);
        img3 = findViewById(R.id.imageView7);

        img.animate().translationY(-2800).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(2000).setDuration(1000).setStartDelay(4000);
        t1.animate().translationY(2000).setDuration(1000).setStartDelay(4000);
        img3.animate().translationY(-2500).setDuration(1000).setStartDelay(3000);
        img2.animate().translationY(-2500).setDuration(1000).setStartDelay(3000);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,SignIn.class));
                finish();
            }
        },SPLASH_SCREEN);

    }


}