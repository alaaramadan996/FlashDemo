package com.alaaramadan.flashdemo.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.view.base.BaseActivity;

import static com.alaaramadan.flashdemo.utils.HelperMethod.changeLang;

public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeLang(this, "ar");
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );

        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {

              /*  if (loadUserData(SplashActivity.this) == null) {
                    Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                }*/
                Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
                startActivity(intent);
               finish();
            }
        }, 2000);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }
}