package com.glitch.detectivesearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class WinActivity extends AppCompatActivity{
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        //Toolbar toolbar = findViewById(R.id.toolbar_detail);
        //setSupportActionBar(toolbar);

        //final int CASE_COUNT=loadData("case_count");
        b = findViewById(R.id.win_btn);
       /* b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int story_number = Objects.requireNonNull(getIntent().getExtras()).getInt("case_number");
                String arr[]=new String[CASE_COUNT];
                for(int x=0;x<arr.length;x++){
                    arr[x]="false";
                }
                for(int x=0;x<(story_number+1);x++){
                    arr[x]="done";
                }

                String arr_eval[]=new String[CASE_COUNT];
                for(int x=0;x<arr_eval.length;x++){
                    arr_eval[x]="false";
                }
                for(int x=0;x<story_number;x++){
                    arr_eval[x]="done";
                }
                if(story_number<arr_eval.length-1){
                    arr_eval[story_number]="true";
                }
                System.out.print(Arrays.toString(arr_eval));
                ArrayList<String> list1 = new ArrayList<String>();
                Collections.addAll(list1, arr);
                saveArrayList(list1,"cases_enabled");

                ArrayList<String> liste = new ArrayList<String>();
                Collections.addAll(liste, arr_eval);
                saveArrayList(liste,"evals_enabled");

                WinActivity.this.finish();
                Intent myIntent = new Intent(WinActivity.this, CasesActivity.class);
                startActivity(myIntent);
            }
        });
    }*/


    /*public int loadData(String KEY) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(WinActivity.this);
        int text = prefs.getInt(KEY, 0);
        return text;
    }*/
    }
}
