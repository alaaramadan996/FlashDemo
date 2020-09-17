package com.alaaramadan.flashdemo.view.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.databinding.ActivityHomeBinding;
import com.alaaramadan.flashdemo.databinding.ActivityHomeContaintBinding;
import com.alaaramadan.flashdemo.view.base.BaseActivity;
import com.alaaramadan.flashdemo.view.fragments.home.CheckPasswordFragment;
import com.alaaramadan.flashdemo.view.fragments.home.HomeFragment;
import com.alaaramadan.flashdemo.view.fragments.home.NavigationViewFragment;
import com.alaaramadan.flashdemo.view.fragments.home.PersonalAccountFragment;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationView;

import butterknife.OnClick;

import static com.alaaramadan.flashdemo.utils.HelperMethod.changeLang;
import static com.alaaramadan.flashdemo.utils.HelperMethod.onCreateErrorToast;
import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;

public class HomeActivity extends BaseActivity {
    private ActivityHomeBinding binding;
    private ActivityHomeContaintBinding homeContaintBinding;
    public  DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeLang( this, "ar" );
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_home );
        drawerLayout = findViewById( R.id.drawerlayout );
        drawerLayout.openDrawer( GravityCompat.END );
       // replaceFragment( getSupportFragmentManager(),R.id.home_activity_nav_view,new NavigationViewFragment() );
//       item.setBackgroundResource( R.drawable.bk_btn_nav_view_red );
        replaceFragment( getSupportFragmentManager(), R.id.home_activity_frame_layout_container, new HomeFragment() );
        onClockItem();

    }


    public void onClockItem() {
      /* binding.navView.setNavigationItemSelectedListener( new NavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               switch (menuItem.getItemId())
               {
                   case R.id.home_activity_btn_account_personal:
                       onCreateErrorToast( HomeActivity.this, getString( R.string.error ) );
                        replaceFragment(getSupportFragmentManager(), R.id.home_activity_frame_layout_container, new PersonalAccountFragment());
                   break;
                   case R.id.home_activity_btn_account_security:
                       onCreateErrorToast( HomeActivity.this, getString( R.string.error ) );
                        replaceFragment(getSupportFragmentManager(), R.id.home_activity_frame_layout_container, new CheckPasswordFragment());
                   break;
                   case R.id.home_activity_btn_flash:
                       onCreateErrorToast( HomeActivity.this, getString( R.string.error ) );
                        replaceFragment(getSupportFragmentManager(), R.id.home_activity_frame_layout_container, new HomeFragment());
                   break;
                   case R.id.home_activity_btn_log_out:
                       onCreateErrorToast( HomeActivity.this, getString( R.string.error ) );
                       sharedPreferencesManger.clean( HomeActivity.this );
                       Intent intent =new Intent( HomeActivity.this,AuthActivity.class );
                       startActivity( intent );
                       break;
               }
               return true;
        }
    } );*/

        ImageView imageView = findViewById( R.id.activity_home_btn_nav_view );
        imageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer( GravityCompat.END );

            }
        } );
        ImageView imageVie = findViewById( R.id.home_activity_img_flash );
        imageVie.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment( getSupportFragmentManager(), R.id.home_activity_frame_layout_container, new HomeFragment() );

            }
        } );

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }



}