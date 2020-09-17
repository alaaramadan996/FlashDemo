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
import com.alaaramadan.flashdemo.data.model.ChangePinCode.ChangePinCode;
import com.alaaramadan.flashdemo.data.model.Login.Login;
import com.alaaramadan.flashdemo.data.model.UserLogin.AuthData;
import com.alaaramadan.flashdemo.databinding.FragmentChangePinCodeBinding;
import com.alaaramadan.flashdemo.utils.InternetState;
import com.alaaramadan.flashdemo.view.base.BaseFragment;
import com.alaaramadan.flashdemo.view.fragments.user_cycle.SecurityConfirmationFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.alaaramadan.flashdemo.data.api.RetrofitClient.getClient;
import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;
import static com.alaaramadan.flashdemo.utils.HelperMethod.dismissProgressDialog;
import static com.alaaramadan.flashdemo.utils.HelperMethod.onCreateErrorToast;
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
            if ((pinCode.equals( pinCodeConfirm ))&&(pinCode.length()==4)){
                resetPinCode();
            }else
                {
                binding.changePinCodeFragmentTvShowMessage.setText( R.string.should_same );
            }
        }else {
            binding.changePinCodeFragmentTvShowMessage.setText( R.string.please_enter_pin_code );
        }
    }

    private void resetPinCode() {
        String udid=sharedPreferencesManger.loadData( getActivity(),"udid_string" );
        String oldPassword=sharedPreferencesManger.loadData( getActivity(),"oldPassword" );
        String Password=sharedPreferencesManger.loadData( getActivity(),"password" );
        String pinCode=binding.changePinCodeFragmentEtPinCode.getText().toString();
        String pinCodeConfirm=binding.changePinCodeFragmentEtPinCodeConfirm.getText().toString();
        if ((!pinCode.isEmpty())&&(!pinCodeConfirm.isEmpty())) {
            if (InternetState.isConnected( getActivity() )) {
                apiService.changePinCode( "set","reset_password3",authData.getStatic(),udid,oldPassword,Password,Password,pinCode,pinCodeConfirm ).enqueue( new Callback<ChangePinCode>() {
                    @Override
                    public void onResponse(Call<ChangePinCode> call, Response<ChangePinCode> response) {
                        if (response.body().getType()==1){
                            new Handler().postDelayed( new Runnable() {

                                @Override
                                public void run() {
                                    binding.changePinCodeFragmentTvShowMessage.setText( response.body().getMessage() );
                                }
                            }, 1000);
                            replaceFragment( getFragmentManager(),R.id.home_activity_frame_layout_container,new HomeFragment() );
                        }
                    }

                    @Override
                    public void onFailure(Call<ChangePinCode> call, Throwable t) {
                        binding.changePinCodeFragmentTvShowMessage.setText( R.string.error );
                    }
                } );

            } else {
                dismissProgressDialog();
                onCreateErrorToast( getActivity(), getString( R.string.no_internet_connection ) );
            }
        }else {
            binding.changePinCodeFragmentTvShowMessage.setText( R.string.please_enter_phone_password );
        }
    }
}