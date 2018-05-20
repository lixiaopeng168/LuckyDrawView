package com.lxp.flop.custom;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import com.lxp.flop.R;
import com.lxp.flop.anim.RotateYAnimation;
import com.lxp.flop.listner.FlopTextViewListner;

import java.lang.reflect.TypeVariable;

/**
 * Created by lxp  on 2018/4/26.
 */
public class FlopView extends View{

//    private int mIsText;//是否显示文字，0显示
    private int mIsCustom;// -1是自定义模式  0必须指定图片，不指定会报错   1.自定义模式
//    private String mText;//文字内容
    private int mPositiveRes;//正面的图片
    private int mOppositeRes;//反面的图片
    private Bitmap mPositiveBitmap;//正面的bitmap
    private Bitmap mOppositeBitmap;//反面的bitmap
//    private BitmapDrawable mPositiveBmDrawable;//动画
//    private int mTextColor;//字体颜色
//    private int mTextSize;//字体大小

    private Paint mPaint;//画笔

    private int mIsAnimaType;//执行动画 0 默认图片  1 第一张  2 第二张

    private int mWidth,mHeight;

    private FlopTextViewListner mTextViewListner;//文字回调


//    private TextVi

    public FlopView(Context context) {
        this(context,null);
    }

    public FlopView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FlopView);
        try {
            mIsCustom = typedArray.getInteger(R.styleable.FlopView_flopIsCustom,0);


            if (mIsCustom == 0){
                mPositiveRes = typedArray.getResourceId(R.styleable.FlopView_flopOppositeRes,0);
                mOppositeRes = typedArray.getResourceId(R.styleable.FlopView_flopOppositeRes,0);
                if (mPositiveRes == 0 || mOppositeRes == 0)
                    throw new RuntimeException("当isCustom为0的时候，必须指定正面和反面的图片");
                mOppositeBitmap = BitmapFactory.decodeResource(getResources(),mOppositeRes);
                mPositiveBitmap = BitmapFactory.decodeResource(getResources(),mPositiveRes);
            }


        }catch (Exception e){

        }finally {
            typedArray.recycle();
        }
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setDither(true);//描边
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = 200;
        int heightSize = 300;

        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (modeWidth == MeasureSpec.AT_MOST){ //warp_content
            widthSize = Math.min(sizeWidth,widthSize);
        }else if (modeWidth == MeasureSpec.EXACTLY){ //指定大小
            widthSize = sizeWidth;
        }else {

        }

        if (modeHeight == MeasureSpec.AT_MOST){
            heightSize = Math.min(sizeHeight,heightSize);
        }else if (modeHeight == MeasureSpec.EXACTLY){
            heightSize = sizeHeight;
        }
        //不减动画会和边界交叉
        mWidth = widthSize-0;
        mHeight = heightSize;
        setMeasuredDimension(widthSize,heightSize);
    }
    public void initDraw(Bitmap bm){
        mIsCustom = 1;
        mIsAnimaType = 0;

        if (bm == null)
            mPositiveBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.flop_01);
        else
            mPositiveBitmap = bm;

        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mIsCustom == -1){
            //先不执行
        }else {
            if (mIsCustom == 1){
                if (mIsAnimaType == 0){

                    Rect rect = new Rect(0,0,mWidth,mHeight);

                    canvas.drawBitmap(mPositiveBitmap,null,rect,mPaint);
                }
                else if (mIsAnimaType == 1){
                    Rect rect = new Rect(0,0,mWidth,mHeight);

                    canvas.drawBitmap(mPositiveBitmap,null,rect,mPaint);
                }

                else if (mIsAnimaType == 2){
                    Rect rect = new Rect(0,0,mWidth,mHeight);

                    canvas.drawBitmap(mOppositeBitmap,null,rect,mPaint);
//                    canvas.save();
//                    drawText(canvas,0);

                }
                else if (mIsAnimaType == 3){
                    Rect rect = new Rect(0,0,mWidth,mHeight);

                    canvas.drawBitmap(mOppositeBitmap,null,rect,mPaint);
                }

            }
           /* else if (mIsCustom == 0){  //为0的时候是默认res文件指定动画
                if (mIsAnimaType == 0){
                    Rect rect = new Rect(0,0,getWidth(),getHeight());
                    canvas.drawBitmap(mOppositeBitmap,null,rect,mPaint);
                }
                else if (mIsAnimaType == 1){
                    Rect rect = new Rect(0,0,getWidth(),getHeight());

                    canvas.drawBitmap(mOppositeBitmap,null,rect,mPaint);
                    canvas.rotate(-45,0,0);

                }

                else if (mIsAnimaType == 2){
                    Rect rect = new Rect(0,0,getWidth(),getHeight());

                    canvas.drawBitmap(mOppositeBitmap,null,rect,mPaint);
                    canvas.save();
                    if (mIsText == 0){
                        drawText(canvas);
                    }

                }
            }*/
//            canvas.restore();
        }
    }

    /**开始执行动画  **/
    public void startAnima(Context context,int position){
        RotateYAnimation anim  = new RotateYAnimation(1000,-90);
        anim.setFillAfter(false);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                animTowStart();
                mIsAnimaType = 2;
                invalidate();
                if (mTextViewListner != null){
                    mTextViewListner.onDrawText(mIsAnimaType);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        startAnimation(anim);
       /* RotateAnimation anim =new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillAfter(true); // 设置保持动画最后的状态
        anim.setDuration(3000); // 设置动画时间
        anim.setInterpolator(new AccelerateInterpolator()); // 设置插入器
        startAnimation(anim);*/



       /* Animator animator = AnimatorInflater.loadAnimator(context, R.animator.flop_anim_y);
        animator.setTarget(FlopView.this);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animTowStart();
                mIsAnimaType = 2;
                invalidate();
            }
        });
        animator.start();*/
       /* ObjectAnimator obj = ObjectAnimator.ofFloat(FlopView.this,"rotationY",0,-90);
        obj.setDuration(1000);
        obj.setRepeatCount(1);
        obj.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animTowStart();
                mIsAnimaType = 2;
                invalidate();

            }
        });
        obj.start();*/
      /*  Animation animShake = AnimationUtils.loadAnimation(context, R.anim.shake);
        animShake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ObjectAnimator obj = ObjectAnimator.ofFloat(FlopView.this,"rotationY",0,-90);
                obj.setDuration(1000);
                obj.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        animTowStart();
                            mIsAnimaType = 2;
                            invalidate();

                    }
                });
                obj.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        startAnimation(animShake);*/


    }
    /**  执行第二个动画  **/
    private void animTowStart(){
//        RotateYAnimation anim = new RotateYAnimation(100,-180);

        /*ObjectAnimator anim = ObjectAnimator.ofFloat(FlopView.this,"rotationY",-90,-180);
        anim.setDuration(1000);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

//                mIsAnimaType = 3;
//                invalidate();
                //绘制文字
                if (mTextViewListner != null){
                    mTextViewListner.onDrawText(mIsAnimaType);
                }
            }
        });
        anim.start();*/
    }




    public void setOnFlopTextViewListner(FlopTextViewListner textViewListner){
        this.mTextViewListner   =   textViewListner;
    }

    public void relese(){
        mTextViewListner = null;
        mPositiveBitmap = null;
        mOppositeBitmap = null;
    }

    public void setmIsCustom(int mIsCustom) {
        this.mIsCustom = mIsCustom;
    }




    public void setmPositiveRes(int mPositiveRes) {
        this.mPositiveRes = mPositiveRes;
    }

    public void setmOppositeRes(int mOppositeRes) {
        this.mOppositeRes = mOppositeRes;
    }


    public void setmPositiveBitmap(Bitmap mPositiveBitmap) {
        this.mPositiveBitmap = mPositiveBitmap;
    }



    public void setmOppositeBitmap(Bitmap mOppositeBitmap) {
        this.mOppositeBitmap = mOppositeBitmap;
    }




    public void setmIsAnimaType(int mIsAnimaType) {
        this.mIsAnimaType = mIsAnimaType;
    }
}























