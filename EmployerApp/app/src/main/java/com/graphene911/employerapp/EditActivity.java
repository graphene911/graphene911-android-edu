package com.graphene911.employerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.graphene911.employerapp.model.Employee;

public class EditActivity extends AppCompatActivity {

    EditText editAge;
    EditText editSalary;
    Button btnSave;

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        // 액션바 타이틀 셋팅
        getSupportActionBar().setTitle(R.string.title_edit);
        // 액션바에 뒤로가기 버튼 셋팅
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 다른 액티비티로부터 받아온 데이터를 처리
        Employee employee = (Employee) getIntent().getSerializableExtra("employee");
        index = getIntent().getIntExtra("index", 0);

        editAge = findViewById(R.id.editAge);
        editSalary = findViewById(R.id.editSalary);
        btnSave = findViewById(R.id.btnSave);

        editAge.setText(employee.age+"");
        editSalary.setText(employee.salary+"");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ageStr = editAge.getText().toString().trim();
                String salaryStr = editSalary.getText().toString().trim();

                int age = Integer.valueOf(ageStr).intValue();
                int salary = Integer.valueOf(salaryStr).intValue();

                Intent intent = new Intent();
                intent.putExtra("age", age);
                intent.putExtra("salary", salary);
                // 이 두개정보로는 정보가 부족하다!!!
                intent.putExtra("index", index);

                setResult(RESULT_OK, intent);

                finish();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        // 1. finish() 이용
        finish();

        // 2. 기계의 백버튼 눌렀을때 호출되는 콜백함수를 이용
//        onBackPressed();

        return true;
    }
}