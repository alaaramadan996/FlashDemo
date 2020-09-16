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
import com.alaaramadan.flashdemo.databinding.FragmentChangePinCodeBinding;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import static com.alaaramadan.flashdemo.data.api.RetrofitClient.getClient;
import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;
import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;


public class ChangePinCodeFragment extends BaseFragment {
    private FragmentChangePinCodeBinding binding;
    private SharedPreferencesManger sharedPreferencesManger;
    private AuthData authData;
    private ApiService apiService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_change_pin_code, container, false );
        apiService = getClient().create( ApiService.class);
        authData=sharedPreferencesManger.loadAuthData( getActivity());
        onClickViews();
        return binding.getRoot();
    }

    private void onClickViews() {
        setOnClick( binding.changePinCodeFragmentBtnConfirm );
    }

    @Override
    public void onClickItem(final View view) {
        disappearKeypad(getActivity(), view);
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.change_pin_code_fragment_btn_confirm:
                    checkPinCode();
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

    private void checkPinCode() {
        String pinCode=binding.changePinCodeFragmentEtPinCode.getText().toString();
        String pinCodeConfirm=binding.changePinCodeFragmentEtPinCodeConfirm.getText().toString();
        if (pinCode.isEmpty()&&pinCodeConfirm.isEmpty()){
            if ((pinCode==pinCodeConfirm)&&(pinCode.length()==4)){
                resetPassword();
            }else
                {
                binding.changePinCodeFragmentTvShowMessage.setText( R.string.should_same );
            }
        }else {
            binding.changePinCodeFragmentTvShowMessage.setText( R.string.please_enter_pin_code );
        }
    }

    private void resetPassword() {
    }
}