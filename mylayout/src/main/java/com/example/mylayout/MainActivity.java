package com.example.mylayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridLayout linear;
    int startNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linear = findViewById(R.id.linear);
        Integer strs [] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        List<Integer> numList = Arrays.asList(strs);


        View.OnClickListener handler = v -> {
            //Toast.makeText(this, "클릭됨", Toast.LENGTH_LONG).show();
            //클릭한 버튼의 text(숫자값)을 읽어서 startNum 같다면
            //startNum ++
            //버튼의 text값을 지운다(클리어)
            //16번까지 도착했으며 게임완료 toast

            int btnNum = Integer.parseInt(((Button)v).getText().toString());
            if( btnNum == startNum ){
                startNum++;
                //값을 비워주고
                ((Button)v).setText("");
            } if(btnNum == 16){
                Toast.makeText(this, "게임종료", Toast.LENGTH_SHORT).show();
            }

        };

        Collections.shuffle(numList);

        //1차원 배열 16개의 임의의 순서로 Collections.shuffle()
        for(int i=1; i<=16; i++){
            Button btn = new Button(this);
            btn.setText(String.valueOf(i));
            linear.addView(btn);
            btn.setOnClickListener(handler);
        }

    }
}