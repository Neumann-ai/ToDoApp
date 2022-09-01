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

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {


    public ArrayList<String> itemList;

    public ArrayAdapter<String> adapter;

    public ListView layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.listView);

        Intent intent = getIntent();

        itemList = PrefConfig.readItemList(getApplicationContext());

        ArrayList<String> updatedList = intent.getStringArrayListExtra("New_Item");

        if(updatedList == null && itemList == null)
        {
            itemList = new ArrayList<>();
            Log.d("message", "no hay nada rey");
        }
        else
        {
            if(updatedList != null)
            {
                itemList = updatedList;
            }
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);

        layout.setAdapter(adapter);
        setUpItemOnLongClickListener();
    }

    public void addItem(View view)
    {
        Intent intent = new Intent(this, AddNewItemActivity.class);
        intent.putStringArrayListExtra("item_List", itemList);
        startActivity(intent);
    }

    public void setUpItemOnLongClickListener()
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

    }
}