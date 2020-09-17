package com.alaaramadan.flashdemo.view.fragments.user_cycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
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
        String DateBirthDay=sharedPreferencesManger.loadData( getActivity(),"DateBirthDay" );
        if(!DateBirthDay.isEmpty()){
            binding.newAccountStepThreeEtDate.setText(DateBirthDay );
        }

        String city=sharedPreferencesManger.loadData( getActivity(),"city" );
        if(!city.isEmpty()){
            binding.newAccountStepThreeEtCity.setText(city );
        }
        String gov=sharedPreferencesManger.loadData( getActivity(),"governorate" );
        if(!gov.isEmpty()){
            binding.newAccountStepThreeEtGovernorate.setText(gov );
        }
        String Gender=sharedPreferencesManger.loadData( getActivity(),"Gender" );
        if(!Gender.isEmpty()){
            binding.newAccountStepThreeEtGender.setText(Gender );
        }
        String name=sharedPreferencesManger.loadData( getActivity(),"nameUser" );
        if(!Gender.isEmpty()){
            binding.newAccountStepThreeEtNameUser.setText(name );
        }
    }
    public void saveData(){
        String City =binding.newAccountStepThreeEtCity.getText().toString();
        String NameUser =binding.newAccountStepThreeEtNameUser.getText().toString();
        String Gender = binding.newAccountStepThreeEtGender.getText().toString();
        String Governorate =binding.newAccountStepThreeEtGovernorate.getText().toString();
        String DateBirthday =binding.newAccountStepThreeEtDate.getText().toString();
        if ((!City.isEmpty())&&(!NameUser.isEmpty())&&(!Gender.isEmpty())&&(!Governorate.isEmpty())&&(!DateBirthday.isEmpty()))
        {
        sharedPreferencesManger.saveData( getActivity(),"City",City );
        sharedPreferencesManger.saveData( getActivity(),"NameUser",NameUser );
        sharedPreferencesManger.saveData( getActivity(),"Gender", Gender);
        sharedPreferencesManger.saveData( getActivity(),"Governorate", Governorate);
        sharedPreferencesManger.saveData( getActivity(),"DateBirthDay", DateBirthday);
        replaceFragment( getFragmentManager(),R.id.auth_activity_frameLayout_container,new  NewAcountStepFourFragment() );
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
                    sharedPreferencesManger.saveData( getActivity(),"check", "3");
                    saveCheck();
                    break;
                case R.id.new_account_step_three_tv_gov:
                    sharedPreferencesManger.saveData( getActivity(),"check", "4");
                    saveCheck();
                    break;
                case R.id.new_account_step_three_tv_date:
                    sharedPreferencesManger.saveData( getActivity(),"check", "5");
                    saveCheck();
                    break;
                case R.id.new_account_step_three_tv_gender:
                    sharedPreferencesManger.saveData( getActivity(),"check", "1");
                    saveCheck();
                    break;

            }

            new Handler().postDelayed( new Runnable() {

                @Override
                public void run() {
                    view.setEnabled(true);
                }
            }, 2500);
        }
    }
  public void saveCheck()
  {
      Intent intent = new Intent(getActivity(), AdvertisementActivity.class);
      startActivity(intent);
      getActivity().finish();
  }

  public void checkInput(){
        binding.newAccountStepThreeEtNameUser.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String City =binding.newAccountStepThreeEtCity.getText().toString();
                String Gender = binding.newAccountStepThreeEtGender.getText().toString();
                String Governorate =binding.newAccountStepThreeEtGovernorate.getText().toString();
                String DateBirthday =binding.newAccountStepThreeEtDate.getText().toString();
               sharedPreferencesManger.saveData( getActivity(),"nameUser",s );
               if ((s.toString().length()>=30)&&(!City.isEmpty())&&(!Gender.isEmpty())&&(!Governorate.isEmpty())&&(!DateBirthday.isEmpty())){
                   binding.newAccountStepThreeBtnNext.setBackgroundResource( R.drawable.bk_log_in );
               }
            }
        } );
  }
}