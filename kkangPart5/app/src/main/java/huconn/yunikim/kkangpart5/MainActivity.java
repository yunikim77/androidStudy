package huconn.yunikim.kkangpart5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    Button btAnother;
    Button btDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);

        btAnother = findViewById(R.id.anotherActi);
        btDialog = findViewById(R.id.dialogActi);
        listView = findViewById(R.id.lv_log);
        listView.setAdapter(arrayAdapter);

        btAnother.setOnClickListener(this);
        btDialog.setOnClickListener(this);

        arrayAdapter.add("onCreate - MainActivity");
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();

        arrayAdapter.add("onResume - MainActivity");
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();

        arrayAdapter.add("onStart - MainActivity");
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        arrayAdapter.add("onPause - MainActivity");
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        arrayAdapter.add("onStop - MainActivity");
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        arrayAdapter.add("onRestart - MainActivity");
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if(v == btAnother) {
            arrayAdapter.add("click ANOTHER button");
            arrayAdapter.notifyDataSetChanged();
        }
        else if(v == btDialog) {
            arrayAdapter.add("click DIALOG button");
            arrayAdapter.notifyDataSetChanged();
        }
    }
}
