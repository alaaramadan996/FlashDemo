package com.alaaramadan.flashdemo.view.activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.databinding.ActivityAdvertisementBinding;
import com.alaaramadan.flashdemo.view.base.BaseActivity;
import com.alaaramadan.flashdemo.view.fragments.adsvertistement.AdvertisementFragment;
import com.alaaramadan.flashdemo.view.fragments.home.PersonalAccountFragment;
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
    private  String check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeLang(this, "ar");
        super.onCreate( savedInstanceState );
        binding= DataBindingUtil.setContentView( this,R.layout.activity_advertisement );
        check=sharedPreferencesManger.loadData( this,"check" );
        int a=Integer.parseInt( check );
        binding.error.setText( check );

        new Handler().postDelayed( new Runnable() {

            @Override
            public void run() {
                checkFragment(a);
            }
        }, 500);


    }


    public void checkFragment(int check2){
         int gender=1;
        if (check2==gender){
            replaceFragment( getSupportFragmentManager(),R.id.advertisement_activity_container,new GenderFragment() );
        }else{
            int ads=2;
            if (check2==ads){
                replaceFragment( getSupportFragmentManager(),R.id.advertisement_activity_container,new AdvertisementFragment() );
            }else {
                int city=3;
                if (check2==city){
                    replaceFragment( getSupportFragmentManager(),R.id.advertisement_activity_container,new CityListFragment() );
                }else {
                    int governorate=4;
                    if (check2==governorate){
                        replaceFragment( getSupportFragmentManager(),R.id.advertisement_activity_container,new GovernorateListFragment() );
                    }else {
                        int date=5;
                        if (check2==date){
                            replaceFragment( getSupportFragmentManager(),R.id.advertisement_activity_container,new DateFragment() );
                        }
                    }
                }
            }
        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }
}