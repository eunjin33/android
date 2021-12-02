package com.example.mydiary;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
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
        boolean str = TextUtils.isEmpty(intent.getStringExtra("id"));

        btnSave.setOnClickListener(v->{

            DiaryVO vo = new DiaryVO();

        vo.setTitle(editTitle.getText().toString());
        vo.setContent(editContent.getText().toString());
            DiaryDAO.insert(dbHelper, vo);


            //수정
//            if(str  ) {
//                DiaryDAO.update();
//                //등록
//            }else if() {
//                DiaryDAO.insert();
//            };

            Intent Mainintent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(Mainintent);

        });

    }
}
