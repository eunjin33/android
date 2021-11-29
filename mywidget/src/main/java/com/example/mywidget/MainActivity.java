package com.example.mywidget;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn, btn2, btn3, btn4;
    EditText txtNum1, txtNum2;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button5);
        btn3 = findViewById(R.id.button6);
        btn4 = findViewById(R.id.button7);
        txtNum1 = findViewById(R.id.txtNum1);
        txtNum2 = findViewById(R.id.txtNum2);
        tv = findViewById(R.id.textView6);

        //익명 클래스로 바꿔? 객체 생성 클래스 선언 한 번에
//        View.OnClickListener handler = new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                System.out.println("클릭됨");
//                Toast.makeText(null, "클릭!!!", Toast.LENGTH_LONG).show();

        //위에 식을 =>람다식으로(하나의 추상메서드가 있는 인터페이스인 경우에만)
//                View.OnClickListener handler = (view) -> {
//                        System.out.println("클릭됨");
//                        Toast.makeText(null, "클릭!!!", Toast.LENGTH_LONG).show();
//        View.OnClickListener handler = view -> {
//            System.out.println("란다로표현");
//        };

        //};
        //btn.setOnClickListener(new ClickHandler());
        //btn.setOnClickListener(handler);
        //btn.setOnClickListener(v->{
        //System.out.println("람다 클릭됨");
        //계산 editText 입력값들을 더해서 textview에 출력 parseInt

//            int num1 = Integer.parseInt(txtNum1.getText().toString());
//            int num2 = Integer.parseInt(txtNum2.getText().toString());
//            String result = String.valueOf(num1 + num2);
//            tv.setText(result);

//            String n1 = txtNum1.getText().toString();
//            String n2 = txtNum2.getText().toString();
//            int result = Integer.parseInt(n1) + Integer.parseInt(n2);
//            tv.setText(String.valueOf(result));
//        });

        View.OnClickListener handler = v -> {
            int n1 = Integer.parseInt(txtNum1.getText().toString());
            int n2 = Integer.parseInt(txtNum2.getText().toString());
            int result = 0;
            switch (v.getId()) {
                case R.id.button:
                    result = n1 + n2;
                    break;
                case R.id.button5:
                    result = n1 - n2;
                    break;
                case R.id.button6:
                    result = n1 * n2;
                    break;
                case R.id.button7:
                    result = (n1 / n2);
                    break;
            }
            tv.setText(String.valueOf(result));
        };
        btn.setOnClickListener(handler);
        btn2.setOnClickListener(handler);
        btn3.setOnClickListener(handler);
        btn4.setOnClickListener(handler);
    }

//
//        btn2.setOnClickListener( v ->{
//            String n1 = txtNum1.getText().toString();
//            String n2 = txtNum2.getText().toString();
//            int result = Integer.parseInt(n1) - Integer.parseInt(n2);
//            tv.setText(String.valueOf(result));
//        });
//
//        btn3.setOnClickListener( v ->{
//            String n1 = txtNum1.getText().toString();
//            String n2 = txtNum2.getText().toString();
//            int result = Integer.parseInt(n1) * Integer.parseInt(n2);
//            tv.setText(String.valueOf(result));
//        });
//
//        btn4.setOnClickListener( v ->{
//            String n1 = txtNum1.getText().toString();
//            String n2 = txtNum2.getText().toString();
//            double result = Integer.parseInt(n1) / Integer.parseInt(n2);
//            tv.setText(String.valueOf(result));
//        });
//    }
//    class ClickHandler  implements View.OnClickListener {
//        @Override
//        public void onClick(View view) {
//            //context 부모가 누군지 알려주는 거?
//            Toast.makeText(null, "클릭!!!", Toast.LENGTH_LONG).show();
//
//            System.out.println("클릭됨");
//            //Toast.makeText(getApplicationContext(), "클릭됨", Toast.LENGTH_LONG).show();
//
//        }
//    }
}

