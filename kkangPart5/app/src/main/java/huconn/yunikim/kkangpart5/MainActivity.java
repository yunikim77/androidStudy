package huconn.yunikim.kkangpart5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    ArrayAdapter<ArrayList> arrayAdapter;
    ArrayList<String> datas;
    Button btAnother;
    Button btDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datas = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, datas);

        btAnother = findViewById(R.id.anotherActi);
        btDialog = findViewById(R.id.dialogActi);
        listView = findViewById(R.id.lv_log);
        listView.setAdapter(arrayAdapter);

        btAnother.setOnClickListener(this);
        btDialog.setOnClickListener(this);

        datas.add("onCreate - MainActivity");
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();

        datas.add("onResume - MainActivity");
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();

        datas.add("onStart - MainActivity");
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        datas.add("onPause - MainActivity");
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        datas.add("onStop - MainActivity");
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        datas.add("onRestart - MainActivity");
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        datas.add("onDestroy - MainActivity");
        arrayAdapter.notifyDataSetChanged();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if(v == btAnother) {
            datas.add("click ANOTHER button");
            arrayAdapter.notifyDataSetChanged();

            Intent intent = new Intent(this, AnotherActivity.class);
            intent.putExtra("data", datas);
            startActivityForResult(intent, 10);
        }
        else if(v == btDialog) {
            datas.add("click DIALOG button");
            arrayAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ArrayList<String> appendData;

        if(requestCode == 10 && resultCode == RESULT_OK) {
            appendData = data.getStringArrayListExtra("data");
            datas.addAll(appendData);
        }
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        datas.add("onSaveInstanceState - MainActivity");
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        datas.add("onRestoreInstanceState - MainActivity");
        arrayAdapter.notifyDataSetChanged();
    }
}
