package com.example.todoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    public ArrayList<Tasks> itemList;

    public ArrayAdapter<Tasks> adapter;

    public RecyclerView layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.mainLayout);
        layout.setLayoutManager(new LinearLayoutManager(this));


        Intent intent = getIntent();

       itemList = PrefConfig.readItemList(getApplicationContext());

        String stupdatedList = intent.getStringExtra("New_Item");

        if(stupdatedList == null && itemList == null)
        {
            itemList = new ArrayList<Tasks>();
            Tasks newTask = new Tasks("default", "default");

            itemList.add(newTask);

            Log.d("message", "no hay nada rey");
        }
        else
        {
            if(stupdatedList != null)
            {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Tasks>>(){}.getType();
                ArrayList<Tasks> updatedList = gson.fromJson(stupdatedList, type);
                itemList = updatedList;
            }
        }

        adapter_Items adapter = new adapter_Items(itemList);

        layout.setAdapter(adapter);
        //setUpItemOnLongClickListener();
    }

    /*public void setUpItemOnLongClickListener()
    {
        layout.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Context context = getApplicationContext();
                Toast.makeText(context, "Item Removed", Toast.LENGTH_LONG).show();

                itemList.remove(i);
                PrefConfig.writeListInPrefs(getApplicationContext(), itemList);

                adapter.notifyDataSetChanged();
                return true;

            }
        });

        layout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Task Done!", Toast.LENGTH_LONG).show();

                String thisItem = itemList.get(position).toString();

                if(!thisItem.contains("Done!"))
                {
                    itemList.set(position, thisItem + "   Done!");
                    PrefConfig.writeListInPrefs(getApplicationContext(), itemList);
                }
                adapter.notifyDataSetChanged();

            }
        });

    }*/

    public void addItem(View view)
    {
        Intent intent = new Intent(this, AddNewItemActivity.class);
        Gson gson = new Gson();
        String JsonList = gson.toJson(itemList);

        intent.putExtra("item_List",  JsonList);
        startActivity(intent);
    }
}