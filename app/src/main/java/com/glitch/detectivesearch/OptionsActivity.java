package com.glitch.detectivesearch;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/*import com.glitch.detectivesearch.R;
import com.google.gson.Gson;
import com.llollox.androidtoggleswitch.widgets.ToggleSwitch;*/

import java.util.ArrayList;
import java.util.Collections;

public class OptionsActivity extends AppCompatActivity {
    Button reset_btn;
    Button save_btn;
    RadioButton radioButton;
    RadioButton normal_rdbtn;
    RadioButton flag_rdbtn;
  /*  ToggleSwitch photo_swtch;
    ToggleSwitch easy_swtch;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_options);
        //Toolbar toolbar = findViewById(R.id.toolbar_detail);
        //setSupportActionBar(toolbar);

        final RadioGroup radioGroup=findViewById(R.id.rg_options);
        normal_rdbtn=findViewById(R.id.normalmode_rb);
        flag_rdbtn=findViewById(R.id.flagmode_rb);
        /*photo_swtch =findViewById(R.id.photo_switch);
        easy_swtch =findViewById(R.id.easy_switch);*/
        save_btn=findViewById(R.id.savechanges_btn);
        reset_btn=findViewById(R.id.reset_btn);

        final int CASE_COUNT=loadData("case_count");
        int mode=loadData("mode");
        int easy=loadData("easy");
        int photo=loadData("photo");
        System.out.println("mode: "+mode+" easy: "+easy+" photo "+photo);

        /*View.OnClickListener first_radio_listener = new View.OnClickListener(){
            public void onClick(View v) {
                photo_swtch.setEnabled(true);
                easy_swtch.setEnabled(true);
            }
        };
        normal_rdbtn.setOnClickListener(first_radio_listener);

        View.OnClickListener second_radio_listener = new View.OnClickListener(){
            public void onClick(View v) {
                photo_swtch.setEnabled(false);
                easy_swtch.setEnabled(false);
            }
        };
        flag_rdbtn.setOnClickListener(second_radio_listener);

        if(mode==0){
            normal_rdbtn.toggle();
            if(easy==0){
                easy_swtch.setCheckedPosition(0);
            }if(easy==1){
                easy_swtch.setCheckedPosition(1);
            }
            if(photo==0){
                photo_swtch.setCheckedPosition(1);
            }if(photo==1){
                photo_swtch.setCheckedPosition(0);
            }
        }if(mode==1){
            flag_rdbtn.toggle();
        }*/

        /*save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                String answer= (String) radioButton.getText();
                if(answer.equals("Normal Mode")){
                    saveData(0,"mode");
                    Integer position = easy_swtch.getCheckedPosition();
                    if(position==0){
                        saveData(0,"easy");
                    }
                    if(position==1){
                        saveData(1,"easy");
                    }
                    //System.out.println("AAAAA "+position);
                    position = easy_swtch.getCheckedPosition();
                    if(position==0){
                        saveData(1,"photo");
                    }
                    if(position==1){
                        saveData(0,"photo");
                    }
                }else{
                    saveData(1,"mode");
                }
                Toast.makeText(OptionsActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                OptionsActivity.this.finish();
            }
        });*/

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String arr[]=new String[CASE_COUNT];
                arr[0]="true";
                for(int c=1;c<CASE_COUNT;c++){
                    arr[c]="false";
                }
                String arr2[]=new String[CASE_COUNT];
                arr2[0]="false";
                for(int c=1;c<CASE_COUNT;c++){
                    arr2[c]="false";
                }

                ArrayList<String> list1 = new ArrayList<String>();
                Collections.addAll(list1, arr);
                //saveArrayList(list1,"cases_enabled");
                ArrayList<String> list2 = new ArrayList<String>();
                Collections.addAll(list2, arr2);
                //saveArrayList(list2,"evals_enabled");

                saveData(0,"mode");
                saveData(0,"easy");
                saveData(1,"photo");
                Toast.makeText(OptionsActivity.this, "Reset", Toast.LENGTH_SHORT).show();
                OptionsActivity.this.finish();
            }
        });

    }

    /*public void saveArrayList(ArrayList<String> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(OptionsActivity.this);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }*/
    public void saveData(int TEXT,String KEY) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(OptionsActivity.this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY, TEXT);
        editor.apply();
    }
    public int loadData(String KEY) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(OptionsActivity.this);
        int text = prefs.getInt(KEY, 0);
        return text;
    }
}
