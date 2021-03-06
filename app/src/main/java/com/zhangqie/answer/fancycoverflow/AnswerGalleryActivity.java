package com.zhangqie.answer.fancycoverflow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zhangqie.answer.OkActivity;
import com.zhangqie.answer.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswerGalleryActivity extends AppCompatActivity implements CallBackInterface {

    //适配器
    private FancyCoverFlowSampleAdapter adapter = null;

    private FancyCoverFlow fancyCoverFlow;


    Map<Integer, String> map = new HashMap<>();

    int answerCount = 0; //总数量

    private int xsCount = 0; //现实

    private int yjCount = 0; //研究

    private int ysCount = 0;  //艺术

    private int shCount = 0; //社会

    private int qyCount = 0; //企业

    private int cgCount = 0; //常规

    List<Integer> integerList = new ArrayList<>();

    String stringType = "";//得到类型结果

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_viewpager);
        initLayout();

    }


    private void initLayout() {
        fancyCoverFlow = (FancyCoverFlow) findViewById(R.id.main_gallery);
        fancyCoverFlow.dp2px();// child间距
        String[] strings = getResources().getStringArray(R.array.app_answer);
        answerCount = strings.length;

        adapter = new FancyCoverFlowSampleAdapter(this, strings, this);
        fancyCoverFlow.setAdapter(adapter);

    }


    private void showCalculation() {
        for (Integer key : map.keySet()) {
            if (key <= 15) {
                xsCount++;
            } else if (key > 15 &&  key <= 30){
                yjCount++;
            } else if (key > 30 &&  key <= 45) {
                ysCount++;
            } else if (key > 45 &&  key <= 60) {
                shCount++;
            } else if (key > 60 &&  key <= 75) {
                qyCount++;
            } else if (key > 75 &&  key <= 90) {
                cgCount++;
            }
        }

        Integer[] numbers = {xsCount, yjCount, ysCount, shCount, qyCount, cgCount};
        int numMax = Collections.max(Arrays.asList(numbers));

        for (int i = 0; i < numbers.length; i++) {
            if (numMax == numbers[i]) {
                integerList.add(i);
            }
        }

        for (int i = 0; i < integerList.size(); i++) {
            if (integerList.get(i) == 0) {
                stringType += "现实型";
            } else if (integerList.get(i) == 1) {
                stringType += "研究型";
            } else if (integerList.get(i) == 2) {
                stringType += "艺术型";
            } else if (integerList.get(i) == 3) {
                stringType += "社会型";
            } else if (integerList.get(i) == 4) {
                stringType += "企业型";
            } else if (integerList.get(i) == 5) {
                stringType += "常规型";
            }
        }
        Log.i("aaa", stringType);
        Intent intent = new Intent(AnswerGalleryActivity.this, OkActivity.class);
        intent.putExtra("stringType", stringType);
        startActivity(intent);
    }


    @Override
    public void callBackClick(View view, int position) {
        if (view.getId() == R.id.tv_fin) {
            if (position != 0) {
                fancyCoverFlow.onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
            }
        } else {
            if (position == answerCount - 1) {
                Log.i("aaaa", map.toString());
                //完成测试
                showCalculation();
                return;
            }
            if (view.getId() == R.id.tv1) {
                map.put(position + 1, "1");
                Log.e("点到我了", "哈萨克");
            }
            fancyCoverFlow.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
        }
    }


/**
 * FancyCoverFlow属性使用
 *
 * // 未选中的透明度
 * this.fancyCoverFlow.setUnselectedAlpha(0.0f);
 * // 未选中的饱和度
 * this.fancyCoverFlow.setUnselectedSaturation(0.0f);
 * // 未选中的比例
 * this.fancyCoverFlow.setUnselectedScale(0.8f);
 * // child间距
 * this.fancyCoverFlow.setSpacing(-60);
 * // 旋转度数
 * this.fancyCoverFlow.setMaxRotation(0);
 * // 非选中的重心偏移,负的向上
 * this.fancyCoverFlow.setScaleDownGravity(-1f);
 * // 作用距离
 * this.fancyCoverFlow.setActionDistance(FancyCoverFlow.ACTION_DISTANCE_AUTO);
 */
}
