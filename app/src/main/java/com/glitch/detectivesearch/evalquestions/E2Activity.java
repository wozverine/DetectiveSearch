package com.glitch.detectivesearch.evalquestions;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.glitch.detectivesearch.R;

import java.util.Objects;

public class E2Activity extends AppCompatActivity {
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
        l = findViewById(R.id.lll);
        l.setBackgroundResource(R.drawable.eval_background_2);
        //Toolbar toolbar = findViewById(R.id.toolbar_detail);
        //setSupportActionBar(toolbar);
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
        int resId = getResources().getIdentifier("eval_"+story_number+"_q2_array", "array", getApplicationInfo().packageName);
        String[] questions_array = res.getStringArray(resId);
        question.setText(questions_array[0]);
        btn_1.setText(questions_array[1]);
        btn_2.setText(questions_array[2]);
        btn_3.setText(questions_array[3]);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Bundle int_bundle = new Bundle();
            int_bundle.putInt("case_number",story_number);
            Intent intent = new Intent(E2Activity.this, E3Activity.class);
            intent.putExtras(int_bundle);
            startActivity(intent);
            E2Activity.this.finish();
            }
        });
    }
}
