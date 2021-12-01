package com.example.myhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        Button btnIn = findViewById(R.id.btnIn);
        TextView tv = findViewById(R.id.textName);

        TextView txtId = findViewById(R.id.txtId);
        TextView txtName = findViewById(R.id.txtName);
        TextView txtPassword = findViewById(R.id.txtPassword);
        TextView txtRole = findViewById(R.id.txtRole);

        RequestQueue queue = Volley.newRequestQueue(this);

        btnIn.setOnClickListener(v->{
            String url = "http:///192.168.35.1/insertUser";
            StringRequest request = new StringRequest(Request.Method.POST,url, s->{
                tv.setText(s);
            }, e->{}){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("id", txtId.getText().toString());
                    map.put("name", txtName.getText().toString());
                    map.put("password", txtPassword.getText().toString());
                    map.put("role",  txtRole.getText().toString());
                    return map;
                }
            };
            queue.add(request);
        });

        button.setOnClickListener(v->{
            //String url = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20211130";
            String url ="http:///192.168.35.1/userList";

            //람다식으로 표현하는 것 s 매개변수 , e 에러 났을 때
            StringRequest request = new StringRequest(url, s->{
                //이름과 패스워드만 표시 .Map or UserVO
                //Map<String, Object> map = ;

                 tv.setText(s);
            }, e->{
                Log.d("request", e.toString());
                //System.out.println(e);
            });
            queue.add(request);
        });

    }
}