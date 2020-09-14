package com.alaaramadan.flashdemo.view.fragments.user_cycle;

import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.api.ApiService;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.data.model.Login.Login;
import com.alaaramadan.flashdemo.databinding.FragmentLoginBinding;
import com.alaaramadan.flashdemo.utils.InternetState;
import com.alaaramadan.flashdemo.view.activities.AuthActivity;
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


public class LoginFragment extends BaseFragment {
    private FragmentLoginBinding binding;
    private ApiService apiService;
    private String Udid;
    private SharedPreferencesManger sharedPreferencesManger;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_login, container, false  );
        Udid = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
        sharedPreferencesManger.saveData( getActivity(),"udid_string",Udid );
        apiService = getClient().create(ApiService.class);
        checkInput();
        onClickViews();
        return binding.getRoot();
    }

    private void onClickViews() {
        setOnClick( binding.loginFragmentBtnLogin );
        setOnClick( binding.loginFragmentBtnRecovery );
    }

    @Override
    public void onClickItem(final View view) {
        disappearKeypad(getActivity(), view);
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.login_fragment_btn_login:
                    checkLogin();
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
        String phone=binding.loginFragmentEtPhone.getText().toString();
        String password=binding.loginFragmentEtPassword.getText().toString();
        binding.loginFragmentEtPhone.setOnFocusChangeListener( new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if ((password.isEmpty())&&(phone.isEmpty())){
                    binding.loginFragmentBtnLogin.setBackgroundResource( R.drawable.bk_log_in );
                }
            }
        } );

        binding.loginFragmentEtPassword.setOnFocusChangeListener( new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if ((password.isEmpty())&&(phone.isEmpty())){
                    binding.loginFragmentBtnLogin.setBackgroundResource( R.drawable.bk_log_in );
                }
            }
        } );
    }

    public void checkLogin(){
        String phone=binding.loginFragmentEtPhone.getText().toString();
        String password=binding.loginFragmentEtPassword.getText().toString();
        if ((password.isEmpty())&&(phone.isEmpty())) {
            if (InternetState.isConnected( getActivity() )) {
                showProgressDialog( getActivity(), getString( R.string.please_wait ) );
                apiService.checkAuth( "check", "UserPhoneAndPassword", phone, password ).enqueue( new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        if (response.body().getType() == "success") {
                            sharedPreferencesManger.saveData( getActivity(),"phone",phone );
                            sharedPreferencesManger.saveData( getActivity(),"password",password );
                            replaceFragment( getFragmentManager(), R.id.auth_activity_frameLayout_container, new SecurityConfirmationFragment() );

                        } else {
                            binding.loginFragmentTvShowMessage.setText( response.body().getData().getTitle() );
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        onCreateErrorToast( getActivity(), getString( R.string.error ) );
                        dismissProgressDialog();
                    }
                } );

            } else {
                dismissProgressDialog();
                onCreateErrorToast( getActivity(), getString( R.string.no_internet_connection ) );
            }
        }else {
            binding.loginFragmentTvShowMessage.setText( R.string.please_enter_phone_password );
        }
    }


}