package com.alaaramadan.flashdemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alaaramadan.flashdemo.R;
import com.alaaramadan.flashdemo.data.local.SharedPreferencesManger;
import com.alaaramadan.flashdemo.data.model.GetWinners.DataWinner;
import com.alaaramadan.flashdemo.data.model.ListCity.DataCity;
import com.alaaramadan.flashdemo.view.activities.AuthActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.alaaramadan.flashdemo.data.local.SharedPreferencesManger.CITY_DATA;

public class WinnersListAdapter extends RecyclerView.Adapter<WinnersListAdapter.ViewHolder> {


    private Context context;
    private Activity activity;
    List<DataWinner> dataWinners = new ArrayList<>();
    public int positions;
    private SharedPreferencesManger sharedPreferencesManger;


    public WinnersListAdapter(Context context, Activity activity
            , List<DataWinner> dataWinners ) {
        this.context = context;
        this.activity = activity;
        this.dataWinners = dataWinners;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.item_get_winner_list,
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
        holder.tvDatePrize.setText( dataWinners.get( position ).getTime() );
        holder.tvNameWinner.setText( dataWinners.get( position ).getName() );
        holder.tvPrize.setText( dataWinners.get( position ).getPrizeInfo() );
        holder.tvSerialPrize.setText( dataWinners.get( position ).getCode() );
        holder.tvWinnerPhone.setText( dataWinners.get( position ).getPhone() );
        if (dataWinners.get( position ).getDeliveryStatus().equals( "1" ))
        {
            holder.btnWinnerProof.setBackgroundResource( R.drawable.bk_log_in );
            holder.tvStatePrize.setText( R.string.state_ok );
        }
        if (dataWinners.get( position ).getDeliveryStatus().equals( "0" ))
        {
            holder.tvStatePrize.setText( R.string.state );
        }

    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataWinners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView( R.id.item_winners_tv_date_prize )
        TextView tvDatePrize;
        @BindView( R.id.item_winners_tv_prize )
        TextView tvPrize;
        @BindView( R.id.item_winners_tv_serial_prize )
        TextView tvSerialPrize;
        @BindView( R.id.item_winners_tv_state_prize )
        TextView tvStatePrize;
        @BindView( R.id.item_winners_tv_winner_name )
        TextView tvNameWinner;
        @BindView( R.id.item_winners_tv_winner_phone )
        TextView tvWinnerPhone;
        @BindView( R.id.item_winners_btn_proof )
        Button btnWinnerProof;
        private View view;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            view = itemView;
            ButterKnife.bind( this,view );
        }
    }
}
