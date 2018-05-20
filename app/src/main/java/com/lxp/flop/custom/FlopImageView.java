package com.lxp.flop.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.lxp.flop.R;
import com.lxp.flop.listner.FlopTextViewListner;

/**
 * Created by lxp  on 2018/5/10.
 * 正面和反面的
 */
public class FlopImageView extends RelativeLayout {
//    private String mPositiveUrl;//正面的url
//    private String mOppositeUrl;//反面的url
    private ImageView mPositiveIv;//正面
    private ImageView mOositiveIv;//反面
    private FlopTextViewListner mTextViewListner;//文字回调
    private Context mContext;

    public FlopImageView(Context context) {
        this(context,null);
    }

    public FlopImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlopImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext  = context;
        initView();

    }

    private void initView() {
        mPositiveIv = new ImageView(mContext);
        mPositiveIv.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        mPositiveIv.setVisibility(View.VISIBLE);
        mOositiveIv = new ImageView(mContext);
        mOositiveIv.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        mOositiveIv.setVisibility(View.GONE);
        addView(mPositiveIv);
        addView(mOositiveIv);
    }



    //初始化view
    public void initImageUrl(String positiviUrl, String oosUrl){
        if (!TextUtils.isEmpty(positiviUrl)) {
            Glide.with(mContext).load(positiviUrl).into(mPositiveIv);
        }else {
            mPositiveIv.setImageResource(R.mipmap.flop_01);
        }

        if (!TextUtils.isEmpty(oosUrl)){
            Glide.with(mContext).load(oosUrl).into(mOositiveIv);
        }else {
            mOositiveIv.setImageResource(R.drawable.shape_circle_white_bg);
        }
    }

    /**
     * 开始动画
     * @param position
     */
    public void startAnima(int position){
        startAnima(position,800);
    }
    public void startAnima(final int position, int duration){
        ScaleAnimation anim = new ScaleAnimation(1,0,1,1
        , Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        anim.setDuration(duration);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mPositiveIv.getVisibility() == View.VISIBLE){
                    mPositiveIv.setVisibility(View.GONE);
                    mOositiveIv.setVisibility(View.VISIBLE);
                }
                if (mTextViewListner != null){
                    mTextViewListner.onDrawText(position);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mPositiveIv.startAnimation(anim);
    }

    /**
     * 设置动画结束后绘制文字回调
     * @param listner
     */
    public void setOnFlopTextViewListner(FlopTextViewListner listner){
        mTextViewListner = listner;
    }

}






