package com.alaaramadan.flashdemo.view.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.databinding.ActivityAdvertisementBinding;
import com.alaaramadan.flashdemo.view.base.BaseActivity;
import com.alaaramadan.flashdemo.view.fragments.user_cycle.CityListFragment;
import com.alaaramadan.flashdemo.view.fragments.user_cycle.DateFragment;
import com.alaaramadan.flashdemo.view.fragments.user_cycle.GenderFragment;
import com.alaaramadan.flashdemo.view.fragments.user_cycle.GovernorateListFragment;
import com.alaaramadan.flashdemo.view.fragments.user_cycle.NewAcountStepThreeFragment;

import static com.alaaramadan.flashdemo.utils.HelperMethod.changeLang;
import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;

public class AdvertisementActivity extends BaseActivity {
    private ActivityAdvertisementBinding binding;
    private SharedPreferencesManger sharedPreferencesManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeLang(this, "ar");
        super.onCreate( savedInstanceState );
        binding= DataBindingUtil.setContentView( AdvertisementActivity.this,R.layout.activity_advertisement );
        checkFragment();

    }
    public void checkFragment(){
        String check=sharedPreferencesManger.loadData( AdvertisementActivity.this,"check" );
        if (check=="gender"){
            replaceFragment( getSupportFragmentManager(),R.id.advertisement_activity_container,new GenderFragment() );
        }
        if (check=="city"){
            replaceFragment( getSupportFragmentManager(),R.id.advertisement_activity_container,new CityListFragment() );
        }
        if (check=="governorate"){
            replaceFragment( getSupportFragmentManager(),R.id.advertisement_activity_container,new GovernorateListFragment() );
        }
        if (check=="date"){
            replaceFragment( getSupportFragmentManager(),R.id.advertisement_activity_container,new DateFragment() );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }
}