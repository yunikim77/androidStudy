package com.huconn.yunikim.part2_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class Lab3_3Activity extends AppCompatActivity implements View.OnClickListener {

    Button trueBtn;
    TextView targetTextview;
    Button falseBtn;
    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3_3);

        trueBtn = findViewById(R.id.btn_visible_true);
        targetTextview = findViewById(R.id.text_visible_target);
        falseBtn = findViewById(R.id.btn_visible_false);
        checkBox = findViewById(R.id.checkbox);

        trueBtn.setOnClickListener(this);
        falseBtn.setOnClickListener(this);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    checkBox.setText("is Checked");
                }
                else {
                    checkBox.setText("is Unchecked");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == trueBtn) {
            targetTextview.setVisibility(View.VISIBLE);
        }
        else {
            targetTextview.setVisibility(View.INVISIBLE);
        }
    }
}
