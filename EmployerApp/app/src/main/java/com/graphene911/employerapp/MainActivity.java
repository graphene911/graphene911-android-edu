package com.graphene911.employerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.graphene911.employerapp.adapter.EmployeeAdapter;
import com.graphene911.employerapp.model.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    // Adapter
    EmployeeAdapter adapter;
    // ArrayList 만들기 전 네트워크 통신 설정 필요
    // ArrayList
    ArrayList<Employee> employeeList = new ArrayList<>();
    
    // 네트워크로 데이터를 주고 받을때 동그라미 표시
    ProgressBar progressBar;

//    final String URL = "http://dummy.restapiexample.com/api/v1/employees";
//    위의 URL이 안되면
    final String URL = "https://block1-image-test.s3.ap-northeast-2.amazonaws.com/employees.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        // 네트워크를 통해서 데이터를 받아온다.
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    // 데이터를 받아오는데 성공했을때 처리하는 곳

                        Log.i("MyEmpApp", response.toString());

                        try {
                            // jsonobject에서 내가 가져올 데이터가 문자열이면, getString("키")
                            // 내가 가져올 데이터가 정수이면, getInt("키")
                            // 내가 가져올 데이터가 float이면, getFloat("키")
                            // 내가 가져올 데이터가 double이면, getDouble("키")
                            // 내가 가져올 데이터가 리스트이면, getJsonArray("키")
                            // 내가 가져올 데이터가 딕셔너리형태이면, getJsonObject("키")
                            // 로 데이터를 가져올 수 있다.

                            JSONArray dataList = response.getJSONArray("data");

                            for(int i = 0; i < dataList.length(); i++){
                                JSONObject data = dataList.getJSONObject(i);
                                int id = data.getInt("id");
                                String name = data.getString("employee_name");
                                int age = data.getInt("employee_age");
                                int salary = data.getInt("employee_salary");

                                // 클래스의 객체로 만들어서 메모리에 저장
                                Employee employee = new Employee(id, name, age, salary);

                                // 멤버변수인 리스트에 담아줘야 CPU가 일할 수 있다.
                                employeeList.add(employee);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "네트워크 에러입니다.", Toast.LENGTH_SHORT).show();
                            return;

                        }

                        // 어댑터 만들어서 실제 화면에 데이터를 표시하도록 한다.
                        adapter = new EmployeeAdapter(MainActivity.this, employeeList);
                        recyclerView.setAdapter(adapter);
                        progressBar.setVisibility(View.INVISIBLE);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    // 데이터를 받아오는데 에러가 발생했을때 처리하는 곳
                        Log.i("MyEmpApp", error.toString());
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }

        );
        // 네트워크 통신 실행하는 코드
        progressBar.setVisibility(View.VISIBLE);
        queue.add(request);

    }
}