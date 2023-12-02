package com.glitch.detectivesearch.questions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.glitch.detectivesearch.FailActivity;
import com.glitch.detectivesearch.R;
import com.glitch.detectivesearch.WinActivity;

import java.util.Objects;
import java.util.Random;

public class Q3Activity extends AppCompatActivity {
    TextView question;
    RadioButton radioButton;
    RadioButton btn_1;
    RadioButton btn_2;
    RadioButton btn_3;
    //RadioButton btn_4;
    Button teleport_btn;
    String[]selected;
    String[]wrong;
    String country;
    ImageView country_image;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_q3);
        setContentView(R.layout.activity_case_ques);
        //Toolbar toolbar = findViewById(R.id.toolbar_detail);
        //setSupportActionBar(toolbar);

        int mode=loadData("mode");
        int easy=loadData("easy");
        int photo=loadData("photo");

        country_image=findViewById(R.id.country_image_1);
        question=findViewById(R.id.question_txt_1);
        //question.setText(R.string.loremipsum);
        final RadioGroup radioGroup=findViewById(R.id.rg_1);
        btn_1=findViewById(R.id.Radio_btn_1_1);
        btn_2=findViewById(R.id.Radio_btn_2_1);
        btn_3=findViewById(R.id.Radio_btn_3_1);
        //btn_4=findViewById(R.id.Radio_btn_4);
        teleport_btn =findViewById(R.id.teleport_btn_1);
        teleport_btn.setText(R.string.teleport);

        final String check_prev= getIntent().getExtras().getString("check");
        final String answer= getIntent().getExtras().getString("country");
        final int story_number = Objects.requireNonNull(getIntent().getExtras()).getInt("case_number");

        if(check_prev.equals("true")){
            selected= getIntent().getExtras().getStringArray("country_array");
            wrong= getIntent().getExtras().getStringArray("wrong_array");
            country=selected[0];
            //System.out.println("cccccccccccc: "+country);
            /*int resId = getResources().getIdentifier(country+"_1", "string", getApplicationInfo().packageName);
            question.setText(resId);
            Context context = country_image.getContext();
            int id = context.getResources().getIdentifier(country.toLowerCase()+"_1", "drawable", context.getPackageName());
            country_image.setImageResource(id);*/
            if(mode==0){
                if(easy==0){
                    int resId = getResources().getIdentifier(country+"_1", "string", getApplicationInfo().packageName);
                    question.setText(resId);
                }
                if(easy==1){
                    int resId = getResources().getIdentifier(country+"_2", "string", getApplicationInfo().packageName);
                    question.setText(resId);
                }
                if(photo==1){
                    Context context = country_image.getContext();
                    int id = context.getResources().getIdentifier(country.toLowerCase()+"_1", "drawable", context.getPackageName());
                    Glide.with(this).load(id).into(country_image);
                    //country_image.setImageResource(id);
                }
            }
            if(mode==1){
                Context context = country_image.getContext();
                int id = context.getResources().getIdentifier(country.toLowerCase()+"_flag", "drawable", context.getPackageName());
                Glide.with(this).load(id).into(country_image);
                //country_image.setImageResource(id);
            }
            Random random=new Random();
            int randomInteger = random.nextInt(3)+1;
            if(randomInteger==0){
                btn_1.setText(selected[0]);
                btn_2.setText(wrong[0]);
                btn_3.setText(wrong[1]);
            }if(randomInteger==1){
                btn_2.setText(selected[0]);
                btn_1.setText(wrong[0]);
                btn_3.setText(wrong[1]);
            }else{
                btn_3.setText(selected[0]);
                btn_1.setText(wrong[0]);
                btn_2.setText(wrong[1]);
            }
        }else{
            wrong= getIntent().getExtras().getStringArray("wrong_array");
            country=wrong[0];
            //question.setText(country);
            /*int resId = getResources().getIdentifier(country+"_1", "string", getApplicationInfo().packageName);
            question.setText(resId);
            Context context = country_image.getContext();
            int id = context.getResources().getIdentifier(country.toLowerCase()+"_1", "drawable", context.getPackageName());
            country_image.setImageResource(id);*/
            if(mode==0){
                if(easy==0){
                    int resId = getResources().getIdentifier(country+"_1", "string", getApplicationInfo().packageName);
                    question.setText(resId);
                }
                if(easy==1){
                    int resId = getResources().getIdentifier(country+"_2", "string", getApplicationInfo().packageName);
                    question.setText(resId);
                }
                if(photo==1){
                    Context context = country_image.getContext();
                    int id = context.getResources().getIdentifier(country.toLowerCase()+"_1", "drawable", context.getPackageName());
                    Glide.with(this).load(id).into(country_image);
                    //country_image.setImageResource(id);
                }
            }
            if(mode==1){
                Context context = country_image.getContext();
                int id = context.getResources().getIdentifier(country.toLowerCase()+"_flag", "drawable", context.getPackageName());
                Glide.with(this).load(id).into(country_image);
                //country_image.setImageResource(id);
            }
            Random random=new Random();
            int randomInteger = random.nextInt(3)+1;
            if(randomInteger==0){
                btn_1.setText(wrong[0]);
                btn_2.setText(wrong[1]);
                btn_3.setText(wrong[2]);
            }if(randomInteger==1){
                btn_2.setText(wrong[0]);
                btn_1.setText(wrong[1]);
                btn_3.setText(wrong[2]);
            }else{
                btn_3.setText(wrong[0]);
                btn_1.setText(wrong[1]);
                btn_2.setText(wrong[2]);
            }
        }

        teleport_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if(selectedId>0){
                radioButton = (RadioButton) findViewById(selectedId);
                String answer= (String) radioButton.getText();
                boolean check_current=answer.equals(country);
                if(check_current&&check_prev.equals("true")){
                    Intent intent = new Intent(Q3Activity.this, WinActivity.class);
                    Bundle int_bundle = new Bundle();
                    int_bundle.putInt("case_number",story_number);
                    intent.putExtras(int_bundle);
                    startActivity(intent);
                    Q3Activity.this.finish();
                    //Log.v("check 3","true");
                }else{
                    Intent intent = new Intent(Q3Activity.this, FailActivity.class);
                    Bundle int_bundle = new Bundle();
                    int_bundle.putInt("case_number",story_number);
                    intent.putExtras(int_bundle);
                    startActivity(intent);
                    Q3Activity.this.finish();
                    //Log.v("check 3","false");
                }
            }else{
                Toast.makeText(Q3Activity.this, "Choose an answer", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }
    public int loadData(String KEY) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Q3Activity.this);
        int text = prefs.getInt(KEY, 0);
        return text;
    }
}
