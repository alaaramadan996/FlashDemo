package com.alaaramadan.flashdemo.view.fragments.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.databinding.FragmentThereIsAWinnerBinding;
import com.alaaramadan.flashdemo.view.activities.AuthActivity;
import com.alaaramadan.flashdemo.view.activities.SplashActivity;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;


public class ThereIsAWinnerFragment extends BaseFragment {
private FragmentThereIsAWinnerBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater,  R.layout.fragment_there_is_a_winner, container, false );

        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                replaceFragment(getFragmentManager(),R.id.home_activity_frame_layout_container,new WinnerListFragment());
            }
        }, 1500);
        return binding.getRoot();
    }
}