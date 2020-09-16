package com.alaaramadan.flashdemo.view.fragments.user_cycle;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.databinding.FragmentNewAcountStepFiveBinding;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;
import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;


public class NewAcountStepFiveFragment extends BaseFragment {
    private FragmentNewAcountStepFiveBinding binding;
    private SharedPreferencesManger sharedPreferencesManger;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater, R.layout.fragment_new_acount_step_five, container, false);
        onClickViews();
        return binding.getRoot();
    }

    private void onClickViews() {
        setOnClick( binding.newAccountStepFiveBtnNext );
    }
    public void CheckInput(){
        binding.newAccountStepFiveEtPinCode.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pinCodeConfirm=binding.newAccountStepFiveEtPinCodeConfirm.getText().toString();
                if((s.toString().length()==4)&&(pinCodeConfirm.length()==4)) {
                    binding.newAccountStepFiveBtnNext.setBackgroundResource( R.drawable.bk_log_in );
                    sharedPreferencesManger.saveData( getActivity(),"pinCode" ,pinCodeConfirm);
                }
            }
        } );
        binding.newAccountStepFiveEtPinCode.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pinCode=binding.newAccountStepFiveEtPinCode.getText().toString();
                if((s.toString().length()==4)&&(pinCode.length()==4)){
                    binding.newAccountStepFiveBtnNext.setBackgroundResource( R.drawable.bk_log_in );
                    sharedPreferencesManger.saveData( getActivity(),"pinCode" ,pinCode);
                }
            }
        } );
           String pinCode=binding.newAccountStepFiveEtPinCode.getText().toString();
           String pinCodeConfirm=binding.newAccountStepFiveEtPinCodeConfirm.getText().toString();
           if((pinCode.length()==4)&&(pinCode.equals( pinCodeConfirm ))){
               sharedPreferencesManger.saveData( getActivity(),"pinCode" ,pinCode);
               replaceFragment( getFragmentManager(),R.id.auth_activity_frameLayout_container,new NewAccountStepSixFragment() );
           }else {
               binding.newAccountStepFiveTvShowMessage.setText( R.string.error );
           }

    }
    @Override
    public void onClickItem(final View view) {
        disappearKeypad(getActivity(), view);
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.new_account_step_five_btn_next:
                     CheckInput();
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
}