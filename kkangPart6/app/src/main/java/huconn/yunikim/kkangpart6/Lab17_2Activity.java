package huconn.yunikim.kkangpart6;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class Lab17_2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab17_2);

        ViewPager viewPager = findViewById(R.id.mainPager);

        MyPagerAdapter myPager = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPager);
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> frag;
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);

            frag = new ArrayList<>();
            frag.add(new OneFragment());
            frag.add(new ThreeFragment());
        }

        @Override
        public int getCount() {
            return frag.size();
        }

        @Override
        public Fragment getItem(int position) {
            return frag.get(position);
        }
    }
}
