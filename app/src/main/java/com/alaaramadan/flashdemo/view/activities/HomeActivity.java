package com.alaaramadan.flashdemo.view.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.databinding.ActivityHomeBinding;
import com.alaaramadan.flashdemo.view.base.BaseActivity;
import com.alaaramadan.flashdemo.view.fragments.home.HomeFragment;
import com.alaaramadan.flashdemo.view.fragments.home.PersonalAccountFragment;
import com.alaaramadan.flashdemo.view.fragments.home.RestorePasswordFragment;

import static com.alaaramadan.flashdemo.utils.HelperMethod.changeLang;
import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;

public class HomeActivity extends BaseActivity {
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeLang(this, "ar");
        super.onCreate( savedInstanceState );
        binding= DataBindingUtil. setContentView( this,R.layout.activity_home );
        replaceFragment( getSupportFragmentManager(),R.id.home_activity_frame_layout_container,new HomeFragment() );
        onClockItem();
    }
    public void onClockItem(){

        final DrawerLayout drawerLayout = findViewById( R.id.drawerlayout );
        binding.activityHomeBtnNavView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer( GravityCompat.START );
            }
        } );

        binding.homeActivityImgFlash.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment( getSupportFragmentManager(),R.id.home_activity_frame_layout_container,new HomeFragment() );
            }
        } );

        binding.homeActivityBtnAccountPersonal.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment( getSupportFragmentManager(),R.id.home_activity_frame_layout_container,new PersonalAccountFragment() );
            }
        } );

        binding.homeActivityBtnAccountSecurity.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment( getSupportFragmentManager(),R.id.home_activity_frame_layout_container,new RestorePasswordFragment() );
            }
        } );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }
}