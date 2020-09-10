package com.alaaramadan.flashdemo.view.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.view.base.BaseActivity;

import static com.alaaramadan.flashdemo.utils.HelperMethod.changeLang;

public class AdvertisementActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeLang(this, "ar");
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_advertisement );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }
}