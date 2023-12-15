package com.glitch.detectivesearch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.glitch.detectivesearch.questions.Q1Activity;

import java.util.Objects;

public class StoryActivity extends AppCompatActivity {
    TextView story;
    Button cont;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_story);
        //Toolbar toolbar = findViewById(R.id.toolbar_detail);
        //setSupportActionBar(toolbar);

        final int story_number = Objects.requireNonNull(getIntent().getExtras()).getInt("case_number");
        final String[]selected= getIntent().getExtras().getStringArray("country_array");
        final String[]wrong= getIntent().getExtras().getStringArray("wrong_array");

        story=findViewById(R.id.tvStory);
        int resId = getResources().getIdentifier("story_"+story_number, "string", getApplicationInfo().packageName);
        story.setText(resId);
        cont=findViewById(R.id.btnContinue);
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Bundle string_array_bundle = new Bundle();
            string_array_bundle.putStringArray("country_array",selected);
            string_array_bundle.putStringArray("wrong_array",wrong);
            Bundle int_bundle = new Bundle();
            int_bundle.putInt("case_number",story_number-1);
            Intent intent = new Intent(StoryActivity.this, Q1Activity.class);
            intent.putExtras(string_array_bundle);
            intent.putExtras(int_bundle);
            startActivity(intent);
            StoryActivity.this.finish();
            }
        });
    }
}
