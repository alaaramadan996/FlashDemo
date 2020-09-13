package com.alaaramadan.flashdemo.view.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.databinding.ActivityHomeBinding;
import com.alaaramadan.flashdemo.view.base.BaseActivity;

import static com.alaaramadan.flashdemo.utils.HelperMethod.changeLang;

public class HomeActivity extends BaseActivity {
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeLang(this, "ar");
        super.onCreate( savedInstanceState );
        binding= DataBindingUtil. setContentView( this,R.layout.activity_home );
        final DrawerLayout drawerLayout = findViewById( R.id.drawerlayout );
        binding.activityHomeBtnNavView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer( GravityCompat.START );
            }
        } );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }
}