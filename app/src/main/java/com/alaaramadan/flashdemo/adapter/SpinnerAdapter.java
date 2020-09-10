package com.alaaramadan.flashdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.model.generalResponse.GeneralResponseData;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter extends BaseAdapter {

    public List<GeneralResponseData> spinnerData = new ArrayList<>();
    private LayoutInflater inflter;
    public int selectedId;
    public String selectedName;

    public SpinnerAdapter(Context applicationContext) {
        inflter = (LayoutInflater.from(applicationContext));
    }

    public void setData(List<GeneralResponseData> spinnerData, String hint) {
        spinnerData.add(0, new GeneralResponseData(0, hint));
        this.spinnerData = new ArrayList<>();
        this.spinnerData = spinnerData;
    }

    public void setData(List<GeneralResponseData> spinnerData, String hint, boolean addHint) {
        if (addHint) {
            spinnerData.add(0, new GeneralResponseData(0, hint));
        }
        this.spinnerData = new ArrayList<>();
        this.spinnerData = spinnerData;
    }

    @Override
    public int getCount() {
        return spinnerData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate( R.layout.items_custom_spinner, null);

        TextView names = (TextView) view.findViewById(R.id.text);

        names.setText(spinnerData.get(i).getName());
        selectedId = spinnerData.get(i).getId();
        selectedName = spinnerData.get(i).getName();

        return view;
    }
}