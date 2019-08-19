package com.example.saeongmeonjul;

import android.bluetooth.BluetoothAdapter;
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

import static app.akexorcist.bluetotohspp.library.BluetoothState.REQUEST_ENABLE_BT;

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
        bluetoothTest();

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

    public void bluetoothTest(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null){
            // 장치가 블루투스를 지원하지 않는 경우
            finish();
        }
        else {
            // 장치가 블루투스를 지원하는 경우
            if(!mBluetoothAdapter.isEnabled()){
                // 블루투스를 지원하지만 비활성 상태인 경우
                // 블루투스를 활성 상태로 바꾸기 위해 사용자 동의 요청
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
            else {
                // 블루투스를 지원하며 활성 상태인 경우
                // 페어링된 기기 목록을 보여주고 연결할 장치를 선택
            }
        }
    }
}
