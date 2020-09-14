package com.alaaramadan.flashdemo.view.fragments.user_cycle;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.api.ApiService;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.data.model.ListCity.DataCity;
import com.alaaramadan.flashdemo.data.model.ListGovernorate.DataGovernorate;
import com.alaaramadan.flashdemo.data.model.Registeration.Registeration;
import com.alaaramadan.flashdemo.databinding.FragmentNewAccountStepSixBinding;
import com.alaaramadan.flashdemo.utils.HelperMethod;
import com.alaaramadan.flashdemo.utils.InternetState;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.content.ContextCompat.getSystemService;
import static com.alaaramadan.flashdemo.data.api.RetrofitClient.getClient;
import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;
import static com.alaaramadan.flashdemo.utils.HelperMethod.dismissProgressDialog;
import static com.alaaramadan.flashdemo.utils.HelperMethod.onCreateErrorToast;
import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;
import static com.alaaramadan.flashdemo.utils.HelperMethod.showProgressDialog;


public class NewAccountStepSixFragment extends BaseFragment {
    private FragmentNewAccountStepSixBinding binding;
    private SharedPreferencesManger sharedPreferencesManger;
    private ApiService apiService;
    private DataCity dataCity;
    private DataGovernorate dataGovernorate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater, R.layout.fragment_new_account_step_six, container, false);
        apiService = getClient().create(ApiService.class);
        dataCity=sharedPreferencesManger.loadCityData( getActivity() );
        dataGovernorate=sharedPreferencesManger.loadGovernorateData( getActivity() );
        onClickViews();

        return binding.getRoot();
    }

    private void onClickViews() {
        setOnClick( binding.newAccountStepSixImgCondition );
        setOnClick( binding.newAccountStepSixImgConditionOk );
    }

    @Override
    public void onClickItem(final View view) {
        disappearKeypad(getActivity(), view);
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.new_account_step_six_img_condition:
                   binding.newAccountStepSixImgCondition.setVisibility( View.GONE );
                   binding.newAccountStepSixImgConditionOk.setVisibility( View.VISIBLE );
                   registerNewUser();
                    break;
                case R.id.new_account_step_six_img_condition_ok:
                    binding.newAccountStepSixImgCondition.setVisibility( View.VISIBLE );
                    binding.newAccountStepSixImgConditionOk.setVisibility( View.GONE );
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

    private void registerNewUser() {
        String phone=sharedPreferencesManger.loadData( getActivity(),"phone" );
        String NameUser=sharedPreferencesManger.loadData( getActivity(),"NameUser" );
        String Gender=sharedPreferencesManger.loadData( getActivity(),"Gender" );
        String DateBirthDay=sharedPreferencesManger.loadData( getActivity(),"DateBirthDay" );
        String udid= sharedPreferencesManger.loadData( getActivity(),"udid_string" );
        String City=dataCity.getId();
        String Governorate=dataGovernorate.getId();
        String password=sharedPreferencesManger.loadData( getActivity(),"password" );
        String pinCode=sharedPreferencesManger.loadData( getActivity(),"pinCode" );
        if (InternetState.isConnected( getActivity() )){
            showProgressDialog(getActivity(), getString(R.string.please_wait));
            apiService.Registration( "set" ,"UserRegister",phone,pinCode,pinCode,password,password,NameUser,udid,Gender,DateBirthDay,City,Governorate).enqueue( new Callback<Registeration>() {
                @Override
                public void onResponse(Call<Registeration> call, Response<Registeration> response) {
                    if(response.body().getType()=="success"){
                        sharedPreferencesManger.clean( getActivity() );
                        replaceFragment( getFragmentManager(),R.id.auth_activity_frameLayout_container,new LoginFragment() );
                    }
                    else {
                        onCreateErrorToast(getActivity(), response.body().getData().getTitle());
                        dismissProgressDialog();
                    }
                }

                @Override
                public void onFailure(Call<Registeration> call, Throwable t) {
                    onCreateErrorToast(getActivity(), t.getMessage());
                    sharedPreferencesManger.clean( getActivity() );
                    replaceFragment( getFragmentManager(),R.id.auth_activity_frameLayout_container,new NewAcountStepTwoFragment() );
                }
            } );
        }else {
            dismissProgressDialog();
            onCreateErrorToast(getActivity(), getString(R.string.no_internet_connection));

        }


    }
}