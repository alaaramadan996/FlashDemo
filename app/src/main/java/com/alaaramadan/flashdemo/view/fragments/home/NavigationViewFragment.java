package com.alaaramadan.flashdemo.view.fragments.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.databinding.FragmentNavigationViewBinding;
import com.alaaramadan.flashdemo.view.activities.AuthActivity;
import com.alaaramadan.flashdemo.view.base.BaseFragment;
import com.alaaramadan.flashdemo.view.fragments.user_cycle.AccountRecoveryFragment;

import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;
import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;


public class NavigationViewFragment extends BaseFragment {
     private FragmentNavigationViewBinding binding;
     private SharedPreferencesManger sharedPreferencesManger;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater, R.layout.fragment_navigation_view, container, false);
        onClickViews();
        return binding.getRoot();
    }


    private void onClickViews() {
        setOnClick( binding.homeActivityBtnAccountPersonal );
        setOnClick( binding.homeActivityBtnAccountSecurity );
        setOnClick( binding.homeActivityBtnFlash );
        setOnClick( binding.homeActivityBtnLogOut );
    }

    @Override
    public void onClickItem(final View view) {
        disappearKeypad(getActivity(), view);
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.home_activity_btn_account_personal:
                    replaceFragment(getFragmentManager(), R.id.home_activity_frame_layout_container, new PersonalAccountFragment());
                    break;
                case R.id.home_activity_btn_account_security:
                    replaceFragment(getFragmentManager(), R.id.home_activity_frame_layout_container, new CheckPasswordFragment());
                    break;
                case R.id.home_activity_btn_flash:
                    replaceFragment(getFragmentManager(), R.id.home_activity_frame_layout_container, new HomeFragment());
                    break;
                case R.id.home_activity_btn_log_out:
                    sharedPreferencesManger.clean( getActivity() );
                    Intent intent =new Intent( getActivity(), AuthActivity.class );
                    startActivity( intent );
                    getActivity().finish();
                    break;

            }

            new Handler().postDelayed( new Runnable() {

                @Override
                public void run() {
                    view.setEnabled(true);
                }
            }, 1500);
        }
    }
}