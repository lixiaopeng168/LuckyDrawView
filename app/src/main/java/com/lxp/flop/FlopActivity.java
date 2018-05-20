package com.lxp.flop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.lxp.flop.custom.FlopTextView;
import com.lxp.flop.anim.Rotatable;

/**
 * Created by lxp  on 2018/4/26.
 */
public class FlopActivity extends AppCompatActivity implements View.OnClickListener {

    private View flopImgRotate01;
    private View flopImgRotate02;
    private RelativeLayout flopImgRotateView01;
    private FlopTextView flopTextView;
//    private FlopView flopView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_flop);
        initView();

    }

    private void initView() {
        flopImgRotate01 = findViewById(R.id.flopImgRotate01);
        flopImgRotate02 = findViewById(R.id.flopImgRotate02);
        flopImgRotateView01 = findViewById(R.id.flopImgRotateView01);

        flopImgRotate01.setBackgroundResource(R.mipmap.flop_01);
        flopImgRotate02.setBackgroundResource(R.mipmap.flop_02);

        flopImgRotate02.setVisibility(View.INVISIBLE);
        flopImgRotate01.setVisibility(View.VISIBLE);

        flopTextView = findViewById(R.id.flopTextView);
        flopTextView.initBackground(BitmapFactory.decodeResource(getResources(),R.mipmap.flop_01));
        findViewById(R.id.flopImgRotateView01).setOnClickListener(this);
        flopTextView.setOnClickListener(this);
//        flopView = findViewById(R.id.flopImgView);
//        flopView.initDraw(BitmapFactory.decodeResource(getResources(),R.mipmap.flop_01));
//                flopView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.flopImgRotateView01:
                cardView();
                break;
            case R.id.flopTextView:
                FlopTextView.Builder builder = new FlopTextView.Builder();
                builder.setmIsCustom(1).setmIsText(1).setmTextColor(Color.RED)
                        .setmTextSize(10).setmIsAnimType(1)
                        .setmPositiveBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.flop_03))
                        .setmOppositeBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.flop_04))
                        .setmText("是谁在用琵琶弹奏一曲东风破，歌声的旋律让人沉醉。今天头条新闻是高利贷3万要还800万。我只想默默的说一句，老妹，你牛啊。");
                flopTextView.setConfig(builder);
                flopTextView.startAnimation(this,1);
//                flopView.setmIsText(0);
//                flopView.setmOppositeBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.flop_02));
//                flopView.setmPositiveBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.flop_01));
//                flopView.setmIsAnimaType(1);
//                flopView.setmIsCustom(1);
//                flopView.setmTextColor(Color.RED);
//                flopView.setmTextSize(50);
//                flopView.setmText("是谁在用琵琶弹奏一曲东风破，歌声的旋律让人沉醉。今天头条新闻是高利贷3万要还800万。我只想默默的说一句，老妹，你牛啊。");

//                flopView.startAnima(this,1);
                break;
        }
    }




    /**
     * 反转卡牌
     */
    public void cardView(){
        if (View.VISIBLE == flopImgRotate01.getVisibility()){
            //先抖动
//            sharkAnim(flopImgRotate01,0,180);

            Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    anim01();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
//            animation.start();
            flopImgRotate01.startAnimation(animation);



        }else if (View.VISIBLE == flopImgRotate02.getVisibility()){
//            rotate180(flopImgRotate02,180,360);
            anim02();

        }

    }

    private void anim02() {
        Rotatable rotatable = new Rotatable.Builder(flopImgRotateView01)
                .sides(R.id.flopImgRotate01, R.id.flopImgRotate02)
                .direction(Rotatable.ROTATE_Y)
                .rotationCount(1)
                .build();
        rotatable.setTouchEnable(false);
        rotatable.rotate(Rotatable.ROTATE_Y, -180, 300);
    }

    private void anim01(){

        Rotatable rotatable = new Rotatable.Builder(flopImgRotateView01)
                .sides(R.id.flopImgRotate01, R.id.flopImgRotate02)
                .direction(Rotatable.ROTATE_Y)
                .rotationCount(1)
                .build();
        rotatable.setTouchEnable(false);

        rotatable.rotate(Rotatable.ROTATE_Y, -90, 800, new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                //隐藏第一个
                flopImgRotate02.setVisibility(View.VISIBLE);
                flopImgRotate01.setVisibility(View.INVISIBLE);
                cardView();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flopTextView.relese();
        flopTextView = null;
    }
}
