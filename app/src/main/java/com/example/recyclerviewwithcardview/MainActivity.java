package com.example.recyclerviewwithcardview;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView rec;
    myAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Recycler and card View");


        //Simple vertical layout
        rec = (RecyclerView) findViewById(R.id.recView);
        //rec.setLayoutManager(new LinearLayoutManager(this));

        adapter = new myAdapter(dataqueue(), getApplicationContext());//get Application contex for click listener in RV
        rec.setAdapter(adapter);

        //horizontal layout as a aid in

        //LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        //rec.setLayoutManager(layoutManager);


        //Grid view
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rec.setLayoutManager(gridLayoutManager);

    }

    public ArrayList<Model> dataqueue() {
        ArrayList<Model> holder = new ArrayList<>();

        Model ob1 = new Model();
        ob1.setHeader("C programing");
        ob1.setDesc("Programing Language");
        ob1.setImagName(R.drawable.c);
        holder.add(ob1);

        Model ob2 = new Model();
        ob2.setHeader("C++ programing");
        ob2.setDesc("Programing Language");
        ob2.setImagName(R.drawable.cpp);
        holder.add(ob2);

        Model ob3 = new Model();
        ob3.setHeader("Java programing");
        ob3.setDesc("Object Oriented Programing Language");
        ob3.setImagName(R.drawable.java);
        holder.add(ob3);

        Model ob4 = new Model();
        ob4.setHeader("HTML");
        ob4.setDesc("Markup Language");
        ob4.setImagName(R.drawable.html);
        holder.add(ob4);

        Model ob5 = new Model();
        ob5.setHeader("css");
        ob5.setDesc("mechanism for adding style");
        ob5.setImagName(R.drawable.css);
        holder.add(ob5);

        Model ob6 = new Model();
        ob6.setHeader("Ruby");
        ob6.setDesc("used in Rails applications.");
        ob6.setImagName(R.drawable.ruby);
        holder.add(ob6);
        return holder;


    }

    //for searchin

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        MenuItem item = menu.findItem(R.id.search_itm);

        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}

/*
 * recycler view add in main activity
 * rec define
 * create xml file with card view
 * create my View Holdr Class
 * create new new  model class=model in this create 3private data and create gatter satter
 *create my Adapter class in this create array list >>create constructer>>LayoutInflater>>Binding>>in getItemCount Retur.data.size
 * after going to MainActivity adapter data
 *
 * apne ko new activity mai detail dikani h uske liye..
 *   step 1adapter=new myAdapter(dataqueue(),getApplicationContext());
 * go into my adapter create context  in constructor after in my Adapter in onBindViewHolder create final Model temp variable after create click listener in the click listener create Intent after this we code into mainActivity2
 * search Item---
 *1 create searchbaar menu >>main menu Create onCreate option menu >>after go to myAdapter create backup Array List After In this implement Filterable afte come main activity
 * */
