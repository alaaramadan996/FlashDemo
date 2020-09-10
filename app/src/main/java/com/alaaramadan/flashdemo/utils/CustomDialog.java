package com.alaaramadan.flashdemo.utils;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alaaramadan.flashdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomDialog extends Dialog {

    @BindView(R.id.dialog_iv_close)
    ImageView dialogIvClose;
    @BindView(R.id.dialog_Tv_dialog_Title)
    TextView dialogTvDialogTitle;
    @BindView(R.id.dialog_Ll_dialog_Ok)
    LinearLayout dialogLlDialogOk;

    private int visible;
    private Activity activity;
    private Context context;
    private String Title;
    private int Icon;
    private View.OnClickListener ok, cancel;
    private boolean Cancelable;

    public CustomDialog(Context context, Activity activity, String title,
                        View.OnClickListener ok, int Visible, boolean Cancelable) {
        super(context);
        this.activity = activity;
        this.context = context;
        Title = title;
        this.ok = ok;
        this.visible = Visible;
        this.Cancelable = Cancelable;
        onCreate();
    }


    public void onCreate() {
        try {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.dialog);
            ButterKnife.bind(this);

            CustomDialog.this.setCancelable(Cancelable);

            dialogLlDialogOk.setVisibility(visible);
            if (visible == View.VISIBLE && ok != null) {
                dialogLlDialogOk.setOnClickListener(ok);
            }

            dialogIvClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });

            dialogTvDialogTitle.setText(Title);

            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            getWindow().setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
            show();

        } catch (Exception e) {

        }

    }


}