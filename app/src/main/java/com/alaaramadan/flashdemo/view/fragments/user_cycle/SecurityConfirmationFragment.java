package com.alaaramadan.flashdemo.view.fragments.user_cycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.api.ApiService;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.data.model.CheckPhone.CheckPhone;
import com.alaaramadan.flashdemo.data.model.UserLogin.UserLogin;
import com.alaaramadan.flashdemo.databinding.FragmentSecurityConfirmationBinding;
import com.alaaramadan.flashdemo.utils.InternetState;
import com.alaaramadan.flashdemo.view.activities.AdvertisementActivity;
import com.alaaramadan.flashdemo.view.activities.HomeActivity;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.alaaramadan.flashdemo.data.api.RetrofitClient.getClient;
import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;
import static com.alaaramadan.flashdemo.utils.HelperMethod.dismissProgressDialog;
import static com.alaaramadan.flashdemo.utils.HelperMethod.onCreateErrorToast;
import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;
import static com.alaaramadan.flashdemo.utils.HelperMethod.showProgressDialog;


public class SecurityConfirmationFragment extends BaseFragment {
    private FragmentSecurityConfirmationBinding binding;
    private ApiService apiService;
    private SharedPreferencesManger sharedPreferencesManger;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_security_confirmation, container, false );
        apiService = getClient().create(ApiService.class);
        checkInput();
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
    public void checkInput(){
        binding.securityConfirmationFragmentEtPinCode.setOnFocusChangeListener( new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (binding.securityConfirmationFragmentEtPinCode.getText().toString().length()==4){
                    binding.securityConfirmationFragmentBtnConfirm.setBackgroundResource( R.drawable.bk_log_in );
                }
            }
        } );
    }
    private void Login() {
        String pin_code=binding.securityConfirmationFragmentEtPinCode.getText().toString();
        if (pin_code.length()==4){
            String password=sharedPreferencesManger.loadData( getActivity(),"password" );
            String phone=sharedPreferencesManger.loadData( getActivity(),"phone" );
            String udid=sharedPreferencesManger.loadData( getActivity(),"udid_string" );
            if (InternetState.isConnected( getActivity() )){
                showProgressDialog(getActivity(), getString(R.string.please_wait));
                apiService.loginAuth( "check" ,"login",phone,password,pin_code,udid).enqueue( new Callback<UserLogin>() {
                    @Override
                    public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                        if (response.body().getType()=="success"){
                            sharedPreferencesManger.saveData( getActivity(),"AuthData",response.body().getData() );
                            if (response.body().getData().getInternalAds()==1){
                                Intent intent=new Intent( getActivity(), AdvertisementActivity.class );
                                startActivity( intent );
                                getActivity().finish();
                                String check="ads";
                                sharedPreferencesManger.saveData( getActivity(),"check",check );
                            }if (response.body().getData().getInternalAds()==0){
                            Intent intent=new Intent( getActivity(), HomeActivity.class );
                            startActivity( intent );
                            getActivity().finish();
                            }
                        }
                        else {
                            binding.securityConfirmationFragmentTvShowMessage.setText( response.body().getData().getTitle() );
                        }
                    }

                    @Override
                    public void onFailure(Call<UserLogin> call, Throwable t) {
                          binding.securityConfirmationFragmentTvShowMessage.setText( t.getMessage() );
                    }
                } );

            }else {
                dismissProgressDialog();
                onCreateErrorToast(getActivity(), getString(R.string.no_internet_connection));
                  }
        }else {
            binding.securityConfirmationFragmentTvShowMessage.setText( R.string.please_enter_pin_code );
              }
    }
}