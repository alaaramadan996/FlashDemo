package com.alaaramadan.flashdemo.view.fragments.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.api.ApiService;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.data.model.UserLogin.AuthData;
import com.alaaramadan.flashdemo.databinding.FragmentRestorePasswordBinding;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;


public class RestorePasswordFragment extends BaseFragment {
    private FragmentRestorePasswordBinding binding;
    private SharedPreferencesManger sharedPreferencesManger;
    private AuthData authData;
    private ApiService apiService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater,  R.layout.fragment_restore_password, container, false);
        onClickViews();
        return binding.getRoot();
    }

    private void onClickViews() {
        setOnClick( binding.restorePasswordFragmentBtnConfirm );
    }

    @Override
    public void onClickItem(final View view) {
        disappearKeypad(getActivity(), view);
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.restore_password_fragment_btn_confirm:
                    checkPassword();
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

    private void checkPassword() {
        String password=binding.restorePasswordFragmentEtNewPassword.getText().toString();
        String passwordConfirm=binding.restorePasswordFragmentEtNewPasswordConfirm.getText().toString();
        if ((password.isEmpty())&&(passwordConfirm.isEmpty())){
            if ((password==passwordConfirm)&&(password.length()>=8)){
               sharedPreferencesManger.saveData( getActivity(),"savePassword" ,password);
            }else
            {
                binding.restorePasswordFragmentTvShowMessage.setText( R.string.check_password_Password_must_be_8_letters_and_numbers );
            }
        }else {
            binding.restorePasswordFragmentTvShowMessage.setText( R.string.check_password_They_should_be_the_same );
        }
    }

}