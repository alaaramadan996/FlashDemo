package com.alaaramadan.flashdemo.view.fragments.user_cycle;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.api.ApiService;
import com.alaaramadan.flashdemo.data.model.ConnectUs.ConnectUs;
import com.alaaramadan.flashdemo.data.model.ListGovernorate.ListGovernorate;
import com.alaaramadan.flashdemo.databinding.FragmentNewAcountStepTwoBinding;
import com.alaaramadan.flashdemo.utils.InternetState;
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


public class NewAcountStepTwoFragment extends BaseFragment {
    private FragmentNewAcountStepTwoBinding binding;
    private ApiService apiService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_new_acount_step_two, container, false );
        apiService = getClient().create(ApiService.class);
        onClickViews();
        return binding.getRoot();
    }

    private void onClickViews() {
        setOnClick( binding.newAccountStepTwoBtnNext );
    }

    @Override
    public void onClickItem(final View view) {
        disappearKeypad(getActivity(), view);
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.new_account_step_two_btn_next:
                    checkPhone();
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

    public void checkPhone(){
        String phone=binding.newAccountStepTwoEtPhone.getText().toString();
        if (phone==null){
            binding.newAccountStepTwoTvShowMessage.setText("برجاء ادخال الرقم" );
        }else {
        if (InternetState.isConnected( getActivity() )){
            showProgressDialog(getActivity(), getString(R.string.please_wait));
            apiService.CheckPhone("check","UserPhone",""  ).enqueue( new Callback<ListGovernorate>() {
                @Override
                public void onResponse(Call<ListGovernorate> call, Response<ListGovernorate> response) {
                    if (response.body().getType()=="success"){
                       replaceFragment( getFragmentManager(),R.id.auth_activity_frameLayout_container,new NewAcountStepThreeFragment() );
                    }
                    else {
                        binding.newAccountStepTwoTvShowMessage.setText( response.body().getType() );
                    }
                }

                @Override
                public void onFailure(Call<ListGovernorate> call, Throwable t) {
                    binding.newAccountStepTwoTvShowMessage.setText( t.getMessage() );

                }
            } );

        }else {
            dismissProgressDialog();
            onCreateErrorToast(getActivity(), getString(R.string.no_internet_connection));
        }
        }
    }
}