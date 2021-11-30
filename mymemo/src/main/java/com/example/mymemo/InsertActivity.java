package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {

    EditText txtName, txtResult, txtAge, txtNo, txtMobile;
    Button btnIns, btnSel, btnSelNo, btnDel, btnUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        txtName = findViewById(R.id.txtName);
        txtAge =findViewById(R.id.txtAge);
        txtNo = findViewById(R.id.txtNo);
        txtMobile = findViewById(R.id.txtMobile);

        txtResult = findViewById(R.id.txtResult);

        btnIns = findViewById(R.id.btnIns);
        btnSel = findViewById(R.id.btnSel);
        btnSelNo =findViewById(R.id.btnSelNo);
        btnDel = findViewById(R.id.btnDel);
        btnUp =findViewById(R.id.btnUp);

        //db Insert
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext()); //DB, table 생성
        btnIns.setOnClickListener(v-> {
            SQLiteDatabase db = dbHelper.getWritableDatabase(); //DB
            String sqlInsert = "INSERT INTO emp " +
                    "(name, age, mobile) VALUES ('"+ txtName.getText().toString() +"', '"+txtAge.getText().toString()+"' , '"+txtMobile.getText().toString()+"')" ;
            db.execSQL(sqlInsert);
            //db.execSQL(sqlInsert, new Object[]{name,20,"010-"}); 위에 값 들어가는 자리에 ?로 하고 거기에 들어가는 값을 배열로 만들어서 넘겨준다
            db.close();

            Intent mainintent = new Intent(getApplicationContext(), MainActivity.class);
            mainintent.putExtra("msg","return test");
            setResult(1, mainintent);
            finish();
        });

        //단건 조회
        btnSelNo.setOnClickListener(v->{
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String sql = "select name,age,mobile from emp where _id=" + txtNo.getText();
            Cursor cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()){
                String name = cursor.getString(0);
                int age = cursor.getInt(1);
                String mobile = cursor.getString(2);

                txtName.setText(name);
                txtAge.setText(String.valueOf(age));
                txtMobile.setText(mobile);
            }
        });

        //삭제  db.delete(테이블이름, "_id=?", new String[]{_id});
        btnDel.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String id = txtNo.getText().toString();
            db.delete("emp", "_id=?", new String[]{id});
            db.close();

            Intent mainintent = new Intent(getApplicationContext(), MainActivity.class);
            mainintent.putExtra("msg","return test");
            setResult(2, mainintent);
            finish();
        });

        //수정
        btnUp.setOnClickListener(v-> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String sql = "update emp set name=?,age=?,mobile=? where _id=?";

            String no = txtNo.getText().toString();
            String name = txtName.getText().toString();
            String age = txtAge.getText().toString();
            String mobile = txtMobile.getText().toString();

            db.execSQL(sql, new Object[]{
                    name,age,mobile,no
            });
            db.close();

            Intent mainintent = new Intent(getApplicationContext(), MainActivity.class);
            mainintent.putExtra("msg","return test");
            setResult(3, mainintent);
            finish();

        });

    }
}