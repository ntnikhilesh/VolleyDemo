package com.example.dell.volleydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class ShowJsonArray extends AppCompatActivity implements OnSuccess{
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Contact> arrayList = new ArrayList<>();
    private OnSuccess onSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_array);

        recyclerView=(RecyclerView)findViewById(R.id.fav_ques_card_recycler_view);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        BackgroundTask backgroundTask=new BackgroundTask(ShowJsonArray.this, new OnSuccess() {
            @Override
            public void getResult(ArrayList<Contact> contacts) {
                adapter=new MyAdapter(contacts);
                recyclerView.setAdapter(adapter);
            }
        });
        arrayList=backgroundTask.getList();
        //Log.d("ntarray","-"+arrayList.size());

    }

    @Override
    public void getResult(ArrayList<Contact> contacts) {

    }
}
