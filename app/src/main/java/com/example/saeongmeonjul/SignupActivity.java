package com.example.saeongmeonjul;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private Button mBtRegisterationPlus;
    private Button mBtFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // init
        mToolbar = findViewById(R.id.toolbar);
        mBtRegisterationPlus = findViewById(R.id.btRegistrationPlus);
        mBtFinish = findViewById(R.id.btFinish);

        // 툴바 세팅
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        mBtRegisterationPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btRegistrationPlus:
                        Intent intent = new Intent(SignupActivity.this, PopupActivity.class);
                        startActivityForResult(intent, 1);
                        break;
                }
            }
        });

        mBtFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
