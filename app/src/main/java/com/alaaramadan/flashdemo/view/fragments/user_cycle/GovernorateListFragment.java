package com.alaaramadan.flashdemo.view.fragments.user_cycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.adapter.CityListAdapter;
import com.alaaramadan.flashdemo.adapter.GovListAdapter;
import com.alaaramadan.flashdemo.data.api.ApiService;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.data.model.ListCity.DataCity;
import com.alaaramadan.flashdemo.data.model.ListCity.ListCity;
import com.alaaramadan.flashdemo.data.model.ListGovernorate.DataGovernorate;
import com.alaaramadan.flashdemo.data.model.ListGovernorate.ListGovernorate;
import com.alaaramadan.flashdemo.databinding.FragmentCityListBinding;
import com.alaaramadan.flashdemo.databinding.FragmentGovernorateListBinding;
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

public class GovernorateListFragment extends BaseFragment {

    private FragmentGovernorateListBinding binding;

    private LinearLayoutManager layoutManager;
    private List<DataGovernorate> dataGovernorates = new ArrayList<>();
    private GovListAdapter govListAdapter ;
    private SharedPreferencesManger sharedPreferencesManger;
    private ApiService apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater, R.layout.fragment_governorate_list, container, false);
        String check="governorate";
        sharedPreferencesManger.saveData( getActivity(),"check", check);
        apiService = getClient().create(ApiService.class);
        setNameGovernorateRecycler();

        return binding.getRoot();
    }


    private void setNameGovernorateRecycler() {

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.fragmentGovernorateRvName.setLayoutManager(layoutManager);

        govListAdapter = new GovListAdapter(getActivity(), getActivity()
                , dataGovernorates);
        binding.fragmentGovernorateRvName.setAdapter(govListAdapter);

        if (dataGovernorates.size() == 0) {
            getNameGovernorateList();
        }


    }
    private void getNameGovernorateList() {
        if (InternetState.isConnected( getActivity() )){
            apiService.getGovernorateList( "get","SystemGovs" ).enqueue( new Callback<ListGovernorate>() {
                @Override
                public void onResponse(Call<ListGovernorate> call, Response<ListGovernorate> response) {
                    if (response.body().getType()==1){
                        dataGovernorates.addAll( response.body().getData() );
                        govListAdapter.notifyDataSetChanged();

                    }
                }

                @Override
                public void onFailure(Call<ListGovernorate> call, Throwable t) {
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