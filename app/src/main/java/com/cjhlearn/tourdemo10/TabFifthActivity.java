package com.cjhlearn.tourdemo10;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TabFifthActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tab_fifth);
        // 根据标签栏传来的参数拼接文本字符串
        String desc = String.format("我是%s页面，来自%s",
                "购物车", getIntent().getExtras().getString("tag"));
        TextView tv_fifth = findViewById(R.id.tv_fifth);
        tv_fifth.setText(desc);
    }
}
