package com.example.maxile.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public class RecycleViewModel{
        public RecycleViewModel(String title, String detail){
            this.title = title;
            this.detail = detail;
        }
        public String title;
        public String detail;

    }

    public List<RecycleViewModel> datas = new ArrayList<RecycleViewModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0;i<20;i++){
            datas.add(new RecycleViewModel("What is Lorem Ipsum?", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        }
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.my_recycle_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new RecyclerAdapter(datas));

    }
}
