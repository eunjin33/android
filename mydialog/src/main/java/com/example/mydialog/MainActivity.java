package com.example.mydialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button, button4, button2, button3;

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        String[] city = new String[]{"대구","서울","부산"};

        button.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("알림창")
                    .setMessage("alert")
                    .setPositiveButton("수정", (dialogInterface, i)->{
                        Log.d("alert", "수정버튼");
                    })
                    .setNegativeButton("삭제", (di, i)->{
                        Log.d("alert", "삭제버튼");
                    })
                    .create()
                    .show();

        });
        ArrayList selectedItems = new ArrayList<Integer>();
        button2.setOnClickListener(v->{

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("지역").setMultiChoiceItems(city, null,
                    (dialog, which, isChecked)-> {
                        if (isChecked) {
                            //which 값이 인트가 넘어온다
                            selectedItems.add(which);
                        } else if (selectedItems.contains(which)) {
                            selectedItems.remove(which);
                        }
                    })
                    .setPositiveButton("확인", (dialog, id)->{
                        //city 배열에서 선택된 값들을 출력..
                        //city.straem
                        for(Object i:selectedItems){
                            int pos = ((Integer)i).intValue();
                            Log.d("alert", city[pos]);
                        }
                    })
                    .setNegativeButton("닫기", (di,i)->{
                    })
                    .create()
                    .show();
        });
        //커스텀
        button3.setOnClickListener(v->{ customModal(); });
        button4.setOnClickListener(v->{});

    }
    private void customModal(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //inflater 뷰를 찾아서 넣기 위해서 사용?
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(R.layout.activity_login)
                .create()
                .show();
    }
}