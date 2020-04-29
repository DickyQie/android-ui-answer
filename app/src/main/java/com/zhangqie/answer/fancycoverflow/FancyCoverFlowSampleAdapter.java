/*
 * Copyright 2013 David Schreiber
 *           2013 John Paul Nalog
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.zhangqie.answer.fancycoverflow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangqie.answer.R;

/**
 * Created by zhh on 2017/11/18.
 */
public class FancyCoverFlowSampleAdapter extends FancyCoverFlowAdapter {

   // private List<String> list = null;
    private LayoutInflater mInflater;
    public String[] strings;

    private CallBackInterface mCallBackInterface; //回调接口

    public FancyCoverFlowSampleAdapter(Context context, String[] strings, CallBackInterface callBack) {
        this.strings = strings;
        mInflater = LayoutInflater.from(context);
        this.mCallBackInterface = callBack;

    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return strings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getCoverFlowItem(final int position, View reuseableView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (reuseableView == null) {
            holder = new ViewHolder();
            reuseableView = mInflater.inflate(R.layout.gallery_item, null);
            //由屏幕大小适配边距(可调)--(一般不修改)
            reuseableView.setLayoutParams(new FancyCoverFlow.LayoutParams(ScreenUtils.dip2px(viewGroup.getContext(),
                    ScreenUtils.getScreenHeight(viewGroup.getContext()) > 1000 ? 280 : 265),
                    ScreenUtils.dip2px(viewGroup.getContext(), 400)));

            holder.tv_content = (TextView) reuseableView.findViewById(R.id.tv_content);
            holder.tv1 = (TextView) reuseableView.findViewById(R.id.tv1);
            holder.tv2 = (TextView) reuseableView.findViewById(R.id.tv2);
            holder.tv_fin = (TextView) reuseableView.findViewById(R.id.tv_fin);
            reuseableView.setTag(holder);
        } else {
            holder = (ViewHolder) reuseableView.getTag();
        }

        holder.tv_content.setText((position + 1) + "." + strings[position]);


        //listview中的点击事件
         holder.tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBackInterface.callBackClick(v,position);
            }
        });
        holder.tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBackInterface.callBackClick(v,position);
            }
        });

        holder.tv_fin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBackInterface.callBackClick(v,position);
            }
        });



        return reuseableView;
    }

    final class ViewHolder {

        public TextView tv_content;
        public TextView tv1;
        public TextView tv2;
        public TextView tv_fin;
    }


}
