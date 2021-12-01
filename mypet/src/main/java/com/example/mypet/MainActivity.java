package com.example.mypet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CheckBox chkAgree;
    RadioGroup radioGroup;
    RadioButton rdoDog, rdoCat, rdoRabbit;
    Button btnOk;
    ImageView imgPat;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgPat = findViewById(R.id.imgPet);
        chkAgree = findViewById(R.id.ChkAgree);
        radioGroup = findViewById(R.id.RadioGroup);
        btnOk = findViewById(R.id.BtnOk);
        textView = findViewById(R.id.textName);

        rdoDog = findViewById(R.id.RdoDog);
        rdoCat = findViewById(R.id.RdoCat);
        rdoRabbit = findViewById(R.id.RdoRabbit);

        View.OnClickListener handler = v ->{
            switch (v.getId()){
                case R.id.RdoDog:
                    imgPat.setImageResource(R.drawable.dog3);
                    break;
                case R.id.RdoCat:
                    imgPat.setImageResource(R.drawable.cat);
                    break;
                case R.id.RdoRabbit:
                    imgPat.setImageResource(R.drawable.rabbit);
                    break;
            }
        };
        rdoDog.setOnClickListener(handler);
        rdoCat.setOnClickListener(handler);
        rdoRabbit.setOnClickListener(handler);

        chkAgree.setOnClickListener( v -> {
            if (chkAgree.isChecked()){
                imgPat.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.VISIBLE);
                btnOk.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
            }else{
                imgPat.setVisibility(View.INVISIBLE);
                radioGroup.setVisibility(View.INVISIBLE);
                btnOk.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
            }
        });
    }
}