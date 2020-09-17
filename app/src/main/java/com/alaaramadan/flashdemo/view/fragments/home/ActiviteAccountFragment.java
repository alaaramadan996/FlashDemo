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
import com.alaaramadan.flashdemo.data.model.GetActivationValues.GetActivationValues;
import com.alaaramadan.flashdemo.data.model.UserLogin.AuthData;
import com.alaaramadan.flashdemo.databinding.FragmentActiviteAccountBinding;
import com.alaaramadan.flashdemo.utils.InternetState;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.databinding.adapters.TextViewBindingAdapter.setText;
import static com.alaaramadan.flashdemo.data.api.RetrofitClient.getClient;
import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;
import static com.alaaramadan.flashdemo.utils.HelperMethod.onCreateErrorToast;
import static com.alaaramadan.flashdemo.utils.HelperMethod.onCreateSuccessToast;


public class ActiviteAccountFragment extends BaseFragment {
     private FragmentActiviteAccountBinding binding;
     private ApiService apiService;
     private AuthData authData;
     private SharedPreferencesManger sharedPreferencesManger;
     private String activation_value;
     private String activation_period;
     private String card;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater, R.layout.fragment_activite_account, container, false);
        apiService = getClient().create(ApiService.class);
        authData=sharedPreferencesManger.loadAuthData( getActivity() );
        getValue();
        onClickViews();
        return binding.getRoot();
    }
    private void onClickViews() {
        setOnClick( binding.activiteAccountFragmentBtnConfirm );
    }

    @Override
    public void onClickItem(final View view) {
        disappearKeypad(getActivity(), view);
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.activite_account_fragment_btn_confirm:
                    checkRequest();
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

    private void checkRequest() {

    }

    private void getValue() {
        String udid=sharedPreferencesManger.loadData( getActivity(),"udid_string" );
        if(InternetState.isConnected( getActivity() )){
            apiService.getActivationValues( "get","ActivationValues",authData.getStatic(),udid ).enqueue( new Callback<GetActivationValues>() {
                @Override
                public void onResponse(Call<GetActivationValues> call, Response<GetActivationValues> response) {
                    if (response.body().getType()==1) {
                        binding.activiteAccountFragmentTvRequiredValue.setText( response.body().getData().getActivationValue() );
                        binding.activiteAccountFragmentTvTypePayment.setText( response.body().getData().getCard() );
                        activation_period=response.body().getData().getActivationPeriod();
                        card=response.body().getData().getCard();
                        activation_value=response.body().getData().getActivationValue();
                    }
                    else {
                        onCreateSuccessToast( getActivity(),response.body().getMessage()  );
                    }
                 }

                @Override
                public void onFailure(Call<GetActivationValues> call, Throwable t) {
                    onCreateErrorToast( getActivity(),getString( R.string.error ));
                }
            } );
        }else {
            onCreateErrorToast( getActivity(),getString( R.string.no_internet_connection ));
        }
    }

}