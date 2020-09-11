package com.alaaramadan.flashdemo.view.fragments.user_cycle;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.databinding.FragmentNewAcountStepFourBinding;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;
import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;


public class NewAcountStepFourFragment extends BaseFragment {
    private FragmentNewAcountStepFourBinding binding;
    private SharedPreferencesManger sharedPreferencesManger;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_new_acount_step_four, container, false );
        onClickViews();
        checkInputConfirmPassword();
        checkInputPassword();
        return binding.getRoot();
    }

    private void onClickViews() {
        setOnClick( binding.newAccountStepFourBtnNext );
        setOnClick( binding.newAccountStepFourEtPassword );
        setOnClick( binding.newAccountStepFourEtConfirmPassword );
    }


    @Override
    public void onClickItem(final View view) {
        disappearKeypad(getActivity(), view);
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.new_account_step_four_btn_next:
                       checkPassword();
                    break;
                case R.id.new_account_step_four_et_confirm_password:

                    break;
                case R.id.new_account_step_four_et_password:

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
        String password =binding.newAccountStepFourEtPassword.getText().toString();
        String confirm =binding.newAccountStepFourEtConfirmPassword.getText().toString();
        if (password==confirm)
        {
            if (password.length()>8){
                sharedPreferencesManger.saveData( getActivity(),"password" ,password);
                replaceFragment( getFragmentManager(),R.id.auth_activity_frameLayout_container,new NewAcountStepFiveFragment() );
            }
            else {
                binding.newAccountStepFourEtShowMessage.setText( R.string.check_password_They_should_be_the_same );
            }

        }else {
            binding.newAccountStepFourEtShowMessage.setText( R.string.check_password_They_should_be_the_same );
        }
    }


    public void checkInputPassword(){
        binding.newAccountStepFourEtPassword.setOnFocusChangeListener( new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    if (binding.newAccountStepFourEtPassword.getText().toString().length()<8){
                        binding.newAccountStepFourEtShowMessage.setText( R.string.check_password_Password_must_be_8_letters_and_numbers );
                    }
                    String password=binding.newAccountStepFourEtPassword.getText().toString();
                    String confirm_password=binding.newAccountStepFourEtConfirmPassword.getText().toString();
                    if (password==confirm_password){
                        binding.newAccountStepFourBtnNext.setBackgroundResource( R.drawable.bk_log_in );
                    }
                }
            }
        } );
    }

    public void checkInputConfirmPassword(){
        binding.newAccountStepFourEtConfirmPassword.setOnFocusChangeListener( new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    if (binding.newAccountStepFourEtPassword.getText().toString().length()<8){
                        binding.newAccountStepFourEtShowMessage.setText( R.string.check_password_Password_must_be_8_letters_and_numbers );
                    }
                    String password=binding.newAccountStepFourEtPassword.getText().toString();
                    String confirm_password=binding.newAccountStepFourEtConfirmPassword.getText().toString();
                    if (password==confirm_password){
                        binding.newAccountStepFourBtnNext.setBackgroundResource( R.drawable.bk_log_in );
                    }
                }
            }
        } );
    }

}