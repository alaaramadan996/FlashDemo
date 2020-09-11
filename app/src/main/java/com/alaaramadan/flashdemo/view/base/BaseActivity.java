package com.alaaramadan.flashdemo.view.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;


public abstract class BaseActivity extends AppCompatActivity {

    public BaseFragment baseFragment;
    public SharedPreferencesManger sharedPreferencesManger;

    public String getSharedPreferencesManger() {

        String a= sharedPreferencesManger.loadData( BaseActivity.this,"api_token" );
        return a;
    }


    @Override
    public void onBackPressed() {
        baseFragment.onBack();
    }

    public void superBackPressed() {
        super.onBackPressed();
    }

    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container,
                                      Bundle savedInstanceState);
}
