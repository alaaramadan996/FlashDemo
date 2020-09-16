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
import com.alaaramadan.flashdemo.view.base.BaseFragment;
import com.alaaramadan.flashdemo.view.fragments.home.WinnerListFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.alaaramadan.flashdemo.data.api.RetrofitClient.getClient;
import static com.alaaramadan.flashdemo.utils.HelperMethod.onCreateErrorToast;
import static com.alaaramadan.flashdemo.utils.HelperMethod.onLoadImageFromUrl;
import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;
import static com.alaaramadan.flashdemo.utils.HelperMethod.showProgressDialog;


public class AdvertisementFragment extends BaseFragment {
    private FragmentAdvertisementBinding binding;
    private ApiService apiService;
    private SharedPreferencesManger sharedPreferencesManger;
    private AuthData authData;
    private Context context;
    private int duration;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_advertisement, container, false );
        authData=sharedPreferencesManger.loadAuthData( getActivity() );
        apiService = getClient().create(ApiService.class);
        getInternalAds();

        return binding.getRoot();
    }

    public void getInternalAds(){
        String udid=sharedPreferencesManger.loadData( getActivity(),"udid_string" );
        if (InternetState.isConnected( getActivity() )) {
            apiService.getInternalAds( "get","InternalAds","1",udid,authData.getStatic() ).enqueue( new Callback<InternalAds>() {
                @Override
                public void onResponse(Call<InternalAds> call, Response<InternalAds> response) {
                    if (response.body().getType()==1){
                        onLoadImageFromUrl( binding.advertisementFragmentImageView,"http://flashfolk.com/ARK_flash/" + response.body().getData().getPlacehold(),getActivity() );
                        duration =Integer.parseInt( response.body().getData().getDuration() )*1000;

                        new Handler().postDelayed( new Runnable() {
                            @Override
                            public void run() {
                                Intent intent=new Intent( getActivity(), HomeActivity.class );
                                startActivity( intent );
                                getActivity().finish();
                            }
                        }, duration);
                    }else {
                        onCreateErrorToast( getActivity(), getString( R.string.error ) );
                    }
                }

                @Override
                public void onFailure(Call<InternalAds> call, Throwable t) {
                    onCreateErrorToast( getActivity(), getString( R.string.error ) );
                }
            } );

        }else {
            onCreateErrorToast( getActivity(), getString( R.string.error ) );
        }

    }
    public void intentHome(){
        Intent intent=new Intent( getActivity(), HomeActivity.class );
        startActivity( intent );
        getActivity().finish();
    }
}