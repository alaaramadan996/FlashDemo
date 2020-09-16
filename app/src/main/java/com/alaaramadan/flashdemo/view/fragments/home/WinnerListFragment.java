package com.alaaramadan.flashdemo.view.fragments.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.adapter.CityListAdapter;
import com.alaaramadan.flashdemo.adapter.WinnerListAdapter;
import com.alaaramadan.flashdemo.data.api.ApiService;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.data.model.GetWinners.DataWinner;
import com.alaaramadan.flashdemo.data.model.GetWinners.GetWinners;
import com.alaaramadan.flashdemo.data.model.ListCity.ListCity;
import com.alaaramadan.flashdemo.databinding.FragmentWinnerListBinding;
import com.alaaramadan.flashdemo.utils.HelperMethod;
import com.alaaramadan.flashdemo.utils.InternetState;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.alaaramadan.flashdemo.data.api.RetrofitClient.getClient;
import static com.alaaramadan.flashdemo.utils.HelperMethod.dismissProgressDialog;
import static com.alaaramadan.flashdemo.utils.HelperMethod.onCreateErrorToast;
import static com.alaaramadan.flashdemo.utils.HelperMethod.showProgressDialog;


public class WinnerListFragment extends BaseFragment {
    private FragmentWinnerListBinding binding;
    private WinnerListAdapter winnerListAdapter;
    private ApiService apiService;
    private List<DataWinner> dataWinners;
    private LinearLayoutManager layoutManager;
    private SharedPreferencesManger sharedPreferencesManger;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater, R.layout.fragment_winner_list, container, false );
        apiService = getClient().create(ApiService.class);
        setWinnerRecycler();
        return binding.getRoot();
    }

    private void setWinnerRecycler() {

        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.winnerFragmentListRvWinner.setLayoutManager(layoutManager);

        winnerListAdapter = new WinnerListAdapter(getActivity(), getActivity()
                , dataWinners);
        binding.winnerFragmentListRvWinner.setAdapter(winnerListAdapter);

        if (dataWinners.size() == 0) {
            getWinnerList();
        }


    }

    private void getWinnerList() {
       String udId= sharedPreferencesManger.loadData( getActivity(),"udid_string" );
        if (InternetState.isConnected( getActivity() )){

            apiService.getWinners( "get" ,"winners","",udId).enqueue( new Callback<GetWinners>() {
                @Override
                public void onResponse(Call<GetWinners> call, Response<GetWinners> response) {
                    if (response.body().getType()=="success"){
                        dataWinners.addAll( response.body().getData() );
                        winnerListAdapter.notifyDataSetChanged();

                    }else {
                        onCreateErrorToast( getActivity(),response.body().getType() );
                    }
                }

                @Override
                public void onFailure(Call<GetWinners> call, Throwable t) {
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