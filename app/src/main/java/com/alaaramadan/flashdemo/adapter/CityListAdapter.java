package com.alaaramadan.flashdemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.utils.HelperMethod;
import com.alaaramadan.flashdemo.view.activities.AuthActivity;
import com.alaaramadan.flashdemo.view.activities.HomeActivity;
import com.alaaramadan.flashdemo.data.model.ListCity.DataCity;
import com.alaaramadan.flashdemo.view.fragments.user_cycle.NewAcountStepThreeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.alaaramadan.flashdemo.data.local.SharedPreferencesManger.CITY_DATA;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {


    private Context context;
    private Activity activity;
    List<DataCity> dataCities = new ArrayList<>();
    public int positions;
    private SharedPreferencesManger sharedPreferencesManger;


    public CityListAdapter(Context context, Activity activity
            , List<DataCity> dataCities ) {
        this.context = context;
        this.activity = activity;
        this.dataCities = dataCities;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.item_city_list,
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
        holder.textView.setText( dataCities.get( position ).getNameAr() );

    }

    private void setAction(ViewHolder holder, int position) {

        AuthActivity authActivity=(AuthActivity) activity;
        sharedPreferencesManger.saveData( activity, CITY_DATA,dataCities.get( position ));
        HelperMethod.replaceFragment( authActivity.getSupportFragmentManager(),R.id.auth_activity_frameLayout_container,new NewAcountStepThreeFragment() );


    }

    @Override
    public int getItemCount() {
        return dataCities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView( R.id.item_city_list_tv_city )
        TextView textView;
        private View view;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            view = itemView;
            ButterKnife.bind( this,view );
        }
    }
}
