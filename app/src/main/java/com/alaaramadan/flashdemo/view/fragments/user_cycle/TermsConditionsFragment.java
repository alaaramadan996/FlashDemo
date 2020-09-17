package com.alaaramadan.flashdemo.view.fragments.user_cycle;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.adapter.CityListAdapter;
import com.alaaramadan.flashdemo.adapter.PrivacyPolicyListAdapter;
import com.alaaramadan.flashdemo.adapter.UsageAgreementListAdapter;
import com.alaaramadan.flashdemo.data.api.ApiService;
import com.alaaramadan.flashdemo.data.model.ListCity.DataCity;
import com.alaaramadan.flashdemo.data.model.ListCity.ListCity;
import com.alaaramadan.flashdemo.data.model.ListPrivacyPolicy.DataPrivacyPolicy;
import com.alaaramadan.flashdemo.data.model.ListPrivacyPolicy.ListPrivacyPolicy;
import com.alaaramadan.flashdemo.data.model.ListUsageAgreement.DataUsageAgreement;
import com.alaaramadan.flashdemo.data.model.ListUsageAgreement.ListUsageAgreement;
import com.alaaramadan.flashdemo.databinding.FragmentTermsConditionsBinding;
import com.alaaramadan.flashdemo.utils.InternetState;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.alaaramadan.flashdemo.data.api.RetrofitClient.getClient;
import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;
import static com.alaaramadan.flashdemo.utils.HelperMethod.dismissProgressDialog;
import static com.alaaramadan.flashdemo.utils.HelperMethod.onCreateErrorToast;
import static com.alaaramadan.flashdemo.utils.HelperMethod.showProgressDialog;


public class TermsConditionsFragment extends BaseFragment {
    private FragmentTermsConditionsBinding binding;
    private ApiService apiService;
    private PrivacyPolicyListAdapter policyListAdapter;
    private UsageAgreementListAdapter usageAgreementListAdapter;
    private List<DataPrivacyPolicy> dataPrivacyPolicies = new ArrayList<>();
    private List<DataUsageAgreement> dataUsageAgreements = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_terms_conditions, container, false );
        apiService = getClient().create(ApiService.class);
        onClickViews();
        setPrivacyPolicyList();
        setUsageAgreementList();
        return binding.getRoot();
    }


    public void setPrivacyPolicyList(){
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.termsConditionFragmentRvPrivacyPolicy.setLayoutManager(layoutManager);

        policyListAdapter = new PrivacyPolicyListAdapter(getActivity(), getActivity()
                , dataPrivacyPolicies);
        binding.termsConditionFragmentRvPrivacyPolicy.setAdapter(policyListAdapter);

        if (dataPrivacyPolicies.size() == 0) {
            getPrivacyPolicyList();
        }

    }

    private void getPrivacyPolicyList() {
        if (InternetState.isConnected( getActivity() )){

            apiService.getPrivacyPolicyList( "get","PrivacyPolicy" ).enqueue( new Callback<ListPrivacyPolicy>() {
                @Override
                public void onResponse(Call<ListPrivacyPolicy> call, Response<ListPrivacyPolicy> response) {
                    if (response.body().getType()==1){
                        dataPrivacyPolicies.addAll( response.body().getData() );
                        policyListAdapter.notifyDataSetChanged();
                    }else {
                        onCreateErrorToast( getActivity(),response.body().getMessage() );
                    }
                }

                @Override
                public void onFailure(Call<ListPrivacyPolicy> call, Throwable t) {
                    onCreateErrorToast(getActivity(), t.getMessage());
                    dismissProgressDialog();

                }
            } );

        }else {
            dismissProgressDialog();
            onCreateErrorToast(getActivity(), getString(R.string.no_internet_connection));
        }
    }


    public void setUsageAgreementList(){
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.termsConditionFragmentRvUsageAgreement.setLayoutManager(layoutManager);

        usageAgreementListAdapter = new UsageAgreementListAdapter(getActivity(), getActivity()
                , dataUsageAgreements);
        binding.termsConditionFragmentRvUsageAgreement.setAdapter(usageAgreementListAdapter);

        if (dataUsageAgreements.size() == 0) {
            getUsageAgreementList();
        }


    }

    private void getUsageAgreementList() {
        if (InternetState.isConnected( getActivity() )){

            apiService.getUsageAgreementList( "get","UsageAgreement" ).enqueue( new Callback<ListUsageAgreement>() {
                @Override
                public void onResponse(Call<ListUsageAgreement> call, Response<ListUsageAgreement> response) {
                    if (response.body().getType()==1){
                        dataUsageAgreements.addAll( response.body().getData() );
                        usageAgreementListAdapter.notifyDataSetChanged();
                    }else {
                        onCreateErrorToast( getActivity(),response.body().getMessage() );
                    }

                }

                @Override
                public void onFailure(Call<ListUsageAgreement> call, Throwable t) {
                    onCreateErrorToast(getActivity(), t.getMessage());
                    dismissProgressDialog();

                }
            } );
        }else {
            dismissProgressDialog();
            onCreateErrorToast(getActivity(), getString(R.string.no_internet_connection));
        }

    }

    private void onClickViews() {
        setOnClick( binding.termsConditionFragmentBtnPrivacyPolicy );
        setOnClick( binding.termsConditionFragmentBtnUsageAgreement );
    }

    @Override
    public void onClickItem(final View view) {
        disappearKeypad(getActivity(), view);
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.terms_condition_fragment_btn_privacy_policy:
                    binding.termsConditionFragmentRvPrivacyPolicy.setVisibility( View.VISIBLE );
                    binding.termsConditionFragmentRvUsageAgreement.setVisibility( View.GONE );
                    binding.termsConditionFragmentBtnUsageAgreement.setBackgroundResource( R.drawable.bk_log_out );
                    binding.termsConditionFragmentBtnPrivacyPolicy.setBackgroundResource( R.drawable.bk_log_in );
                    break;
                case R.id.terms_condition_fragment_btn_usage_agreement:
                    binding.termsConditionFragmentRvPrivacyPolicy.setVisibility( View.GONE );
                    binding.termsConditionFragmentRvUsageAgreement.setVisibility( View.VISIBLE );
                    binding.termsConditionFragmentBtnPrivacyPolicy.setBackgroundResource( R.drawable.bk_log_out );
                    binding.termsConditionFragmentBtnUsageAgreement.setBackgroundResource( R.drawable.bk_log_in );
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
}