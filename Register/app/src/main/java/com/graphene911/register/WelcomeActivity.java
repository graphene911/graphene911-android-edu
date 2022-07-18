package com.graphene911.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    TextView txtWelcome;
    TextView txtSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        String email = getIntent().getStringExtra("email");

        txtWelcome = findViewById(R.id.txtWelcome);
        txtSp = findViewById(R.id.txtSp);

        txtWelcome.setText(email + " 님 안녕하세요");

        // SharedPreferences 에서 데이터를 가져오는 방법
        SharedPreferences sp = getSharedPreferences("Register", MODE_PRIVATE);
        String savedEmail = sp.getString("email", "없음");
        txtSp.setText("저장되었던 email은 : " + savedEmail);

    }
}