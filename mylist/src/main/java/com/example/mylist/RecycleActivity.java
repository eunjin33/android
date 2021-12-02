package com.example.mylist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecycleActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    //List<Map<String, String>> list;
    List<MemoVO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        //MAP으로
//        list = new ArrayList<>();
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("name", "홍길동"); map.put("addr", "대구");
//        list.add(map);
//        map = new HashMap<String, String>();
//        map.put("name", "김기자"); map.put("addr", "서울");
//        list.add(map);
//        map = new HashMap<String, String>();
//        map.put("name", "김사또"); map.put("addr", "부산");
//        list.add(map);

        //List<vo>
        list = new ArrayList<MemoVO>();

        MemoVO memoVO = new MemoVO();
        memoVO.setTitle("java"); memoVO.setContent("java content");
        list.add(memoVO);

        memoVO = new MemoVO();
        memoVO.setTitle("spring"); memoVO.setContent("spring content");
        list.add(memoVO);
        memoVO = new MemoVO();
        memoVO.setTitle("android"); memoVO.setContent("android content");
        list.add(memoVO);



        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager  layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyRecycleAdapter(list));


        recyclerView.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage("아이템이 선택됨").create();
        });
    }
}