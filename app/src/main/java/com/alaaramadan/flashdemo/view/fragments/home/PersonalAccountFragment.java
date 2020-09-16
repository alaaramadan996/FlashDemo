package com.alaaramadan.flashdemo.view.fragments.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.data.model.UserLogin.AuthData;
import com.alaaramadan.flashdemo.databinding.FragmentPersonalAccountBinding;
import com.alaaramadan.flashdemo.view.base.BaseFragment;


public class PersonalAccountFragment extends BaseFragment {
     private FragmentPersonalAccountBinding binding;
    private SharedPreferencesManger sharedPreferencesManger;
    private AuthData authData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_personal_account, container, false  );
        authData=sharedPreferencesManger.loadAuthData( getActivity() );
        setDataAccount();

        return binding.getRoot();
    }

    public void setDataAccount(){
        binding.personalAccountFragmentTvBirthdayUser.setText( authData.getDateOfBirth() );
        binding.personalAccountFragmentTvCityUser.setText( authData.getCity() );
        binding.personalAccountFragmentTvDateCreate.setText( authData.getCreatedAt() );
        binding.personalAccountFragmentTvGenderUser.setText( authData.getGender() );
        binding.personalAccountFragmentTvNameUser.setText( authData.getName() );
        binding.personalAccountFragmentTvGovernoreteUser.setText( authData.getGov() );
        binding.personalAccountFragmentTvRemainingPeriod.setText( authData.getActivationRemaining() );
        if (authData.getStatus()=="1"){
            binding.personalAccountFragmentTvStateAccount.setText( R.string.activite );
            binding.personalAccountFragmentBtnActivateTheAccount.setBackgroundResource( R.drawable.bk_log_out );
        }
        if (authData.getStatus()=="0"){
            binding.personalAccountFragmentTvStateAccount.setText(R.string.no_activite  );
            binding.personalAccountFragmentBtnActivateTheAccount.setBackgroundResource( R.drawable.bk_log_in );
        }
        binding.personalAccountFragmentTvPhoneUser.setText( authData.getPhone() );
        binding.personalAccountFragmentTvGovernoreteUser.setText( authData.getGov() );
    }
}