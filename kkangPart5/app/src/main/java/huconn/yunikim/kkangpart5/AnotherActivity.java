package huconn.yunikim.kkangpart5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class AnotherActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> datas;
    Button btClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        btClose = findViewById(R.id.btClose);
        btClose.setOnClickListener(this);

        datas = new ArrayList<>();
        datas.add("onCreate - AnotherActivity");
    }

    @Override
    public void onClick(View v) {
        Intent intent = getIntent();
        intent.putExtra("data", datas);

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        datas.add("onRestoreInstanceState - AnotherActivity");
    }

    @Override
    protected void onStart() {
        datas.add("onStart - AnotherActivity");
        super.onStart();
    }

    @Override
    protected void onResume() {
        datas.add("onResume - AnotherActivity");
        super.onResume();
    }

    @Override
    protected void onPause() {
        datas.add("onPause - AnotherActivity");
        super.onPause();
    }

    @Override
    protected void onStop() {
        datas.add("onStop - AnotherActivity");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        datas.add("onDestroy - AnotherActivity");
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        datas.add("onRestart - AnotherActivity");
        super.onRestart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        datas.add("onSaveInstanceState - AnotherActivity");
    }
}
