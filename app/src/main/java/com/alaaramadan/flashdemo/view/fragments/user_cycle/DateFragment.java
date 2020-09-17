package com.alaaramadan.flashdemo.view.fragments.user_cycle;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.databinding.FragmentDateBinding;
import com.alaaramadan.flashdemo.view.activities.AdvertisementActivity;
import com.alaaramadan.flashdemo.view.activities.AuthActivity;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import java.util.Calendar;

import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;
import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;


public class DateFragment extends BaseFragment {

    private FragmentDateBinding binding;
    private SharedPreferencesManger sharedPreferencesManger;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_date, container, false );
        onClickViews();

        binding.datePicker.setOnDateChangedListener( new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                binding.dateFragmentTvDate.setText( year+"/"+monthOfYear+"/"+dayOfMonth );
                sharedPreferencesManger.saveData( getActivity(),"Date",binding.dateFragmentTvDate.getText() );

            }
        } );

        return binding.getRoot();
    }


    private void onClickViews() {
        setOnClick( binding.datePicker );
        setOnClick( binding.dateFragmentBtnCancel );
        setOnClick( binding.dateFragmentBtnConfirm );
    }

    @Override
    public void onClickItem(final View view) {
        disappearKeypad(getActivity(), view);
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.date_fragment_btn_cancel:
                    saveCheck();
                    break;
                case R.id.date_fragment_btn_confirm:
                    String date=binding.dateFragmentTvDate.getText().toString();
                    sharedPreferencesManger.saveData( getActivity(),"DateBirthDay", date);
                   saveCheck();
                    break;

            }

            new Handler().postDelayed( new Runnable() {

                @Override
                public void run() {
                    view.setEnabled(true);
                }
            }, 2000);
        }
    }

    public void saveCheck()
    {
        sharedPreferencesManger.saveData( getActivity(),"check","date" );
        Intent intent = new Intent(getActivity(), AuthActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
    public static java.util.Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}