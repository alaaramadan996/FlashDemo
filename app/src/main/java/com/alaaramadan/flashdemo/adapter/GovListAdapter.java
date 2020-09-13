package com.alaaramadan.flashdemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.data.model.ListCity.DataCity;
import com.alaaramadan.flashdemo.data.model.ListGovernorate.DataGovernorate;
import com.alaaramadan.flashdemo.utils.HelperMethod;
import com.alaaramadan.flashdemo.view.activities.AuthActivity;
import com.alaaramadan.flashdemo.view.fragments.user_cycle.NewAcountStepThreeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.alaaramadan.flashdemo.data.local.SharedPreferencesManger.CITY_DATA;
import static com.alaaramadan.flashdemo.data.local.SharedPreferencesManger.GOV_DATA;

public class GovListAdapter extends RecyclerView.Adapter<GovListAdapter.ViewHolder> {


    private Context context;
    private Activity activity;
    List<DataGovernorate> dataGovernorates = new ArrayList<>();
    public int positions;
    private SharedPreferencesManger sharedPreferencesManger;


    public GovListAdapter(Context context, Activity activity
            , List<DataGovernorate> dataGovernorates ) {
        this.context = context;
        this.activity = activity;
        this.dataGovernorates = dataGovernorates;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.item_governorate_list,
                parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }


    public void setData(ViewHolder holder,int position){
        positions =position;
        holder.textView.setText( dataGovernorates.get( position ).getNameAr() );

    }

    private void setAction(ViewHolder holder, int position) {

        holder.view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferencesManger.saveData( activity, GOV_DATA,dataGovernorates.get( position ));
                Intent intent = new Intent (v.getContext(), AuthActivity.class);
                context.startActivity( intent );
                AuthActivity authActivity=(AuthActivity) activity;

            }
        } );

    }

    @Override
    public int getItemCount() {
        return dataGovernorates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView( R.id.item_governorate_tv_name_gov )
        TextView textView;
        private View view;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            view = itemView;
            ButterKnife.bind( this,view );
        }
    }
}
