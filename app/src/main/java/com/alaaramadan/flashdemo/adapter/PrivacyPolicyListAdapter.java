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
import com.alaaramadan.flashdemo.data.model.ListCity.DataCity;
import com.alaaramadan.flashdemo.data.model.ListPrivacyPolicy.DataPrivacyPolicy;
import com.alaaramadan.flashdemo.utils.HelperMethod;
import com.alaaramadan.flashdemo.view.activities.AuthActivity;
import com.alaaramadan.flashdemo.view.fragments.user_cycle.NewAcountStepThreeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.alaaramadan.flashdemo.data.local.SharedPreferencesManger.CITY_DATA;

public class PrivacyPolicyListAdapter extends RecyclerView.Adapter<PrivacyPolicyListAdapter.ViewHolder> {


    private Context context;
    private Activity activity;
    List<DataPrivacyPolicy> dataPrivacyPolicies = new ArrayList<>();
    public int positions;
    private SharedPreferencesManger sharedPreferencesManger;


    public PrivacyPolicyListAdapter(Context context, Activity activity
            , List<DataPrivacyPolicy> dataPrivacyPolicies ) {
        this.context = context;
        this.activity = activity;
        this.dataPrivacyPolicies = dataPrivacyPolicies;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.item_rc_condition_fragment,
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
        holder.tvTitle.setText( dataPrivacyPolicies.get( position ).getTitle() );
        holder.tvContent.setText( dataPrivacyPolicies.get( position ).getContent() );

    }

    private void setAction(ViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return dataPrivacyPolicies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView( R.id.item_rv_privacy_policy_tv_title )
        TextView tvTitle;
        @BindView( R.id.item_rv_privacy_policy_tv_content )
        TextView tvContent;
        private View view;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            view = itemView;
            ButterKnife.bind( this,view );
        }
    }
}
