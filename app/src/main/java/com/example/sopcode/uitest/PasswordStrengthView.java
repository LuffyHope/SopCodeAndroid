package com.example.sopcode.uitest;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sopcode.R;

public class PasswordStrengthView extends LinearLayout {
    private String[] textColor = {"#F54252", "#FF935C", "#C5C812", "#01C988"};
    private TextView tvMsg;
    private ImageView[] imageViews;

    public PasswordStrengthView(Context context) {
        super(context);
        init(context);
    }


    public PasswordStrengthView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PasswordStrengthView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.password_strength_layout, this, true);
        ImageView imageOne = view.findViewById(R.id.image_one);
        ImageView imageTwo = view.findViewById(R.id.image_two);
        ImageView imageThree = view.findViewById(R.id.image_three);
        ImageView imageFour = view.findViewById(R.id.image_four);
        tvMsg = view.findViewById(R.id.tv_msg);

        imageViews = new ImageView[]{imageOne, imageTwo, imageThree, imageFour};

    }

    public void setViewStatus(int status, Context context) {
        if (status < 0 || status > 4) {
            return;
        }
        tvMsg.setVisibility(((status - 1) < 0) ? GONE : VISIBLE);
        if (((status - 1) >= 0))
            tvMsg.setTextColor(Color.parseColor(textColor[status - 1]));
        switch (status) {
            case 0://正常状态
                for (int i = 0; i < imageViews.length; i++) {
                    imageViews[i].setImageDrawable(getResources().getDrawable(R.drawable.normal));
                }
                break;
            case 1://弱
                for (int i = 0; i < imageViews.length; i++) {
                    imageViews[i].setImageDrawable(getResources().getDrawable((i > status - 1) ? R.drawable.normal : R.drawable.one));
                }
                tvMsg.setText("弱");
                break;
            case 2://中
                for (int i = 0; i < imageViews.length; i++) {
                    imageViews[i].setImageDrawable(getResources().getDrawable(i > status - 1 ? R.drawable.normal : R.drawable.two));
                }
                tvMsg.setText("中");
                break;
            case 3://强
                for (int i = 0; i < imageViews.length; i++) {
                    imageViews[i].setImageDrawable(getResources().getDrawable(i > status - 1 ? R.drawable.normal : R.drawable.three));
                }
                tvMsg.setText("强");
                break;
            case 4://极强
                for (int i = 0; i < imageViews.length; i++) {
                    imageViews[i].setImageDrawable(getResources().getDrawable(R.drawable.four));
                }
                tvMsg.setText("极强");
                break;
            default:
                break;
        }
    }
}
