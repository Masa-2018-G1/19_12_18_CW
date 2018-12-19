package com.sheygam.masa_2018_g1_19_12_18_cw;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button prevBtn, nextBtn, startBtn, endBtn;
    private ViewPager myPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        myPager = findViewById(R.id.my_view_pager);
        myPager.setOffscreenPageLimit(2);
        myPager.setAdapter(adapter);
        prevBtn = findViewById(R.id.prevBtn);
        nextBtn = findViewById(R.id.nextBtn);
        startBtn = findViewById(R.id.startBtn);
        endBtn = findViewById(R.id.endBtn);
        startBtn.setOnClickListener(this);
        endBtn.setOnClickListener(this);
        prevBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

        myPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("SCROLLED", "onPageScrolled() called with: position = [" + position + "], positionOffset = [" + positionOffset + "], positionOffsetPixels = [" + positionOffsetPixels + "]");
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("MY_TAG", "onPageSelected() called with: position = [" + position + "]");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        Log.d("MY_TAG", "IDLE");
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        Log.d("MY_TAG", "SETTLING");
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        Log.d("MY_TAG", "DRAGGING");
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.prevBtn) {
            int pos = myPager.getCurrentItem();
            if (pos > 0) {
                myPager.setCurrentItem(--pos);
            }
        } else if (v.getId() == R.id.nextBtn) {
            int pos = myPager.getCurrentItem();
            int count = myPager.getAdapter().getCount();
            if (pos < count - 1) {
                myPager.setCurrentItem(++pos);
            }
        } else if (v.getId() == R.id.startBtn) {
            myPager.setCurrentItem(0, true);
        } else if (v.getId() == R.id.endBtn) {
            myPager.setCurrentItem(myPager.getAdapter().getCount() - 1, false);
        }
    }

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Random random = new Random();
            int r = random.nextInt(256);
            int g = random.nextInt(256);
            int b = random.nextInt(256);
            return PageFragment.newInstance(Color.rgb(r, g, b), position);
        }

        @Override
        public int getCount() {
            return 10;
        }
    }
}
