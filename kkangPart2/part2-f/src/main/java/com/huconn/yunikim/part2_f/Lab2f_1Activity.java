package com.huconn.yunikim.part2_f;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Lab2f_1Activity extends AppCompatActivity implements View.OnClickListener {

    Button btn_OK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2f_1);
        btn_OK = findViewById(R.id.confirmBtn);
        btn_OK.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast toast = Toast.makeText(this, R.string.clickOK, Toast.LENGTH_SHORT );
        toast.show();
    }
}
