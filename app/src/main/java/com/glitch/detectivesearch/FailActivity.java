package com.glitch.detectivesearch;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FailActivity extends AppCompatActivity{
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fail);
        //Toolbar toolbar = findViewById(R.id.toolbar_detail);
        //setSupportActionBar(toolbar);

        b=findViewById(R.id.fail_btn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FailActivity.this.finish();
                /*Intent myIntent = new Intent(FailActivity.this, CasesActivity.class);
                startActivity(myIntent);*/
            }
        });
    }
}
