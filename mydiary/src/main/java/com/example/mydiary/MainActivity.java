package com.example.mydiary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    //List<DiaryVO> list;
    ListView listDiary;
    Button btnWriteForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //myadapter 만들고 list에 넣는다?????
        dbHelper = new DBHelper(getApplicationContext());
        ArrayList<DiaryVO> list = DiaryDAO.selectAll(dbHelper);
        //list 결과를 listview에 나오도록 데이터 3개 나오게
        //listview 초기화 adapter(수)지정
        listDiary =findViewById(R.id.listDiary);

        DiaryAdapter adapter = new DiaryAdapter(list);
        listDiary.setAdapter(adapter);

        listDiary.setOnItemClickListener((adapterView, view, i, l )->{
            //Toast.makeText(getApplicationContext(),list.get(i).getContent(), Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("알림창")
                    .setMessage(list.get(i).getTitle())
                    .setPositiveButton("수정", (dialogInterface, a) -> {
                        Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
                        intent.putExtra("id" ,list.get(i).getId());
                        intent.putExtra("title" ,list.get(i).getTitle());
                        intent.putExtra("content", list.get(i).getContent());
                        startActivityForResult(intent, 1);

                        //값을 가지고 activity_write로 이동
                        ((DiaryAdapter)listDiary.getAdapter()).setList(DiaryDAO.selectAll(dbHelper));
                        ((DiaryAdapter)listDiary.getAdapter()).notifyDataSetChanged();
                        //인텐트 만들어서 엑티비티 이동

                    })
                    .setNegativeButton("삭제", (dialogInterface, a) ->{
                       DiaryDAO.delete(dbHelper, list.get(i).getId());
                       //삭제하고 바로 화면에 안 보여서
                       list.remove(i);
                       ((DiaryAdapter)listDiary.getAdapter()).notifyDataSetChanged();
                    })
                    .create()
                    .show();
        });


        //쓰기버튼 이벤트 지정 : writeAct...로 이동
        btnWriteForm = findViewById(R.id.btnWriteForm);

        btnWriteForm.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), WriteActivity.class);
            startActivityForResult(intent, 1);
        });

    }
}