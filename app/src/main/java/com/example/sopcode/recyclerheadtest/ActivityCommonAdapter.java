package com.example.sopcode.recyclerheadtest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sopcode.R;

import java.util.List;

public class ActivityCommonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<String> list;
    private final int IMAGE_VIEW = 0;
    private final int TEXT_VIEW = 1;
    private final int FOOT_VIEW = 2;


    public ActivityCommonAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (IMAGE_VIEW == viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_activity, parent, false);
            return new ImageHolder(view);
        } else if (TEXT_VIEW == viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
            return new TextHolder(view);
        } else if (FOOT_VIEW == viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foot_activity, parent, false);
            return new FootHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImageHolder) {
            ((ImageHolder) holder).setImage("");
            ((ImageHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //图片的点击事件
                    Toast.makeText(((ImageHolder) holder).itemView.getContext(), "图片点击事件", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (holder instanceof TextHolder) {
            ((TextHolder) holder).setView();
            ((TextHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //文本的点击事件；
                    Toast.makeText(((TextHolder) holder).itemView.getContext(), "文本点击事件", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return FOOT_VIEW;
        } else if ((position % 2) == 0) {
            return IMAGE_VIEW;
        } else {
            return TEXT_VIEW;
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public static class ImageHolder extends RecyclerView.ViewHolder {
        private final ImageView image;

        public ImageHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }

        public void setImage(String url) {
            //Glide 设置image
        }
    }

    public static class TextHolder extends RecyclerView.ViewHolder {

        private final ImageView imageIcon;
        private final TextView tvText;

        public TextHolder(View itemView) {
            super(itemView);
            imageIcon = itemView.findViewById(R.id.image_icon);
            tvText = itemView.findViewById(R.id.tv_text);
        }

        public void setView() {
            //Glide
            //TextView
        }
    }

    public static class FootHolder extends RecyclerView.ViewHolder {

        public FootHolder(View itemView) {
            super(itemView);
        }
    }
}
