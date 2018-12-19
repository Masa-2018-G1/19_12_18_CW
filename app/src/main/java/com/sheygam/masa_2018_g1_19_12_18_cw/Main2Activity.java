package com.sheygam.masa_2018_g1_19_12_18_cw;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ViewPager viewPager = findViewById(R.id.myPager);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(adapter.getCount()/2,false);
    }

    class MyAdapter extends FragmentStatePagerAdapter{
        private PageFragment[] arr;
        public MyAdapter(FragmentManager fm) {
            super(fm);
            Random random = new Random();
            arr = new PageFragment[10];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = PageFragment.newInstance(Color.rgb(
                        random.nextInt(256),
                        random.nextInt(256),
                        random.nextInt(256)),
                        i);
            }


        }

        @Override
        public Fragment getItem(int position) {
            return arr[position%arr.length];
        }

        @Override
        public int getCount() {
            return 1_000_000;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + (position%arr.length);
        }
    }
}
