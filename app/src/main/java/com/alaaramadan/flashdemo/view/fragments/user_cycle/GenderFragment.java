package com.alaaramadan.flashdemo.view.fragments.user_cycle;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.databinding.FragmentGenderBinding;
import com.alaaramadan.flashdemo.view.activities.AuthActivity;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import static androidx.databinding.adapters.ViewBindingAdapter.setOnClick;
import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;
import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;


public class GenderFragment extends BaseFragment {
    private FragmentGenderBinding binding;
    private SharedPreferencesManger sharedPreferencesManger;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater, R.layout.fragment_gender, container, false  );

        onClickViews();
        return binding.getRoot();
    }

    private void onClickViews() {
        setOnClick( binding.genderFragmentBtnMale );
        setOnClick( binding.genderFragmentBtnFemale );
    }



    @Override
    public void onClickItem(final View view) {
        disappearKeypad(getActivity(), view);
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.gender_fragment_btn_female:
                    sharedPreferencesManger.saveData( getActivity(),"Gender","انثي" );
                    saveCheck();
                    break;
                case R.id.gender_fragment_btn_male:
                    sharedPreferencesManger.saveData( getActivity(),"Gender","ذكر" );
                    saveCheck();
                    break;

            }

            new Handler().postDelayed( new Runnable() {

                @Override
                public void run() {
                    view.setEnabled(true);
                }
            }, 500);
        }
    }

    public void saveCheck()
    {
        sharedPreferencesManger.saveData( getActivity(),"check","gender" );
        Intent intent = new Intent(getActivity(), AuthActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}