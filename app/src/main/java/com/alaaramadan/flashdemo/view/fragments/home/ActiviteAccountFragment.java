package com.alaaramadan.flashdemo.view.fragments.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.databinding.FragmentActiviteAccountBinding;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;


public class ActiviteAccountFragment extends BaseFragment {
     private FragmentActiviteAccountBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater, R.layout.fragment_activite_account, container, false);
        onClickViews();
        onBack();
        return binding.getRoot();
    }
    private void onClickViews() {
        setOnClick( binding.activiteAccountFragmentBtnConfirm );
    }

    @Override
    public void onClickItem(final View view) {
        disappearKeypad(getActivity(), view);
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.activite_account_fragment_btn_confirm:
                    checkPassword();
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

    private void checkPassword() { }

}