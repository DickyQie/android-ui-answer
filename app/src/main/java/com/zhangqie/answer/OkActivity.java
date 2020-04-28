package com.zhangqie.answer;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by zhangqie on 2020/2/18
 * Describe:
 */
public class OkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ok_activity);


        String stringType = getIntent().getStringExtra("stringType");

        TextView tv1 = findViewById(R.id.tv1);
        tv1.setText("完成测试，你是"+stringType);
    }
}
