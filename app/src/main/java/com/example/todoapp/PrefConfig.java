package com.example.todoapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PrefConfig {

    private static final String LIST_KEY =  "list_key";

    public static void writeListInPrefs(Context context, ArrayList<Tasks> itemList)
    {
        Gson gson = new Gson();
        String jSonString = gson.toJson(itemList);


        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        editor.putString(LIST_KEY, jSonString);
        editor.apply();
    }

    public static ArrayList<Tasks> readItemList(Context context)
    {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        String jSonString = sharedPrefs.getString(LIST_KEY, "");

      //  sharedPrefs.edit().clear();

       Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Tasks>>(){}.getType();
        ArrayList<Tasks> itemList = gson.fromJson(jSonString, type);
        return itemList;
    }



}
