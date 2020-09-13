package com.alaaramadan.flashdemo.view.fragments.user_cycle;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.api.ApiService;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.databinding.FragmentSecurityConfirmationBinding;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;
import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;


public class SecurityConfirmationFragment extends BaseFragment {
    private FragmentSecurityConfirmationBinding binding;
    private ApiService apiService;
    private SharedPreferencesManger sharedPreferencesManger;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_security_confirmation, container, false );
        onBack();
        onClickViews();
        return binding.getRoot();
    }

    private void onClickViews() {
        setOnClick( binding.securityConfirmationFragmentBtnConfirm );
    }
    @Override
    public void onClickItem(final View view) {
        disappearKeypad(getActivity(), view);
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.login_fragment_btn_login:
                    Login();
                    break;
                case R.id.login_fragment_btn_recovery:
                    replaceFragment( getFragmentManager(),R.id.auth_activity_frameLayout_container,new AccountRecoveryFragment() );

                    break;

            }

            new Handler().postDelayed( new Runnable() {

                @Override
                public void run() {
                    view.setEnabled(true);
                }
            }, 1000);
        }
    }

    private void Login() {
    }
}