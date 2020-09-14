package com.alaaramadan.flashdemo.view.fragments.adsvertistement;

import android.content.Context;
import android.content.Intent;
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
import com.alaaramadan.flashdemo.data.model.InternalAds.InternalAds;
import com.alaaramadan.flashdemo.data.model.UserLogin.AuthData;
import com.alaaramadan.flashdemo.databinding.FragmentAdvertisementBinding;
import com.alaaramadan.flashdemo.utils.InternetState;
import com.alaaramadan.flashdemo.view.activities.HomeActivity;
import com.alaaramadan.flashdemo.view.fragments.home.WinnerListFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.alaaramadan.flashdemo.data.api.RetrofitClient.getClient;
import static com.alaaramadan.flashdemo.utils.HelperMethod.onLoadImageFromUrl;
import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;
import static com.alaaramadan.flashdemo.utils.HelperMethod.showProgressDialog;


public class AdvertisementFragment extends Fragment {
    private FragmentAdvertisementBinding binding;
    private ApiService apiService;
    private SharedPreferencesManger sharedPreferencesManger;
    private AuthData authData;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_advertisement, container, false );
        authData=sharedPreferencesManger.loadAuthData( getActivity() );

        apiService = getClient().create(ApiService.class);
        return binding.getRoot();
    }

    public void getInternalAds(){
        String udid=sharedPreferencesManger.loadData( getActivity(),"udid_string" );
        if (InternetState.isConnected( getActivity() )) {
            showProgressDialog( getActivity(), getString( R.string.please_wait ) );
            apiService.getInternalAds( "get","InternalAds","1",udid,authData.getStatic() ).enqueue( new Callback<InternalAds>() {
                @Override
                public void onResponse(Call<InternalAds> call, Response<InternalAds> response) {
                    if (response.body().getType()=="success"){
                        onLoadImageFromUrl( binding.advertisementFragmentImageView,response.body().getData().getPlacehold(),context );
                        int duration =Integer.parseInt( response.body().getData().getDuration() )*1000;
                        new Handler().postDelayed( new Runnable() {
                            @Override
                            public void run() {
                                intentHome();
                            }
                        }, duration);
                    }else {
                            intentHome();
                    }
                }

                @Override
                public void onFailure(Call<InternalAds> call, Throwable t) {
                            intentHome();
                }
            } );

        }else {
            intentHome();
        }

    }
    public void intentHome(){
        Intent intent=new Intent( getActivity(), HomeActivity.class );
        startActivity( intent );
        getActivity().finish();
    }
}