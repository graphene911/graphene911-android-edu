package com.graphene911.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText editEmail;
    EditText editPassword1;
    EditText editPassword2;
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.editEmail);
        editPassword1 = findViewById(R.id.editPassword1);
        editPassword2 = findViewById(R.id.editPassword2);
        btnRegister = findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 1. email 형식 체크
                String email = editEmail.getText().toString().trim();
                Pattern pattern = android.util.Patterns.EMAIL_ADDRESS;

                // 1 - 1. 유저가 아무것도 입력을 안했거나 이메일 형식이 틀리면
                // 유저에게 이메일을 제대로 입력해 달라고 알려준다.

                if ( email.isEmpty() || !pattern.matcher(email).matches() ){
                    //이메일이 공백이거나 매치된 이메일이 true가 아니면
                    Toast.makeText(MainActivity.this, "email을 확인 해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 2. 비밀번호를 체크한다.
                // 비밀번호의 길이가 6자리 이상 12자리 이하인지 체크한다.
                // 비밀번호가 서로 같은지 체크한다.

                String password1 = editPassword1.getText().toString().trim();
                String password2 = editPassword2.getText().toString().trim();

                if( password1.length() < 6 || password1.length() > 12 ){
                    Toast.makeText(MainActivity.this, "비밀번호의 길이는 6자 이상 12자 이하로 입력해 주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                if( !password1.equals(password2) ){
                    Toast.makeText(MainActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // SharedPreferences 를 이용해서 앱 내의 저장소에 영구적으로 저장하는 방법
                // 앱을 삭제하기 전까지는 영구적으로 저장된다.
                // 단, 앱을 삭제하면 당연히 값이 같이 삭제된다.
                SharedPreferences sp = getSharedPreferences("Register", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("email", email);
                editor.putInt("number", 365);
                editor.apply();


                // 정상적으로 수행 됐을때 새로운 액티비티를 실행한다.
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);


                // 이 액티비티는 위의 새로운 액티비티를 실행 시키고 나서 종료한다.
                finish();

            }
        });


    }
}