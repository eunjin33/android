package com.example.myfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    Button btnRead, btnWrite;
    DatePicker dp;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);
        dp = findViewById(R.id.DatePicker);
        editText= findViewById(R.id.EditText);

        dp.init(2021,11,2,(datePicker, i, i1, i2) -> {
            //i년, i1월, i2일
            //파일을 읽어서 view에 보이도록
            i1 =i1+1;
            String filename = Integer.toString(i)
                    +((i1<10) ? ("0" +i1) : i1)
                    +((i2<10) ? ("0"+i2) : i2) + ".txt";
            fileRead(filename);
        });
        btnWrite.setOnClickListener(v->{
            try {
                String year = Integer.toString(dp.getYear());
                String month = Integer.toString(dp.getMonth()+1);
                String day = Integer.toString( dp.getDayOfMonth());

                String fi = year+month+day;

                FileOutputStream outFs = openFileOutput(fi+".txt", Context.MODE_PRIVATE);
                String str = editText.getText().toString();
                outFs.write(str.getBytes());
                outFs.close();
                Toast.makeText(getApplicationContext(), "파일생성", Toast.LENGTH_SHORT).show();
            }catch (IOException e){
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream inFs = openFileInput("file.txt");
                    byte[] txt = new byte[30];
                    inFs.read(txt);
                    String str = new String(txt);
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                    inFs.close();
                }catch (IOException e){
                    Toast.makeText(getApplicationContext(), "파일없음", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void fileRead(String filename){
        try{
            FileInputStream inFs = openFileInput("filename");
            byte[] txt = new byte[30];
            inFs.read(txt);
            String str = new String(txt);
            editText.setText(str);
            inFs.close();
        }catch (IOException e){
            Toast.makeText(getApplicationContext(), "파일없음", Toast.LENGTH_SHORT).show();
        }
    }
}