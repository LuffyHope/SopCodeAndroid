package com.example.sopcode.uitest;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sopcode.R;

public class RegistProgressView extends LinearLayout {

    private TextView[] circleTv;
    private TextView[] titleTv;
    private View[] line;

    public RegistProgressView(Context context) {
        super(context);
        init(context);
    }

    public RegistProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RegistProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.progress_regist, this, true);
        TextView textView = view.findViewById(R.id.textView);
        TextView textView2 = view.findViewById(R.id.textView2);
        TextView textView3 = view.findViewById(R.id.textView3);

        circleTv = new TextView[]{textView, textView2, textView3};

        TextView tv_one = view.findViewById(R.id.tv_one);
        TextView tv_two = view.findViewById(R.id.tv_two);
        TextView tv_three = view.findViewById(R.id.tv_three);

        titleTv = new TextView[]{tv_one, tv_two, tv_three};

        View one = view.findViewById(R.id.v_one);
        View two = view.findViewById(R.id.v_two);
        View three = view.findViewById(R.id.v_three);
        View four = view.findViewById(R.id.v_four);

        line = new View[]{one, two, three, four};
    }

    //index 只能传0 或 1 或 2  因为只有3个页面;
    public void setViewStatus(int index) {
        //圆形TextView 以及底部的title颜色；
        for (int i = 0; i < circleTv.length; i++) {
            circleTv[i].setBackground(getResources().getDrawable(i <= index ? R.drawable.shape_circle_red : R.drawable.shape_circle_white));
            titleTv[i].setTextColor(getResources().getColor(i <= index ? R.color._343434 : R.color.color_CCCC));
            circleTv[i].setTextColor(getResources().getColor(i <= index ? R.color.white : R.color.color_CCCC));
        }

        //线的状态；
        for (int i = 0; i < line.length; i++) {
            if (0 == index) {
                line[i].setBackgroundColor(getResources().getColor(i <= 0 ? R.color.button_f54252 : R.color.color_CCCC));
            } else if (1 == index) {
                line[i].setBackgroundColor(getResources().getColor(i <= 2 ? R.color.button_f54252 : R.color.color_CCCC));
            } else if (2 == index) {
                line[i].setBackgroundColor(getResources().getColor(R.color.button_f54252));
            }
        }
    }

}
