package com.cjhlearn.tourdemo10;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cjhlearn.tourdemo10.adapter.LaunchSimpleAdapter;

public class LaunchSimpleActivity extends AppCompatActivity {
    // 声明引导页面的图片数组
    private int[] lanuchImageArray = {R.drawable.guide_bg1,
            R.drawable.guide_bg2, R.drawable.guide_bg3, R.drawable.guide_bg4};

    private Button startMainViewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        startMainViewButton=findViewById(R.id.btn_start);
        // 从布局视图中获取名叫vp_launch的翻页视图
        ViewPager vp_launch = findViewById(R.id.vp_launch);
        vp_launch.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                if (i==(lanuchImageArray.length-1))
                {
                    Log.i("PageView", "onPageSelected: last page!");
                    startMainViewButton.setVisibility(View.VISIBLE);//当最后一页时，将button设置为可视化状态
                }else
                {
                    startMainViewButton.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        // 构建一个引导页面的翻页适配器
        LaunchSimpleAdapter adapter = new LaunchSimpleAdapter(this, lanuchImageArray);
        // 给vp_launch设置引导页面适配器
        vp_launch.setAdapter(adapter);
        // 设置vp_launch默认显示第一个页面
        vp_launch.setCurrentItem(0);
    }
}
