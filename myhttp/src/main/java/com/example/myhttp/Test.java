package com.example.myhttp;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        System.out.println("hello");

        //Log.d("test","hello");
        Gson gson = new Gson();
        EmpVO vo = new EmpVO();
        vo.setAge(10);
        vo.setFname("kim");
        String result = gson.toJson(vo);
        System.out.println(result);

        String response = "{\"fname\":\"홍길동\",\"age\":10}";
        EmpVO emp = gson.fromJson(response, EmpVO.class);
        System.out.println(emp.getFname());

        Map<String, Object> map = gson.fromJson(response, Map.class);
        System.out.println(map.get("fname"));
        System.out.println(map.get("age"));

        response = "{\"data\":[{\"fanme\":\"김유신\",\"age\":10}]}";
        map = gson.fromJson(response, Map.class);

        List list = (List)map.get("data"); //배열이라서 list로 받기 List로 형변환
        Map std = (Map)list.get(0);  //배열에 있는 첫번째 요소를 가지고 오는데 객체니까 Map에 담는다
        System.out.println(std.get("fanme"));

        //위에 3줄을 한 줄에 담은 거
        //System.out.println(((Map)((List<Object>)map.get("data")).get(0)).get("fanme"));
        System.out.println("===========");
        ListEmp listEmp = gson.fromJson(response, ListEmp.class);
        System.out.println(listEmp.data.get(0).getFname());

    }
}
