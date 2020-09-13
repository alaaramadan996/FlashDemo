package com.alaaramadan.flashdemo.view.fragments.user_cycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.api.ApiService;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.data.model.ListCity.DataCity;
import com.alaaramadan.flashdemo.data.model.ListGovernorate.DataGovernorate;
import com.alaaramadan.flashdemo.databinding.FragmentNewAcountStepThreeBinding;
import com.alaaramadan.flashdemo.view.activities.AdvertisementActivity;
import com.alaaramadan.flashdemo.view.activities.AuthActivity;
import com.alaaramadan.flashdemo.view.base.BaseFragment;

import static com.alaaramadan.flashdemo.data.api.RetrofitClient.getClient;
import static com.alaaramadan.flashdemo.utils.HelperMethod.disappearKeypad;
import static com.alaaramadan.flashdemo.utils.HelperMethod.replaceFragment;


public class NewAcountStepThreeFragment extends BaseFragment {

    private FragmentNewAcountStepThreeBinding binding;
    private SharedPreferencesManger sharedPreferencesManger;
    private ApiService apiService;
    private DataCity dataCity;
    private DataGovernorate dataGovernorate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate( inflater,R.layout.fragment_new_acount_step_three, container, false );
        apiService = getClient().create(ApiService.class);
        dataCity=sharedPreferencesManger.loadCityData( getActivity() );
        dataGovernorate=sharedPreferencesManger.loadGovernorateData( getActivity() );
        loadData();
        onClickViews();

        return binding.getRoot();
    }

    private void onClickViews() {
        setOnClick( binding.newAccountStepThreeBtnNext );
        setOnClick( binding.newAccountStepThreeTvCity );
        setOnClick( binding.newAccountStepThreeTvDate );
        setOnClick( binding.newAccountStepThreeTvGov );
        setOnClick( binding.newAccountStepThreeTvGender );

    }
    public void loadData(){
        binding.newAccountStepThreeEtDate.setText( sharedPreferencesManger.loadData( getActivity(),"DateBirthDay" ) );
        binding.newAccountStepThreeEtCity.setText( dataCity.getNameAr() );
        binding.newAccountStepThreeEtGovernorate.setText( dataGovernorate.getNameAr() );
        binding.newAccountStepThreeEtGender.setText( sharedPreferencesManger.loadData( getActivity(),"Gender" ) );
    }
    public void saveData(){
        String City =binding.newAccountStepThreeEtCity.getText().toString();
        String NameUser =binding.newAccountStepThreeEtNameUser.getText().toString();
        String Gender = binding.newAccountStepThreeEtGender.getText().toString();
        String Governorate =binding.newAccountStepThreeEtGovernorate.getText().toString();
        String DateBirthday =binding.newAccountStepThreeEtDate.getText().toString();
        if ((City!=null)&&(NameUser!=null)&&(Gender!=null)&&(Governorate!=null)&&(DateBirthday!=null))
        {
        sharedPreferencesManger.saveData( getActivity(),"City",City );
        sharedPreferencesManger.saveData( getActivity(),"NameUser",NameUser );
        sharedPreferencesManger.saveData( getActivity(),"Gender", Gender);
        sharedPreferencesManger.saveData( getActivity(),"Governorate", Governorate);
        sharedPreferencesManger.saveData( getActivity(),"DateBirthDay", DateBirthday);
        replaceFragment( getFragmentManager(),R.id.auth_activity_frameLayout_contain_city,new  NewAcountStepFourFragment() );
        }else {
            binding.newAccountStepThreeTvShowMessage.setText( R.string.error );
        }

    }


    @Override
    public void onClickItem(final View view) {
        disappearKeypad(getActivity(), view);
        synchronized (view) {
            view.setEnabled(false);
            switch (view.getId()) {
                case R.id.new_account_step_three_btn_next:
                     saveData();
                    break;
                case R.id.new_account_step_three_tv_city:
                    String check="city";
                    sharedPreferencesManger.saveData( getActivity(),"check", check);
                    saveCheck();
                    break;
                case R.id.new_account_step_three_tv_gov:
                    String check3="governorate";
                    sharedPreferencesManger.saveData( getActivity(),"check", check3);
                    saveCheck();
                    break;
                case R.id.new_account_step_three_tv_date:
                    String check2="date";
                    sharedPreferencesManger.saveData( getActivity(),"check", check2);
                    saveCheck();
                    break;
                case R.id.new_account_step_three_tv_gender:
                    String check4="gender";
                    sharedPreferencesManger.saveData( getActivity(),"check", check4);
                    saveCheck();
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
  public void saveCheck()
  {
      Intent intent = new Intent(getActivity(), AdvertisementActivity.class);
      startActivity(intent);
      getActivity().finish();
  }
}