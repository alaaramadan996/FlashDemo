package com.alaaramadan.flashdemo.view.fragments.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.databinding.FragmentYouAreTheWinnerBinding;

import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;


public class YouAreTheWinnerFragment extends Fragment {
    private FragmentYouAreTheWinnerBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_you_are_the_winner, container, false );
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                replaceFragment(getFragmentManager(),R.id.home_activity_frame_layout_container,new WinnerListFragment());
            }
        }, 1500);
        return binding.getRoot();
    }
}