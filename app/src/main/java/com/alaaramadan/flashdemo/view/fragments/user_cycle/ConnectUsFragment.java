package com.alaaramadan.flashdemo.view.fragments.user_cycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.api.ApiService;
import com.alaaramadan.flashdemo.data.model.ConnectUs.ConnectUs;
import com.alaaramadan.flashdemo.databinding.FragmentConnectUsBinding;
import com.alaaramadan.flashdemo.utils.InternetState;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.alaaramadan.flashdemo.data.api.RetrofitClient.getClient;
import static com.alaaramadan.flashdemo.utils.HelperMethod.dismissProgressDialog;
import static com.alaaramadan.flashdemo.utils.HelperMethod.onCreateErrorToast;
import static com.alaaramadan.flashdemo.utils.HelperMethod.showProgressDialog;


public class ConnectUsFragment extends BaseFragment {

   private FragmentConnectUsBinding binding;
   private ApiService apiService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_connect_us, container, false );
        apiService = getClient().create( ApiService.class);
        getDataConnectUs();
        return binding.getRoot();
    }

    public void getDataConnectUs(){
        if (InternetState.isConnected( getActivity() )){

            apiService.getConnectUs( "get" ,"ContactUs").enqueue( new Callback<ConnectUs>() {
                @Override
                public void onResponse(Call<ConnectUs> call, Response<ConnectUs> response) {
                    if (response.body().getType()==1){
                        binding.connectFragmentUsTvFb.setText( response.body().getData().getFacebook() );
                        binding.connectFragmentUsTvLocation.setText( response.body().getData().getLocation() );
                        binding.connectFragmentUsTvTelephone.setText( response.body().getData().getPhone() );
                        binding.connectFragmentUsTvWhatsUp.setText( response.body().getData().getWhatsApp());
                    }else {
                        onCreateErrorToast( getActivity(),response.body().getMessage() );
                    }


                }

                @Override
                public void onFailure(Call<ConnectUs> call, Throwable t) {
                    onCreateErrorToast(getActivity(), getString(R.string.error));
                }
            } );

        }else {
            dismissProgressDialog();
            onCreateErrorToast(getActivity(), getString(R.string.no_internet_connection));
        }
    }
}