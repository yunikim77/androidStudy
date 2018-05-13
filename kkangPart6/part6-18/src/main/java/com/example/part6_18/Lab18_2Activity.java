package com.example.part6_18;

import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class Lab18_2Activity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, View.OnClickListener {

    ViewPager viewPager;
    FloatingActionButton floatingActionButton;
    RelativeLayout relativeLayout;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab18_2);

        relativeLayout = findViewById(R.id.lab2_container);
        viewPager = findViewById(R.id.lab2_viewpager);
        floatingActionButton = findViewById(R.id.lab2_fab);
        tabLayout = findViewById(R.id.lab2_tabs);

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager, false);
        tabLayout.addOnTabSelectedListener(this);
        floatingActionButton.setOnClickListener(this);  // View.OnClickListener
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> fragmentList = new ArrayList<>();
        private String titles[] = new String[]{"TAB1", "TAB2", "TAB3"};
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            fragmentList.add(new OneFragment());
            fragmentList.add(new TwoFragment());
            fragmentList.add(new ThreeFragment());
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return this.fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition(), true);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onClick(View v) {
        CoordinatorLayout coordinatorLayout = findViewById(R.id.coordiFab);
        Snackbar.make(coordinatorLayout, "I am SnackBar", Snackbar.LENGTH_LONG).setAction(
                "MoreAction", new View.OnClickListener() {
                    public void onClick(View v) {

                    }
                }
        ).show();
    }
}
