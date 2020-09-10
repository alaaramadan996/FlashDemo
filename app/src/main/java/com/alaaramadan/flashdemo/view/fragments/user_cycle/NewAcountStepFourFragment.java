package com.alaaramadan.flashdemo.view.fragments.user_cycle;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.databinding.FragmentNewAcountStepFourBinding;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;


public class NewAcountStepFourFragment extends BaseFragment {
    private FragmentNewAcountStepFourBinding binding;

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


    public void checkInputPassword(){
        binding.newAccountStepFourEtPassword.setOnFocusChangeListener( new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    if (binding.newAccountStepFourEtPassword.getText().toString().length()<8){
                        binding.newAccountStepFourEtShowMessage.setText( "كلمة المرور يجب ان تكون 8 حروف و ارقام" );
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
                        binding.newAccountStepFourEtShowMessage.setText( "كلمة المرور يجب ان تكون 8 حروف و ارقام" );
                    }
                }
            }
        } );
    }

}