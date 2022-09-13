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


public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {


    public ArrayList<Tasks> itemList;

    public RecyclerView layout;

    public adapter_Items adapterItems;



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

        adapterItems = new adapter_Items(itemList, this );

        layout.setAdapter(adapterItems);

    }

    public void addItem(View view)
    {
        Intent intent = new Intent(this, AddNewItemActivity.class);
        Gson gson = new Gson();
        String JsonList = gson.toJson(itemList);

        intent.putExtra("item_List",  JsonList);
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {
        Tasks doneTask = itemList.get(position);

        String taskText = doneTask.getText();

        if(!doneTask.getText().contains("Done!"))
        {
            doneTask.setText(taskText + " Done!");
            itemList.set(position, doneTask);
            Context context = getApplicationContext();
            Toast.makeText(context, "Task Done!", Toast.LENGTH_LONG).show();
            PrefConfig.writeListInPrefs(getApplicationContext(), itemList);
            adapterItems.notifyDataSetChanged();
        }


    }

    @Override
    public void onItemLongClick(int position) {
        itemList.remove(position);

        Context context = getApplicationContext();
        Toast.makeText(context, "Item Removed", Toast.LENGTH_LONG).show();

        PrefConfig.writeListInPrefs(getApplicationContext(), itemList);

        adapterItems.notifyDataSetChanged();
    }
}