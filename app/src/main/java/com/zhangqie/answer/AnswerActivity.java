package com.zhangqie.answer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by zhangqie on 2020/2/17
 * Describe:
 */
public class AnswerActivity extends AppCompatActivity {


    RecyclerView recycler_view;
    AnswerAdapter answerAdapter;
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer_activity);
        recycler_view = findViewById(R.id.recycler_view);
        initView();

    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_view.setLayoutManager(linearLayoutManager);


        String[] strings = getResources().getStringArray(R.array.app_answer);
        answerCount = strings.length;
        answerAdapter = new AnswerAdapter(this, strings);

        recycler_view.setAdapter(answerAdapter);

        answerAdapter.setOnItemClickListenter(new OnItemClickListenter() {
            @Override
            public void onItemClick(View view, int position) {
                if (view.getId() == R.id.tv_fin) {
                    if (position != 0) {
                        recycler_view.scrollToPosition(position - 1);
                        answerAdapter.notifyItemChanged(position - 1);
                    }
                } else {
                    if (view.getId() == R.id.tv1) {
                        map.put(position + 1, "1");
                    } else if (view.getId() == R.id.tv2) {
                        //map.put(position+1, "0");
                    }

                    if (position < answerCount - 1) {
                        recycler_view.scrollToPosition(position + 1);
                        answerAdapter.notifyItemChanged(position + 1);
                    } else {
                        Log.i("aaaa", map.toString());
                        //完成测试
                        showCalculation();


                    }
                }
            }
        });

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
        int numMax =  Collections.max(Arrays.asList(numbers));

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
        Intent intent = new Intent(AnswerActivity.this, OkActivity.class);
        intent.putExtra("stringType", stringType);
        startActivity(intent);
    }

}
