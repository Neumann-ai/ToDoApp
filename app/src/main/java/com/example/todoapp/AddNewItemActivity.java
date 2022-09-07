package com.example.todoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;



public class AddNewItemActivity extends AppCompatActivity {

    public Tasks newItem;

    public ArrayList<Tasks> itemList;

    public String jsonItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);

        Intent intent = getIntent();
        jsonItemList = intent.getStringExtra("item_List");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Tasks>>(){}.getType();
        itemList = gson.fromJson(jsonItemList, type);
    }

    public void createItem(View view)
    {
        EditText text = findViewById(R.id.editTextTextPersonName);
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        newItem = new Tasks(text.getText().toString(), formater.format(date));


        if(!newItem.getText().isEmpty())
        {
            itemList.add(newItem);
            PrefConfig.writeListInPrefs(getApplicationContext(), itemList);

            Gson gson = new Gson();

            String JsonList = gson.toJson(itemList);

            Intent mainIntent = new Intent(this, MainActivity.class);
            mainIntent = mainIntent.putExtra("New_Item", JsonList);
            startActivity(mainIntent);
        }
        else
        {
            Context context = getApplicationContext();

            Toast.makeText(context,"You can't create an empty task", Toast.LENGTH_LONG).show();
        }

    }
}