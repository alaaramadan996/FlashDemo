package com.alaaramadan.flashdemo.view.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.api.ApiService;
import com.alaaramadan.flashdemo.data.model.CheckRegistration.CheckRegistration;
import com.alaaramadan.flashdemo.databinding.ActivityAuthBinding;
import com.alaaramadan.flashdemo.utils.InternetState;
import com.alaaramadan.flashdemo.view.base.BaseActivity;
import com.alaaramadan.flashdemo.view.fragments.user_cycle.DateFragment;
import com.alaaramadan.flashdemo.view.fragments.user_cycle.NewAcountStepTwoFragment;
import com.alaaramadan.flashdemo.view.fragments.user_cycle.SignUpClosedFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.databinding.DataBindingUtil.setContentView;
import static com.alaaramadan.flashdemo.data.api.RetrofitClient.getClient;
import static com.alaaramadan.flashdemo.utils.HelperMethod.changeLang;
import static com.alaaramadan.flashdemo.utils.HelperMethod.changeLang;
import static com.alaaramadan.flashdemo.utils.HelperMethod.onCreateErrorToast;
import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;
import static com.alaaramadan.flashdemo.utils.HelperMethod.showProgressDialog;

public class AuthActivity extends BaseActivity {
    private ActivityAuthBinding binding;
    private BottomNavigationView bottomNavigationView;
    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeLang(this, "ar");
        super.onCreate( savedInstanceState );

        binding= DataBindingUtil. setContentView( this,R.layout.activity_auth );
        apiService = getClient().create(ApiService.class);
        BottomNavigationView navView = findViewById( R.id.activity_auth_bottom_nav );
        NavController navController = Navigation.findNavController( this, R.id.auth_activity_frameLayout_container );
        NavigationUI.setupWithNavController( navView, navController );
        BottomNav();
    }
    public void CheckRegistration(){
     if (InternetState.isConnected( this )){
         showProgressDialog(this, getString(R.string.please_wait));
         apiService.checkRegistration( "check","UserRegistration" ).enqueue( new Callback<CheckRegistration>() {
             @Override
             public void onResponse(Call<CheckRegistration> call, Response<CheckRegistration> response) {
                 if (response.body().getType()=="success"){
                     replaceFragment( getSupportFragmentManager(),R.id.auth_activity_frameLayout_container,new NewAcountStepTwoFragment() );
                 }else {
                     replaceFragment( getSupportFragmentManager(),R.id.auth_activity_frameLayout_container,new SignUpClosedFragment() );
                 }
             }

             @Override
             public void onFailure(Call<CheckRegistration> call, Throwable t) {
                 onCreateErrorToast( AuthActivity.this,t.getMessage() );
             }
         } );
     }
    }

    public void BottomNav(){
        binding.activityAuthBottomNav.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bottom_nav_auth_condition:
                        break;
                    case R.id.bottom_nav_auth_contact_with_us:
                        break;
                    case R.id.bottom_nav_auth_new_account:
                  CheckRegistration();
                        break;
                    case R.id.bottom_nav_auth_login:
                        break;


                }
                return true;
            }
        } );
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        changeLang(this, "ar");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     return null;
    }
}