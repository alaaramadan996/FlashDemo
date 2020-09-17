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
import com.alaaramadan.flashdemo.data.model.CheckOldPassword.CheckOldPassword;
import com.alaaramadan.flashdemo.data.model.Login.Login;
import com.alaaramadan.flashdemo.data.model.UserLogin.AuthData;
import com.alaaramadan.flashdemo.databinding.FragmentCheckPasswordBinding;
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


public class CheckPasswordFragment extends BaseFragment {
    private FragmentCheckPasswordBinding binding;
    private ApiService apiService;
    private AuthData authData;
    private SharedPreferencesManger sharedPreferencesManger;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater, R.layout.fragment_check_password, container, false);
        apiService = getClient().create( ApiService.class);
        authData=sharedPreferencesManger.loadAuthData( getActivity());
        onClickViews();
        return binding.getRoot();
    }
    private void onClickViews() {
        setOnClick( binding.checkPasswordFragmentBtnConfirm );
    }

    @Override
    public void onClickItem(final View view) {
        disappearKeypad(getActivity(), view);
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.change_pin_code_fragment_btn_confirm:
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
        String udid=sharedPreferencesManger.loadData( getActivity(),"udid_string" );
        String password=binding.checkPasswordFragmentEtPassword.getText().toString();
        if (!password.isEmpty()) {
            if (InternetState.isConnected( getActivity() )) {
                apiService.checkOldPassword( "set","reset_password1",authData.getStatic(),udid,password ).enqueue( new Callback<CheckOldPassword>() {
                    @Override
                    public void onResponse(Call<CheckOldPassword> call, Response<CheckOldPassword> response) {
                        if (response.body().getType()==1){
                            sharedPreferencesManger.saveData( getActivity(),"oldPassword",password );
                            replaceFragment( getFragmentManager(),R.id.home_activity_frame_layout_container,new RestorePasswordFragment() );
                        }else {
                            binding.checkPasswordFragmentTvShowMessage.setText( response.body().getMessage() );
                        }
                    }

                    @Override
                    public void onFailure(Call<CheckOldPassword> call, Throwable t) {
                      binding.checkPasswordFragmentTvShowMessage.setText( R.string.error );
                    }
                } );


            } else {
                dismissProgressDialog();
                onCreateErrorToast( getActivity(), getString( R.string.no_internet_connection ) );
            }
        }else {
            binding.checkPasswordFragmentTvShowMessage.setText( R.string.please_enter_phone_password );
        }
    }
}