package com.example.mylist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    List<Map<String, String>> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);

        //String[] data = new String[]{"","",""}; 아래랑 같은 거 <-직접 배열을 만드는 거
        //String[] data = getResources().getStringArray(R.array.city);

        list = new ArrayList<>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "홍길동"); map.put("addr", "대구");
        list.add(map);
        map = new HashMap<String, String>();
        map.put("name", "김기자"); map.put("addr", "서울");
        list.add(map);
        map = new HashMap<String, String>();
        map.put("name", "김사또"); map.put("addr", "부산");
        list.add(map);

        MyAdapter adapter = new MyAdapter(list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            Toast.makeText(getApplicationContext(), list.get(i).get("name") , Toast.LENGTH_SHORT).show();
        });
    }
}