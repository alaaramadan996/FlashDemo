package com.alaaramadan.flashdemo.view.fragments.user_cycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.adapter.CityListAdapter;
import com.alaaramadan.flashdemo.data.api.ApiService;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.data.model.ListCity.DataCity;
import com.alaaramadan.flashdemo.data.model.ListCity.ListCity;
import com.alaaramadan.flashdemo.data.model.ListGovernorate.DataGovernorate;
import com.alaaramadan.flashdemo.databinding.FragmentCityListBinding;
import com.alaaramadan.flashdemo.utils.InternetState;
import com.alaaramadan.flashdemo.view.activities.AuthActivity;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.alaaramadan.flashdemo.data.api.RetrofitClient.getClient;
import static com.alaaramadan.flashdemo.utils.HelperMethod.dismissProgressDialog;
import static com.alaaramadan.flashdemo.utils.HelperMethod.onCreateErrorToast;
import static com.alaaramadan.flashdemo.utils.HelperMethod.showProgressDialog;


public class CityListFragment extends BaseFragment {

    private FragmentCityListBinding binding;

    private LinearLayoutManager layoutManager;
    private List<DataCity> dataCities = new ArrayList<>();
    private CityListAdapter cityListAdapter ;
    private SharedPreferencesManger sharedPreferencesManger;
    private ApiService apiService;
    private DataGovernorate dataGovernorate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_city_list, container, false );
        apiService = getClient().create(ApiService.class);
        dataGovernorate=sharedPreferencesManger.loadGovernorateData( getActivity() );
        setNameCitiesRecycler();
        return binding.getRoot();
    }



    private void setNameCitiesRecycler() {

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.cityListFragmentRvCity.setLayoutManager(layoutManager);

        cityListAdapter = new CityListAdapter(getActivity(), getActivity()
                , dataCities);
        binding.cityListFragmentRvCity.setAdapter(cityListAdapter);

        if (dataCities.size() == 0) {
            getNameCityList();
        }


    }
    private void getNameCityList() {
        if (InternetState.isConnected( getActivity() )){

            apiService.getCityList( "get","SystemCity",dataGovernorate.getId()).enqueue( new Callback<ListCity>() {
                @Override
                public void onResponse(Call<ListCity> call, Response<ListCity> response) {
                    if (response.body().getType()==1){
                        dataCities.addAll( response.body().getData() );
                        cityListAdapter.notifyDataSetChanged();

                    }else {
                        onCreateErrorToast( getActivity(),response.body().getMessage() );
                    }
                }

                @Override
                public void onFailure(Call<ListCity> call, Throwable t) {
                    onCreateErrorToast(getActivity(), getString(R.string.error));
                    dismissProgressDialog();

                }
            } );

        }else {
            dismissProgressDialog();
            onCreateErrorToast(getActivity(), getString(R.string.no_internet_connection));
        }


    }
}