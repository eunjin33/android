package com.example.mydiary;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class WriteActivity extends AppCompatActivity {
    Button btnSave;
    Button btnImage;
    DBHelper dbHelper;
    EditText editTitle;
    EditText editContent;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);
        btnSave = findViewById(R.id.btnSave);

        dbHelper = new DBHelper(getApplicationContext());

        Intent intent = getIntent();//값을 가지고 온다
        //값이 비어있으면 id값을 str에 넣어준다?

        boolean str = TextUtils.isEmpty(intent.getStringExtra("id"));

        btnSave.setOnClickListener(v->{
            DiaryVO vo = new DiaryVO();

        vo.setTitle(editTitle.getText().toString());
        vo.setContent(editContent.getText().toString());

        System.out.println(editTitle.getText().toString());
        System.out.println(editContent.getText().toString());

            //수정
            //str id값이 있으면
            if(str == false) {
                vo.setId(intent.getStringExtra("id"));
                System.out.println("아이디값"+intent.getStringExtra("id"));
                DiaryDAO.update(dbHelper,vo);
                System.out.println("업데이트"+vo);
            }else if(str == true) {
                DiaryDAO.insert(dbHelper,vo);
                System.out.println("인서트"+vo);
            };
            Intent Mainintent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(Mainintent, 2);

        });

    }
}
