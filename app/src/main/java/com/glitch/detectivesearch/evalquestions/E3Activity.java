package com.glitch.detectivesearch.evalquestions;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.glitch.detectivesearch.R;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class E3Activity extends AppCompatActivity {
    TextView question;
    RadioButton radioButton;
    RadioButton btn_1;
    RadioButton btn_2;
    RadioButton btn_3;
    //RadioButton btn_4;
    Button next_btn;
    LinearLayout l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_evaluations);
        //Toolbar toolbar = findViewById(R.id.toolbar_detail);
        //setSupportActionBar(toolbar);
        l = findViewById(R.id.lll);
        l.setBackgroundResource(R.drawable.eval_background_3);
        question = findViewById(R.id.tvEval);
        //question.setText(R.string.loremipsum);
        final RadioGroup radioGroup = findViewById(R.id.rgEval);
        btn_1 = findViewById(R.id.rbEval1);
        btn_2 = findViewById(R.id.rbEval2);
        btn_3 = findViewById(R.id.rbEval3);
        //btn_4=findViewById(R.id.Radio_btn_4);
        next_btn = findViewById(R.id.btnNextEval);
        next_btn.setText(R.string.next);

        final int story_number = Objects.requireNonNull(getIntent().getExtras()).getInt("case_number");
        if(story_number==5){
            l.setBackgroundResource(R.drawable.eval_error);
        }
        Resources res = getResources();
        int resId = getResources().getIdentifier("eval_"+story_number+"_q3_array", "array", getApplicationInfo().packageName);
        String[] questions_array = res.getStringArray(resId);
        question.setText(questions_array[0]);
        btn_1.setText(questions_array[1]);
        btn_2.setText(questions_array[2]);
        btn_3.setText(questions_array[3]);
        next_btn.setText(R.string.complete);
        final int CASE_COUNT=loadData("case_count");

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String[] arr=new String[CASE_COUNT];
            for(int x=0;x<arr.length;x++){
                arr[x]="false";
            }
            for(int x=0;x<(story_number);x++){
                arr[x]="done";
            }
            if(story_number<arr.length-1){
                arr[story_number]="true";
            }
            ArrayList<String> list1 = new ArrayList<String>();
            Collections.addAll(list1, arr);
            //saveArrayList(list1,"cases_enabled");

            String[] arr_eval=new String[CASE_COUNT];
            for(int x=0;x<arr_eval.length;x++){
                arr_eval[x]="false";
            }
            for(int x=0;x<story_number;x++){
                arr_eval[x]="done";
            }

            ArrayList<String> liste = new ArrayList<String>();
            Collections.addAll(liste, arr_eval);
            //saveArrayList(liste,"evals_enabled");

            Bundle int_bundle = new Bundle();
            int_bundle.putInt("case_number",story_number);
            /*Intent intent = new Intent(E3Activity.this, CasesActivity.class);
            intent.putExtras(int_bundle);
            startActivity(intent);
            E3Activity.this.finish();*/
            }
        });
    }
    /*public void saveArrayList(ArrayList<String> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(E3Activity.this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }*/

    public int loadData(String KEY) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(E3Activity.this);
        int text = prefs.getInt(KEY, 0);
        return text;
    }
}