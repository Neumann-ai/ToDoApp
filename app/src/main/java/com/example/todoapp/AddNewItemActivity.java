package com.example.todoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddNewItemActivity extends AppCompatActivity {

    public String newItem;

    public ArrayList<String> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);

        Intent intent = getIntent();
        itemList = intent.getStringArrayListExtra("item_List");

    }

    public void createItem(View view)
    {
        EditText text = findViewById(R.id.editTextTextPersonName);
        newItem = text.getText().toString();

        if(!newItem.isEmpty())
        {
            itemList.add(newItem);
            PrefConfig.writeListInPrefs(getApplicationContext(), itemList);

            Intent mainIntent = new Intent(this, MainActivity.class);
            mainIntent = mainIntent.putStringArrayListExtra("New_Item", itemList);
            startActivity(mainIntent);
        }
        else
        {
            Context context = getApplicationContext();

            Toast.makeText(context,"You can't create an empty task", Toast.LENGTH_LONG).show();
        }

    }
}