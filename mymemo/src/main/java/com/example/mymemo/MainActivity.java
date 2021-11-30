package com.example.mymemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText txtName, txtResult, txtAge, txtNo, txtMobile;
    Button btnIns, btnSel, btnSelNo, btnDel, btnInsert;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            Toast.makeText(this, "등록완료", Toast.LENGTH_LONG).show();
        } else if (resultCode == 2) {
            Toast.makeText(this, "삭제완료", Toast.LENGTH_LONG).show();
        } else if(resultCode == 3 ) {
            Toast.makeText(this, "수정완료", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = findViewById(R.id.txtName);
        txtAge =findViewById(R.id.txtAge);
        txtNo = findViewById(R.id.txtNo);
        txtMobile = findViewById(R.id.txtMobile);

        txtResult = findViewById(R.id.txtResult);

        btnIns = findViewById(R.id.btnIns);
        btnSel = findViewById(R.id.btnSel);
        btnSelNo =findViewById(R.id.btnSelNo);
        btnDel = findViewById(R.id.btnDel);
        btnInsert = findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), InsertActivity.class);
            startActivityForResult(intent, 1);
        });

//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
//            super.onActivityResult(requestCode, resultCode, data);
//            if(requestCode == 1 ){
//                Toast.makeText(, "", Toast.LENGTH_SHORT).show();
//            }
//        }




        //db select
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        ArrayList<HashMap<String, String>> list  = new ArrayList<HashMap<String, String>>();
        btnSel.setOnClickListener(v->{
            SQLiteDatabase db = dbHelper.getReadableDatabase();
//            String sql = "select name,age,mobile from emp order by name desc";
//            Cursor cursor = db.rawQuery(sql, null);
//
//            while(cursor.moveToNext()){
//                HashMap<String, String> map = new HashMap<String, String>();
//                map.put("name", cursor.getString(0));
//                map.put("age", cursor.getString(1));
//                map.put("mobile", cursor.getString(2));
//                list.add(map);
//            }
//            txtResult.setText(list.toString());

            String sqlp = "";
            String sql = "select name,age,mobile from emp order by name desc";
            Cursor cursor = db.rawQuery(sql, null); //실행결과를 담는 것

            while(cursor.moveToNext()){
                String name = cursor.getString(0);
                int age = cursor.getInt(1);
                String mobile = cursor.getString(2);
                sqlp += name + String.valueOf(age) + mobile +"\n";
            }
                txtResult.setText(sqlp);
        });

    }
}