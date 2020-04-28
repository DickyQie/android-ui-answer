package com.zhangqie.answer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by zhangqie on 2020/2/17
 * Describe:
 */
public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {

    public String[] strings;
    private LayoutInflater layoutInflater;

    public AnswerAdapter(Context context, String[] strings) {
        this.strings = strings;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return strings.length;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolder = layoutInflater.inflate(R.layout.item_recycler, parent, false);
        return new ViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_content.setText((position + 1) + "." + strings[position]);
        if (position == getItemCount() - 1) {
            holder.tv_fin.setVisibility(View.GONE);
        }
    }

    public void setOnItemClickListenter(OnItemClickListenter onItemClickListenter) {
        this.onItemClickListenter = onItemClickListenter;
    }

    public OnItemClickListenter onItemClickListenter;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_content;
        public TextView tv1;
        public TextView tv2;
        public TextView tv_fin;

        public ViewHolder(View view) {
            super(view);
            tv_content = view.findViewById(R.id.tv_content);
            tv1 = view.findViewById(R.id.tv1);
            tv2 = view.findViewById(R.id.tv2);
            tv_fin = view.findViewById(R.id.tv_fin);
            tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListenter != null) {
                        onItemClickListenter.onItemClick(view, getAdapterPosition());
                    }
                }
            });

            tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListenter != null) {
                        onItemClickListenter.onItemClick(view, getAdapterPosition());
                    }
                }
            });
            tv_fin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListenter != null) {
                        onItemClickListenter.onItemClick(view, getAdapterPosition());
                    }
                }
            });
        }

    }

}
