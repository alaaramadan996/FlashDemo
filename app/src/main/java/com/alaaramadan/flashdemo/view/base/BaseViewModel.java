package com.alaaramadan.flashdemo.view.base;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.alaaramadan.flashdemo.data.model.NullResponse;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class BaseViewModel extends ViewModel {

    public MutableLiveData<NullResponse> generalRequestMutableLiveData;
    public MutableLiveData<Boolean> error;
    public String errorText;

    @SuppressLint("CheckResult")
    public void onGeneralRequest(Single<NullResponse> call) {
        generalRequestMutableLiveData = new MutableLiveData<>();
        error = new MutableLiveData<>();

        call.subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<NullResponse>() {
                    @Override
                    public void onSuccess(NullResponse generalRequest) {
                        try {
                            generalRequestMutableLiveData.setValue(generalRequest);
                        } catch (Exception e) {
//                            registerException(e);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        error = new MutableLiveData<>();
                        errorText = null;
                        error.setValue(true);
                    }
                });
    }


    public void reset() {
        generalRequestMutableLiveData = new MutableLiveData<>();
        error = new MutableLiveData<>();
    }
}
