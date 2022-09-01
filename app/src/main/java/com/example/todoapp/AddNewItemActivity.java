package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

        itemList.add(newItem);
        PrefConfig.writeListInPrefs(getApplicationContext(), itemList);


        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent = mainIntent.putStringArrayListExtra("New_Item", itemList);
        startActivity(mainIntent);
    }
}