package com.alaaramadan.flashdemo.view.fragments.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.api.ApiService;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.data.model.Flash.Flash;
import com.alaaramadan.flashdemo.data.model.GetWinners.GetWinners;
import com.alaaramadan.flashdemo.data.model.UserLogin.AuthData;
import com.alaaramadan.flashdemo.databinding.FragmentHomeBinding;
import com.alaaramadan.flashdemo.utils.InternetState;
import com.alaaramadan.flashdemo.view.base.BaseFragment;
import com.alaaramadan.flashdemo.view.fragments.user_cycle.AccountRecoveryFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.alaaramadan.flashdemo.data.api.RetrofitClient.getClient;
import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;
import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;
import static com.alaaramadan.flashdemo.utils.HelperMethod.showProgressDialog;


public class HomeFragment extends BaseFragment {
    private FragmentHomeBinding binding;
    private ApiService apiService;
    private AuthData authData;
    private SharedPreferencesManger sharedPreferencesManger;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater, R.layout.fragment_home, container, false );
        onClickViews();
        apiService = getClient().create( ApiService.class);
        authData=sharedPreferencesManger.loadAuthData( getActivity() );
        return binding.getRoot();
        //https://www.youtube.com/watch?v=6SrKOBV_hx8
    }

    private void onClickViews() {
        setOnClick( binding.homeFragmentBtnTryLucky );
    }

    @Override
    public void onClickItem(final View view) {
        disappearKeypad(getActivity(), view);
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.home_fragment_btn_try_lucky:
                    checkFlash();
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

    private void checkFlash() {
        String code=binding.homeFragmentEtSerailNumber.getText().toString();
        if (!code.isEmpty()){
            String udid=sharedPreferencesManger.loadData( getActivity(),"udid_string" );
            if (InternetState.isConnected( getActivity() )){
                apiService.getWinners( "get","TodayWinner",authData.getStatic(),udid ).enqueue( new Callback<GetWinners>() {
                    @Override
                    public void onResponse(Call<GetWinners> call, Response<GetWinners> response) {
                        if(response.isSuccessful())
                        {
                            if (response.body().getType()==1)
                            {
                                replaceFragment( getFragmentManager(),R.id.home_activity_frame_layout_container,new ThereIsAWinnerFragment() );
                            }
                        }
                        else {
                            checkCode(code,udid);
                        }

                    }

                    @Override
                    public void onFailure(Call<GetWinners> call, Throwable t) {
                       binding.homeFragmentTvShowMessage.setText( R.string.error );
                    }
                } );
            }else {
                binding.homeFragmentTvShowMessage.setText( R.string.no_internet_connection );
            }
        }else {
            binding.homeFragmentTvShowMessage.setText( R.string.should_enter_code );
        }

    }

    private void checkCode(String code,String udid) {
        apiService.setFlash( "set" ,"flash",authData.getStatic(),udid,code).enqueue( new Callback<Flash>() {
            @Override
            public void onResponse(Call<Flash> call, Response<Flash> response) {
                if (response.body().getType()==1){
                    replaceFragment( getFragmentManager(),R.id.home_activity_frame_layout_container,new ThereIsAWinnerFragment() );
                }else {
                    binding.homeFragmentTvShowMessage.setText( response.body().getMessage() );
                }
            }

            @Override
            public void onFailure(Call<Flash> call, Throwable t) {
                binding.homeFragmentTvShowMessage.setText( R.string.error );

            }
        } );

    }
}