package com.alaaramadan.flashdemo.view.fragments.user_cycle;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.databinding.FragmentNewAccountStepSixBinding;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;


public class NewAccountStepSixFragment extends BaseFragment {
    private FragmentNewAccountStepSixBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater, R.layout.fragment_new_account_step_six, container, false);
        onClickViews();
        return binding.getRoot();
    }

    private void onClickViews() {
        setOnClick( binding.newAccountStepSixImgCondition );
        setOnClick( binding.newAccountStepSixImgConditionOk );
    }

    @Override
    public void onClickItem(final View view) {
        disappearKeypad(getActivity(), view);
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.new_account_step_six_img_condition:
                   binding.newAccountStepSixImgCondition.setVisibility( View.GONE );
                   binding.newAccountStepSixImgConditionOk.setVisibility( View.VISIBLE );
                    break;
                case R.id.new_account_step_six_img_condition_ok:
                    binding.newAccountStepSixImgCondition.setVisibility( View.VISIBLE );
                    binding.newAccountStepSixImgConditionOk.setVisibility( View.GONE );
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