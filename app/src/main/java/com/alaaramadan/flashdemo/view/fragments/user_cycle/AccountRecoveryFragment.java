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
import com.alaaramadan.flashdemo.data.api.ApiService;
import com.alaaramadan.flashdemo.data.model.UserRestore.UserRestore;
import com.alaaramadan.flashdemo.databinding.FragmentAccountRecoveryBinding;
import com.alaaramadan.flashdemo.utils.InternetState;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.alaaramadan.flashdemo.data.api.RetrofitClient.getClient;
import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;
import static com.alaaramadan.flashdemo.utils.HelperMethod.dismissProgressDialog;
import static com.alaaramadan.flashdemo.utils.HelperMethod.onCreateErrorToast;
import static com.alaaramadan.flashdemo.utils.HelperMethod.showProgressDialog;


public class AccountRecoveryFragment extends BaseFragment {
    private FragmentAccountRecoveryBinding binding;
    private ApiService apiService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_account_recovery, container, false );
        apiService = getClient().create(ApiService.class);
        checkInput();
        onClickViews();
        return binding.getRoot();
    }



    private void onClickViews() {
        setOnClick( binding.accountRecoveryBtnSend );
    }

    @Override
    public void onClickItem(final View view) {
        disappearKeypad(getActivity(), view);
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.account_recovery_btn_send:
                    sendRequest();
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
        private void checkInput(){
           binding.accountRecoveryEtPhone.addTextChangedListener( new TextWatcher() {
               @Override
               public void beforeTextChanged(CharSequence s, int start, int count, int after) {

               }

               @Override
               public void onTextChanged(CharSequence s, int start, int before, int count) {

               }

               @Override
               public void afterTextChanged(Editable s) {
                   if((s.toString().length()==11)){
                       binding.accountRecoveryBtnSend.setBackgroundResource( R.drawable.bk_log_in );
                   }
               }
           } );
        }

    private void sendRequest() {
        String phone=binding.accountRecoveryEtPhone.getText().toString();
        if (phone!=null)
        {
            if (InternetState.isConnected( getActivity() )){

                apiService.restoreAccount("set","UserRestore",phone).enqueue( new Callback<UserRestore>() {
                    @Override
                    public void onResponse(Call<UserRestore> call, Response<UserRestore> response) {
                        if (response.body().getType()==0){
                            binding.accountRecoveryTvShowMassage.setText( response.body().getMessage() );

                        }else {
                            binding.accountRecoveryTvShowMassage.setText( response.body().getMessage() );
                        }
                    }

                    @Override
                    public void onFailure(Call<UserRestore> call, Throwable t) {
                      binding.accountRecoveryTvShowMassage.setText( t.getMessage() );
                    }
                } );
            }else {
                dismissProgressDialog();
                onCreateErrorToast(getActivity(), getString(R.string.no_internet_connection));
            }
        }


    }
}