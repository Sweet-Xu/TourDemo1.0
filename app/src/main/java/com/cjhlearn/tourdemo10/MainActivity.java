package com.cjhlearn.tourdemo10;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.cjhlearn.tourdemo10.adapter.LaunchSimpleAdapter;


public class MainActivity extends AppCompatActivity {
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
    };

    protected static final int WRITE_COARSE_LOCATION_REQUEST_CODE=0;

    private static final int LOGIN_MAIN_ACT_RESULT_FLAG=0x01;

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
        // 构建一个引导页面的翻页适配器
        LaunchSimpleAdapter adapter = new LaunchSimpleAdapter(this, lanuchImageArray);
        // 给vp_launch设置引导页面适配器
        vp_launch.setAdapter(adapter);
        // 设置vp_launch默认显示第一个页面
        vp_launch.setCurrentItem(0);

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

        startMainViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, LoginMainActivity.class);
                startActivityForResult(intent,LOGIN_MAIN_ACT_RESULT_FLAG);
            }
        });

        checkAMAPfPermission(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch (requestCode)
        {
            case LOGIN_MAIN_ACT_RESULT_FLAG:
                if (resultCode==Activity.RESULT_OK) {
                    Intent intent = new Intent(MainActivity.this, TabGroupActivity.class);
                    startActivity(intent);
                }else{
                    Log.w("MainActivity", "onActivityResult: result is not OK!");
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    //检测定位及网络相关权限已开启
    private void checkAMAPfPermission(Activity activity)
    {
        for (String permission:
                needPermissions) {
            int IsHavePermission=ActivityCompat.checkSelfPermission(activity,permission);
            if(ContextCompat.checkSelfPermission(this, permission)!= PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,new String[]{permission},WRITE_COARSE_LOCATION_REQUEST_CODE);
            }
        }
    }
}
