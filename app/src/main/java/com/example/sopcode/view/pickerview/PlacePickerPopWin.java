package com.example.sopcode.view.pickerview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.sopcode.R;

import java.util.ArrayList;
import java.util.List;

public class PlacePickerPopWin extends PopupWindow implements OnClickListener {

    private static final int DEFAULT_MIN_YEAR = 1900;
    public Button cancelBtn;
    public Button confirmBtn;
    public LoopView provideLoopView;
    public LoopView cityLoopView;
    public View pickerContainerV;
    public View contentView;//root view


    private int providePoint = 0;
    private int cityPoint = 0;
    private Context mContext;
    private String textCancel;
    private String textConfirm;
    private int colorCancel;
    private int colorConfirm;
    private int btnTextsize;//text btnTextsize of cancel and confirm button

    List<String> mProvideList = new ArrayList();
    List<ChianProviceCity> mChinaPlaceList;

    public static class Builder {

        //Required
        private Context context;
        private IPickedListener listener;

        public Builder(Context context, IPickedListener listener) {
            this.context = context;
            this.listener = listener;
        }

        List<ChianProviceCity> chinaPlace;
        //Option
        private boolean showDayMonthYear = false;
        private String textCancel = "取消";
        private String textConfirm = "确定";
        private int colorCancel = Color.parseColor("#999999");
        private int colorConfirm = Color.parseColor("#009900");
        private int btnTextSize = 16;//text btnTextsize of cancel and confirm button
        private int viewTextSize = 25;


        public Builder textCancel(String textCancel) {
            this.textCancel = textCancel;
            return this;
        }

        public Builder textConfirm(String textConfirm) {
            this.textConfirm = textConfirm;
            return this;
        }

        public Builder colorCancel(int colorCancel) {
            this.colorCancel = colorCancel;
            return this;
        }

        public Builder colorConfirm(int colorConfirm) {
            this.colorConfirm = colorConfirm;
            return this;
        }

        /**
         * set btn text btnTextSize
         *
         * @param textSize dp
         */
        public Builder btnTextSize(int textSize) {
            this.btnTextSize = textSize;
            return this;
        }

        public Builder viewTextSize(int textSize) {
            this.viewTextSize = textSize;
            return this;
        }

        public Builder setProvideAndCity(List<ChianProviceCity> chinaPlace) {
            this.chinaPlace = chinaPlace;
            return this;
        }

        public PlacePickerPopWin build() {
            return new PlacePickerPopWin(this);
        }

    }

    public PlacePickerPopWin(Builder builder) {
        this.textCancel = builder.textCancel;
        this.textConfirm = builder.textConfirm;
        this.mContext = builder.context;
        this.mListener = builder.listener;
        this.colorCancel = builder.colorCancel;
        this.colorConfirm = builder.colorConfirm;
        this.btnTextsize = builder.btnTextSize;
        this.mChinaPlaceList = builder.chinaPlace;
        for (ChianProviceCity province : builder.chinaPlace) {
            this.mProvideList.add(province.getProvince());
        }
        ChianProviceCity provincesBean = builder.chinaPlace.get(providePoint);
        initView();
    }

    private IPickedListener mListener;

    private void initView() {
        contentView = LayoutInflater.from(mContext).inflate(R.layout.place_pick_view, null);
        cancelBtn = (Button) contentView.findViewById(R.id.btn_cancel);
        cancelBtn.setTextColor(colorCancel);
        cancelBtn.setTextSize(btnTextsize);
        confirmBtn = (Button) contentView.findViewById(R.id.btn_confirm);
        confirmBtn.setTextColor(colorConfirm);
        confirmBtn.setTextSize(btnTextsize);
        provideLoopView = (LoopView) contentView.findViewById(R.id.pick_provide);
        cityLoopView = (LoopView) contentView.findViewById(R.id.pick_city);
        pickerContainerV = contentView.findViewById(R.id.container_picker);

        provideLoopView.setLoopListener(new LoopScrollListener() {
            @Override
            public void onItemSelect(int item) {
                providePoint = item;
                initCityViews();
            }
        });
        cityLoopView.setLoopListener(new LoopScrollListener() {
            @Override
            public void onItemSelect(int item) {
                cityPoint = item;
            }
        });


        initProvideViews();
        initCityViews();
        cancelBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);
        contentView.setOnClickListener(this);

        if (!TextUtils.isEmpty(textConfirm)) {
            confirmBtn.setText(textConfirm);
        }

        if (!TextUtils.isEmpty(textCancel)) {
            cancelBtn.setText(textCancel);
        }

        setTouchable(true);
        setFocusable(true);
        // setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
        setAnimationStyle(R.style.FadeInPopWin);
        setContentView(contentView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**
     * Init year and month loop view,
     * Let the day loop view be handled separately
     */
    private void initProvideViews() {
        provideLoopView.setDataList(mProvideList);
        provideLoopView.setInitPosition(providePoint);
    }

    private void initCityViews() {
        cityLoopView.setDataList(mChinaPlaceList.get(providePoint).getCities());
        cityLoopView.setInitPosition(cityPoint);
    }


    /**
     * set selected date position value when initView.
     *
     * @param dateStr
     */
    public void setSelectedDate(String dateStr) {

    }

    /**
     * Show date picker popWindow
     *
     * @param activity
     */
    public void showPopWin(Activity activity) {

        if (null != activity) {

            TranslateAnimation trans = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF,
                    0, Animation.RELATIVE_TO_SELF, 1,
                    Animation.RELATIVE_TO_SELF, 0);

            showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM,
                    0, 0);
            trans.setDuration(400);
            trans.setInterpolator(new AccelerateDecelerateInterpolator());

            pickerContainerV.startAnimation(trans);
        }
    }

    /**
     * Dismiss date picker popWindow
     */
    public void dismissPopWin() {

        TranslateAnimation trans = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1);

        trans.setDuration(400);
        trans.setInterpolator(new AccelerateInterpolator());
        trans.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                dismiss();
            }
        });

        pickerContainerV.startAnimation(trans);
    }

    @Override
    public void onClick(View v) {

        if (v == contentView || v == cancelBtn) {

            dismissPopWin();
        } else if (v == confirmBtn) {
            if (null != mListener && mProvideList != null) {
                mListener.setDataString(mProvideList.get(providePoint), mChinaPlaceList.get(providePoint).getCities().get(cityPoint));
            }
            dismissPopWin();
        }
    }


    public interface IPickedListener {

        /**
         * Listener when date has been checked
         */
        void setDataString(String provide, String city);
    }
}
