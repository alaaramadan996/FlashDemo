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
import com.alaaramadan.flashdemo.data.model.ListPrivacyPolicy.DataPrivacyPolicy;
import com.alaaramadan.flashdemo.data.model.ListUsageAgreement.DataUsageAgreement;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsageAgreementListAdapter extends RecyclerView.Adapter<UsageAgreementListAdapter.ViewHolder> {


    private Context context;
    private Activity activity;
    List<DataUsageAgreement> dataUsageAgreements = new ArrayList<>();
    public int positions;
    private SharedPreferencesManger sharedPreferencesManger;


    public UsageAgreementListAdapter(Context context, Activity activity
            , List<DataUsageAgreement> dataUsageAgreements ) {
        this.context = context;
        this.activity = activity;
        this.dataUsageAgreements = dataUsageAgreements;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.item_recycleview_condition,
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
        holder.tvTitle.setText( dataUsageAgreements.get( position ).getTitle() );
        holder.tvContent.setText( dataUsageAgreements.get( position ).getContent() );

    }

    private void setAction(ViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return dataUsageAgreements.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView( R.id.item_rv_usage_agreement_tv_title )
        TextView tvTitle;
        @BindView( R.id.item_rv_usage_agreement_tv_content )
        TextView tvContent;
        private View view;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            view = itemView;
            ButterKnife.bind( this,view );
        }
    }
}
