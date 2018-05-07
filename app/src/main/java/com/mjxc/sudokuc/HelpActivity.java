package com.mjxc.sudokuc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * 作者：xk on 2018/3/30
 * 版本：v1.0
 * 描述：
 */

public class HelpActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private int imgs[] = new int[]{R.mipmap.help1,R.mipmap.help2,R.mipmap.help3};
    private ArrayList<View> aList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_help);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        aList = new ArrayList<>();
        for (int i = 0; i <3 ; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imgs[i]);
            aList.add(imageView);
        }
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
               View view =  aList.get(position);
               view.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if(position!=aList.size()-1){
                           mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
                       }else{
                           HelpActivity.this.finish();
                       }
                   }
               });
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgs.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
               container.addView(aList.get(position));
                return aList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//                super.destroyItem(container, position, object);
                container.removeView(aList.get(position));
            }
        });
    }
}
