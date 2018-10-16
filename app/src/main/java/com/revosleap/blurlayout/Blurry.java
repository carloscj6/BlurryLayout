package com.revosleap.blurlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.revosleap.blurrylayout.BlurryLayout;


public class Blurry extends AppCompatActivity {
    private BlurryLayout blurLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_blurry);
        blurLayout= findViewById(R.id.blurLayout);
       // blurLayout.blurBackground(getResources().getDrawable(R.drawable.past),10);

    }
}
