package com.alaaramadan.flashdemo.view.fragments.user_cycle;

import android.os.Bundle;
import android.os.Handler;
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
                case R.id.new_account_step_two_btn_next:
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

    private void sendRequest() {
        String phone=binding.accountRecoveryEtPhone.getText().toString();
        if (phone.isEmpty())
        {
            if (InternetState.isConnected( getActivity() )){
                showProgressDialog(getActivity(), getString(R.string.please_wait));
                apiService.restoreAccount("set","UserRestore",phone).enqueue( new Callback<UserRestore>() {
                    @Override
                    public void onResponse(Call<UserRestore> call, Response<UserRestore> response) {
                        if (response.body().getType()=="error"){
                            binding.accountRecoveryTvShowMassage.setText( response.body().getData().getTitle() );

                        }else {
                            binding.accountRecoveryTvShowMassage.setText( "'طلبك قيد المعالجة" );
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