package com.alaaramadan.flashdemo.view.fragments.user_cycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.databinding.FragmentNewAccountSecurityBinding;
import com.alaaramadan.flashdemo.view.base.BaseFragment;


public class NewAccountSecurityFragment extends BaseFragment {
    private FragmentNewAccountSecurityBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_new_account_security, container, false );

        return binding.getRoot();
    }

}