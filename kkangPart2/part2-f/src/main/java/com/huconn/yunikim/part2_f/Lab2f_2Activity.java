package com.huconn.yunikim.part2_f;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Lab2f_2Activity extends AppCompatActivity {

    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2f_2);
    }

    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if( which == DialogInterface.BUTTON_POSITIVE ) {
                finishAffinity();
                //finish();
                System.runFinalization();
                System.exit(0);
            }
        }
    };

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage(R.string.alertMsg);
        builder.setPositiveButton(R.string.alertOK, dialogListener);
        builder.setNegativeButton(R.string.alertNO, null);
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
    }
}
