package com.mallcloud.rfiddemo_c.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {

    private List<ImageView> images;
    /*
    * 构造方法传入图片列表和viewPager实例
    * */
    public ViewPagerAdapter(ArrayList<ImageView> images, ViewPager viewPager) {
        this.images = images;
    }



    @Override
    public int getCount() {
        return Integer.MAX_VALUE;  //返回一个无限大的值可以无限魂环
    }

    /*
    * 判断是否使用缓存
    * 如果是true，使用缓存不用去调用instantiateItem方法创建一个新的对象
    *
    * */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /*
    * 初始化一个条目
    * position 当前需要加载条目的索引
    * */
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //将position对应位置的ImageView添加到ViewPager中
        ImageView imageView = images.get(position % images.size());
        container.addView(imageView);
        return imageView;
    }


    /*
    * 销毁一个条目
    * position 就是当前需要被销毁的条目的索引
    * */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //把ImageView从ViewPager中移除掉
        container.removeView((View) object);
    }
}
