package huconn.yunikim.kkangpart6;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btFrag1;
    Button btFrag2;
    Button btFrag3;

    FragmentManager fm;
    OneFragment frag1;
    TwoFragment frag2;
    ThreeFragment frag3;

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v == btFrag1) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.main_container, frag1);
                ft.commit();
            } else if(v == btFrag2) {
                frag2.show(fm, null);
            } else if(v == btFrag3) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.main_container, frag3);
                ft.commit();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btFrag1 = findViewById(R.id.btn1);
        btFrag2 = findViewById(R.id.btn2);
        btFrag3 = findViewById(R.id.btn3);

        btFrag1.setOnClickListener(listener);
        btFrag2.setOnClickListener(listener);
        btFrag3.setOnClickListener(listener);

        fm = getSupportFragmentManager();
        frag1 = new OneFragment();
        frag2 = new TwoFragment();
        frag3 = new ThreeFragment();
    }
}
