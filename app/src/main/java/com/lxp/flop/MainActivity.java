package com.lxp.flop;

import android.content.Context;
import android.content.Intent;
import android.print.PrinterId;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.lxp.flop.view.FlopPopupWindow;

public class MainActivity extends AppCompatActivity {


    private Context mContext;
    private View mRootViewId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        View decorView = getWindow().getDecorView();
        ViewGroup g = decorView.findViewById(android.R.id.content);
        mRootViewId = g.getChildAt(0);

        findViewById(R.id.landing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FlopPopupWindow(mContext).showAtLocation(mRootViewId, Gravity.CENTER,0,0);
//                startActivity(new Intent(MainActivity.this,LandingActivity.class));
//                startActivity(new Intent(MainActivity.this,FlopActivity.class));
            }
        });

        findViewById(R.id.animate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,AnimateActivity.class));
            }
        });
    }












}
