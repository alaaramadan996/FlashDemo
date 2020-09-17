package com.alaaramadan.flashdemo.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.databinding.ActivitySplashBinding;
import com.alaaramadan.flashdemo.view.base.BaseActivity;

import static com.alaaramadan.flashdemo.utils.HelperMethod.changeLang;

public class SplashActivity extends BaseActivity {
    private String Udid;
    private ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeLang(this, "ar");
        super.onCreate( savedInstanceState );
        binding= DataBindingUtil.setContentView( this,R.layout.activity_splash );
        Udid = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        sharedPreferencesManger.saveData( SplashActivity.this,"udid_string",Udid );
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