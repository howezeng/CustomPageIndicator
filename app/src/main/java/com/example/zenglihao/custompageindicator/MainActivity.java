package com.example.zenglihao.custompageindicator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zenglihao.custompageindicator.custom.PageIndicatorView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager pager;
    private PageIndicatorView pageIndicatorView;
    private  PicFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageIndicatorView = (PageIndicatorView) findViewById(R.id.pager_indicator);
        pager = (ViewPager) findViewById(R.id.pager);
        adapter =  new PicFragmentAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pageIndicatorView.setCount(adapter.getCount());
        pageIndicatorView.attachToViewPager(pager);
    }


    private class PicFragmentAdapter extends FragmentStatePagerAdapter {

        private List<Pair<Integer, Integer>> dataMap;

        PicFragmentAdapter(FragmentManager fm) {
            super(fm);
            dataMap = new ArrayList<>();
            dataMap.add(new Pair<>(R.color.bg_1, R.drawable.demo_icon));
            dataMap.add(new Pair<>(R.color.bg_2, R.drawable.demo_icon));
            dataMap.add(new Pair<>(R.color.bg_3, R.drawable.demo_icon));
            dataMap.add(new Pair<>(R.color.bg_4, R.drawable.demo_icon));
        }

        @Override
        public Fragment getItem(int position) {
            return PicFragment.newInstance(dataMap.get(position).first, dataMap.get(position).second);
        }

        @Override
        public int getCount() {
            return dataMap.size();
        }
    }
}
