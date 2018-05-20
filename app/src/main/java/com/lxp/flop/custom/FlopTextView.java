package com.lxp.flop.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lxp.flop.R;
import com.lxp.flop.listner.FlopOnAnimationEndListner;
import com.lxp.flop.listner.FlopTextViewListner;


/**
 * Created by lxp  on 2018/4/27.
 */
public class FlopTextView extends RelativeLayout implements FlopTextViewListner {
    private int mTextColor;//字体颜色
    private int mTextSize;//字体大小
    private int mIsText;//是否显示文字，1显示
    private String mText;//显示的文字
//    private FlopView mFlopView;//翻牌动画view

    private FlopImageView mFlopView;//翻牌动画

    //    private FlopViewCamer mFlopView;//翻牌动画view
//    private TextView mTextView;
    private TextPaint mPaint;
    private int mWidth;
    private int mHeight;
    private Context context;
    private int marginLeft, marginTop, marginRight, marginBottom;//设置边距
    private int mFlopIvWidth, mFlopIvHeight;//设置图片宽高
    private int mFlopIvTop, mFlopIvBottom;//设置图片顶部和底部距离
    private FlopOnAnimationEndListner mFlopAnimEnd;//动画执行结束监听
    private int mClickFlopPosition;//点击的下标
    private String mIvUrl;//图片地址

    public FlopTextView(Context context) {
        this(context, null);
    }

    public FlopTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlopTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
//        mFlopView = new FlopView(context,attrs);
        mFlopView = new FlopImageView(context, attrs);
//        mFlopView = new FlopViewCamer(context,attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FlopView);
        mIsText = typedArray.getInteger(R.styleable.FlopView_flopIsText, -1);
        if (mIsText == 0) {
            mText = typedArray.getString(R.styleable.FlopView_flopText);
            mTextColor = typedArray.getInteger(R.styleable.FlopView_flopTextColor, Color.BLACK);
            mTextSize = (int) typedArray.getDimension(R.styleable.FlopView_flopTextSize, 30f);
        }

        mPaint = new TextPaint();
        mFlopIvWidth = (int) typedArray.getDimension(R.styleable.FlopView_flopIvWidth, 0f);
        mFlopIvHeight = (int) typedArray.getDimension(R.styleable.FlopView_flopIvHeight, 0f);
        mFlopIvTop = (int) typedArray.getDimension(R.styleable.FlopView_flopIvTop, 0f);
        mFlopIvBottom = (int) typedArray.getDimension(R.styleable.FlopView_flopIvBottom, 0f);
        marginLeft = (int) typedArray.getDimension(R.styleable.FlopView_flopMarginLeft, 0f);
        marginRight = (int) typedArray.getDimension(R.styleable.FlopView_flopMarginRight, 0f);
        marginTop = (int) typedArray.getDimension(R.styleable.FlopView_flopMarginTop, 0f);
        marginBottom = (int) typedArray.getDimension(R.styleable.FlopView_flopMarginBottom, 0f);


        RelativeLayout.LayoutParams flopViewParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        flopViewParams.setMargins(marginLeft, marginTop, marginRight, marginBottom);
        Log.e("flopWidth", "执行flopTextView");
        flopViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//        mFlopView.setLayoutParams(flopViewParams);
        mFlopView.setLayoutParams(flopViewParams);

        addView(mFlopView);

//        mPaint.setStyle(Paint.ANTI_ALIAS_FLAG);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = widthMeasureSpec;
        mHeight = heightMeasureSpec;
        //视图是个正方形的 所以有宽就足够了 默认值是500 也就是WRAP_CONTENT的时候
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
    }


    public void initImageUrl(String positiviUrl, String oosUrl) {
        mFlopView.initImageUrl(positiviUrl, oosUrl);
    }

    public void initBackground(Bitmap res) {
        if (res != null) {
//            mFlopView.initDraw(res);
        }
    }


    public void setFlopOnAnimationEndListner(int position, FlopOnAnimationEndListner animationEndListner) {
//        CLog.e("flopTextView","positon:"+position+"\tclickFlopPosition:"+mClickFlopPosition);
        mClickFlopPosition = position;
        mFlopAnimEnd = animationEndListner;
    }

    public void startAnimation(Context context, int position) {
        if (mFlopView != null) {
            mFlopView.setOnFlopTextViewListner(this);
//            mFlopView.startAnima(context,position);
            mFlopView.startAnima(position);
        }

    }

    public void setConfig(Builder builder) {
        if (builder.mIsText != 0) {
            mIsText = builder.mIsText;
        }
        if (builder.mTextSize != 0) {
            mTextSize = builder.mTextSize;
        }
        if (builder.mTextColor != 0) {
            mTextColor = builder.mTextColor;
        }
        if (!TextUtils.isEmpty(builder.mText)) {
            mText = builder.mText;
        }
       /* if (builder.mPositiveRes != 0){
            mFlopView.setmPositiveRes(builder.mPositiveRes);
        }
        if (builder.mOppositeRes != 0){
            mFlopView.setmOppositeRes(builder.mOppositeRes);
        }
        if (builder.mPositiveRes != 0){
            mFlopView.setmPositiveRes(builder.mPositiveRes);
        }
        if (builder.mPositiveBitmap != null){
            mFlopView.setmPositiveBitmap(builder.mPositiveBitmap);
        }
        if (builder.mPositiveBitmap != null){
            mFlopView.setmOppositeBitmap(builder.mOppositeBitmap);
        }*/
        if (!TextUtils.isEmpty(builder.mIvUrl)) {
            mIvUrl = builder.mIvUrl;
        }
        if (builder.mIsCustom != 0) {
//            mFlopView.setmIsCustom(builder.mIsCustom);
        }

    }

    @Override
    public void onDrawText(int type) {
        //绘制文字
//        Log.e("FlopTextView","draw绘制文字:"+mIsText+"\t"+mText);

        if (mIsText == 1) {
            mPaint.setColor(mTextColor);
            mPaint.setTextSize(mTextSize);
            //设置图片
            ImageView mIv = new ImageView(context);
            mIv.setId(R.id.flop_text_image);
            RelativeLayout.LayoutParams mIvParams = new RelativeLayout.LayoutParams(mFlopIvWidth, mFlopIvHeight);

            mIvParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mIvParams.setMargins(0, mFlopIvTop, 0, mFlopIvBottom);
            mIv.setLayoutParams(mIvParams);
            Glide.with(context).load(mIvUrl).into(mIv);
//            GlideUtils.loadGif(context, mIvUrl, mIv, R.mipmap.default_icon);
            addView(mIv);

            //设置展示的textView
            TextView mTextView = new TextView(context);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            params.addRule(RelativeLayout.BELOW, mIv.getId());
            params.setMargins(marginLeft, marginTop, marginRight, marginBottom);
            mTextView.setLayoutParams(params);
            mTextView.setTextColor(mTextColor);
            mTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);
            addView(mTextView);
            mTextView.setText(mText);
            //完成后回调
            if (mFlopAnimEnd != null) {
//                CLog.e("flopTextView", "\t回调clickFlopPosition:" + mClickFlopPosition);
                mFlopAnimEnd.onAnimationEnd(mClickFlopPosition);
            }
        }

    }


    public static final class Builder {
        private int mIsText;//是否显示文字，0显示
        private int mIsCustom;// -1是自定义模式  0必须指定图片，不指定会报错   1.自定义模式
        private String mText;//文字内容
        private int mPositiveRes;//正面的图片
        private int mOppositeRes;//反面的图片
        private Bitmap mPositiveBitmap;//正面的bitmap
        private Bitmap mOppositeBitmap;//反面的bitmap
        private String mPositiveUrl;//正面的url
        private String mOppositeUrl;//反面的url

        private int mTextColor;//字体颜色
        private int mTextSize;//字体大小
        private int mIsAnimType;//1执行第一个动画
        private String mIvUrl;//显示小图片地址

        public String getmPositiveUrl() {
            return mPositiveUrl;
        }

        public Builder setmPositiveUrl(String mPositiveUrl) {
            this.mPositiveUrl = mPositiveUrl;
            return this;
        }

        public String getmOppositeUrl() {
            return mOppositeUrl;
        }

        public Builder setmOppositeUrl(String mOppositeUrl) {
            this.mOppositeUrl = mOppositeUrl;
            return this;
        }

        public Builder setmIvUrl(String url) {
            this.mIvUrl = url;
            return this;
        }

        public String getmIvUrl() {
            return mIvUrl;
        }

        public Builder setmIsAnimType(int mIsAnimType) {
            this.mIsAnimType = mIsAnimType;
            return this;
        }

        public Builder setmTextColor(int mTextColor) {
            this.mTextColor = mTextColor;
            return this;
        }

        public Builder setmTextSize(int mTextSize) {
            this.mTextSize = mTextSize;
            return this;
        }

        public Builder setmIsText(int mIsText) {
            this.mIsText = mIsText;
            return this;
        }

        public Builder setmIsCustom(int mIsCustom) {
            this.mIsCustom = mIsCustom;
            return this;
        }

        public Builder setmText(String mText) {
            this.mText = mText;
            return this;
        }

        public Builder setmPositiveRes(int mPositiveRes) {
            this.mPositiveRes = mPositiveRes;
            return this;
        }

        public Builder setmOppositeRes(int mOppositeRes) {
            this.mOppositeRes = mOppositeRes;
            return this;
        }

        public Builder setmPositiveBitmap(Bitmap mPositiveBitmap) {
            this.mPositiveBitmap = mPositiveBitmap;
            return this;
        }

        public Builder setmOppositeBitmap(Bitmap mOppositeBitmap) {
            this.mOppositeBitmap = mOppositeBitmap;
            return this;
        }

        public int getmIsText() {
            return mIsText;
        }

        public int getmIsCustom() {
            return mIsCustom;
        }

        public String getmText() {
            return mText;
        }

        public int getmPositiveRes() {
            return mPositiveRes;
        }

        public int getmOppositeRes() {
            return mOppositeRes;
        }

        public Bitmap getmPositiveBitmap() {
            return mPositiveBitmap;
        }

        public Bitmap getmOppositeBitmap() {
            return mOppositeBitmap;
        }

        public int getmTextColor() {
            return mTextColor;
        }

        public int getmTextSize() {
            return mTextSize;
        }

        public int getmIsAnimType() {
            return mIsAnimType;
        }
    }

    public void relese() {
        if (mFlopView != null)
//            mFlopView.relese();
            mText = null;
    }
}
