package com.example.saeongmeonjul;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 액션바 없애기
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new splashHandler(), 3000);
    }

        private class splashHandler implements Runnable{
            @Override
            public void run() {
                startActivity(new Intent(getApplication(), MainActivity.class));
                SplashActivity.this.finish();
            }
        }

    @Override
    public void onBackPressed() {

    }
}
