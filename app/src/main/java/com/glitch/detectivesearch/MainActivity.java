package com.glitch.detectivesearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.glitch.detectivesearch.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    Button start_btn;
    Button options_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start_btn=findViewById(R.id.start_btn);
        options_btn=findViewById(R.id.options_btn);
        final int CASE_COUNT=10;
        saveData(CASE_COUNT,"case_count");
        final String PREFS_NAME = "MyPrefsFile";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something
            Log.d("Comments", "First time");
            String arr[]=new String[CASE_COUNT];
            String arr_evals[]=new String[CASE_COUNT];
            arr[0]="true";
            arr_evals[0]="false";
            for(int c=1;c<CASE_COUNT;c++){
                arr[c]="false";
                arr_evals[c]="false";
            }

            ArrayList<String> list_cases = new ArrayList<String>();
            Collections.addAll(list_cases, arr);
            saveArrayList(list_cases,"cases_enabled");
            saveData(0,"mode");
            saveData(0,"easy");
            saveData(1,"photo");
            //saveData(CASE_COUNT,"case_count");

            ArrayList<String> list_evals = new ArrayList<String>();
            Collections.addAll(list_evals, arr_evals);
            saveArrayList(list_evals,"evals_enabled");

            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).apply();
        }

        ArrayList<String> cases=getArrayList("cases_enabled");
        final String[] cases_enabled=new String[cases.size()];
        cases.toArray(cases_enabled);

        ArrayList<String> evals=getArrayList("evals_enabled");
        final String[] evals_enabled=new String[evals.size()];
        evals.toArray(evals_enabled);

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent myIntent = new Intent(MainActivity.this, CasesActivity.class);
            Bundle string_array_bundle = new Bundle();
            string_array_bundle.putStringArray("cases_enabled",cases_enabled);
            string_array_bundle.putStringArray("evals_enabled",evals_enabled);
            myIntent.putExtras(string_array_bundle);
            startActivity(myIntent);
            }
        });

        options_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent myIntent = new Intent(MainActivity.this, OptionsActivity.class);
            startActivity(myIntent);
            }
        });
    }

    public void saveArrayList(ArrayList<String> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void saveData(int TEXT,String KEY) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY, TEXT);
        editor.apply();
    }

    public int loadData(String KEY) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        int text = prefs.getInt(KEY, 0);
        return text;
    }
}
