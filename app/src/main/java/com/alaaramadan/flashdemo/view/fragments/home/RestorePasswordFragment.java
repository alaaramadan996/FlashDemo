package com.alaaramadan.flashdemo.view.fragments.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.api.ApiService;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.data.model.CheckOldPassword.CheckOldPassword;
import com.alaaramadan.flashdemo.data.model.MatchPassword.MatchPassword;
import com.alaaramadan.flashdemo.data.model.UserLogin.AuthData;
import com.alaaramadan.flashdemo.databinding.FragmentRestorePasswordBinding;
import com.alaaramadan.flashdemo.utils.InternetState;
import com.alaaramadan.flashdemo.view.base.BaseFragment;
import com.alaaramadan.flashdemo.view.fragments.user_cycle.NewAcountStepFiveFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;
import static com.alaaramadan.flashdemo.utils.HelperMethod.dismissProgressDialog;
import static com.alaaramadan.flashdemo.utils.HelperMethod.onCreateErrorToast;
import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;


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
        authData=sharedPreferencesManger.loadAuthData( getActivity() );
        checkInput();
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

    public void checkInput(){
        binding.restorePasswordFragmentEtNewPassword.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String confirm_password=binding.restorePasswordFragmentEtNewPasswordConfirm.getText().toString();
                if((s.toString().length()>=8)&&(confirm_password.length()>=8)){
                    binding.restorePasswordFragmentBtnConfirm.setBackgroundResource( R.drawable.bk_log_in );

                }else {
                    binding.restorePasswordFragmentTvShowMessage.setText( R.string.check_password_Password_must_be_8_letters_and_numbers );
                }
            }
        } );
        binding.restorePasswordFragmentEtNewPasswordConfirm.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String password=binding.restorePasswordFragmentEtNewPassword.getText().toString();
                if((s.toString().length()>=8)&&(password.length()>=8)){
                    binding.restorePasswordFragmentBtnConfirm.setBackgroundResource( R.drawable.bk_log_in );

                }else {
                    binding.restorePasswordFragmentTvShowMessage.setText( R.string.check_password_Password_must_be_8_letters_and_numbers );
                }
            }
        } );
    }

    private void checkPassword() {
        String password =binding.restorePasswordFragmentEtNewPassword.getText().toString();
        String confirm =binding.restorePasswordFragmentEtNewPasswordConfirm.getText().toString();
        if (password.equals(confirm))
        {
            if (!password.matches( "[0-9]+" )){
                if (!password.contains("[a-zA-Z]+")&&(password.length()>=8))
                {
                  checkNewPassword();
                }
                else
                {
                    binding.restorePasswordFragmentTvShowMessage.setText( R.string.check_password_Password_must_be_8_letters_and_numbers );
                }
            }
            else {
                binding.restorePasswordFragmentTvShowMessage.setText( R.string.check_password_Password_must_be_8_letters_and_numbers );
            }


        }else {
            binding.restorePasswordFragmentTvShowMessage.setText( R.string.check_password_They_should_be_the_same );
        }
    }

    private void checkNewPassword() {
        String udid=sharedPreferencesManger.loadData( getActivity(),"udid_string" );
        String oldPassword=sharedPreferencesManger.loadData( getActivity(),"oldPassword" );
        String password=binding.restorePasswordFragmentEtNewPassword.getText().toString();
        String passwordConfirm=binding.restorePasswordFragmentEtNewPasswordConfirm.getText().toString();
            if (InternetState.isConnected( getActivity() )) {
                apiService.matchPassword( "set","reset_password2",authData.getStatic(),udid,password,passwordConfirm,oldPassword ).enqueue( new Callback<MatchPassword>() {
                    @Override
                    public void onResponse(Call<MatchPassword> call, Response<MatchPassword> response) {
                        if (response.body().getType()==1){
                            sharedPreferencesManger.saveData( getActivity(),"password",password );
                            replaceFragment( getFragmentManager(),R.id.home_activity_frame_layout_container,new ChangePinCodeFragment() );
                        }else {
                            binding.restorePasswordFragmentTvShowMessage.setText( response.body().getMessage() );
                        }
                    }

                    @Override
                    public void onFailure(Call<MatchPassword> call, Throwable t) {
                       binding.restorePasswordFragmentTvShowMessage.setText( R.string.error );
                    }
                } );

            } else {
                dismissProgressDialog();
                onCreateErrorToast( getActivity(), getString( R.string.no_internet_connection ) );
            }
        }
}

