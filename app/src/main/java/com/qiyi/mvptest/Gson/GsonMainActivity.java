package com.qiyi.mvptest.Gson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qiyi.mvptest.R;

import java.util.ArrayList;
import java.util.List;

public class GsonMainActivity extends AppCompatActivity {
    private TextView tv;
    Gson gson = new Gson();
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_main);
        tv = (TextView) findViewById(R.id.tv);
    }

    public void objToJson(View view) {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person p = new Person();
            p.setName("name" + i);
            p.setAge(i * 5);
            persons.add(p);
        }
        str = gson.toJson(persons);
        tv.setText(str);
    }

    public void jsonToSingleObj(View view) {
        String json = "{'name':'name0','age':0}";
        Person person = gson.fromJson(json, Person.class);
        String str = "name=" + person.getName() + ",age=" + person.getAge();
        tv.setText(str);
    }

    public void jsonToObjSet(View view) {
        List<Person> ps = gson.fromJson(str, new TypeToken<List<Person>>(){}.getType());
        String s = "";
        for (int i = 0; i < ps.size(); i++) {
            Person p = ps.get(i);
            s += p.toString();
        }
        tv.setText(s);
    }

}
