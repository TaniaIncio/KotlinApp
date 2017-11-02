package com.tincio.pharmaapp.presentation.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tincio.pharmaapp.R;
import com.tincio.pharmaapp.presentation.view.MvpView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.Timer;
import java.util.TimerTask;


public abstract class BaseActivity extends AppCompatActivity {//Dialog.OnClickListener,

    /***Add loading**/
    AVLoadingIndicatorView loadingWithText;
    LinearLayout linearLoadinWithText;
    TextView loadingText;
    Boolean onBack = true;
    TextView lblTitleToolbar;
    ImageView iconBack;
    Toolbar toolbar;
    String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.zpub_activity_main);*/
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(getLayoutId());
        //setupWindowAnimations();
        setupToolbar();
        bindViews();
    }

    protected void bindViews(){
        /*ButterKnife.bind(this);*/
        loadingWithText  = (AVLoadingIndicatorView)findViewById(R.id.loading_withtext);
        linearLoadinWithText  = (LinearLayout)findViewById(R.id.loading_layout_withtext);
        loadingText  = (TextView)findViewById(R.id.txt_loading);
        //lblTitleToolbar  = (TextView)findViewById(R.id.lbl_title_fragment);
    }

    public Toolbar getToolbar(){return toolbar;}
    protected void setupToolbar(){
        if(toolbar!= null){
            setSupportActionBar(toolbar);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);

        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setReturnTransition(slide);
    }

    protected abstract int getLayoutId();
  //  protected abstract int getContainerFragment();

    public void showLoaderGeneral(String message) {
        if(loadingText==null)return;
        loadingText.setText(message);
    //    loadingWithText.show();
        linearLoadinWithText.setVisibility(View.GONE);
        disableViews();
        onBack = false;
    }

    public void hideLoaderGeneral() {
        if(linearLoadinWithText==null)return;
        linearLoadinWithText.setVisibility(View.GONE);
        enableViews();
        onBack = true;

    }

    public void onShowDialogGeneral(String title, String message) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Light_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(getResources().getString(R.string.app_name))
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
               /* .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })*/
              //  .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public Boolean getOnBack(){
        return onBack;
    }

    /***disabled touch in all view**/
    public void disableViews() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    /***disabled on back**/
    public void enableViews() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    /*@Override
    public void onChangeTitleActionBar(String title) {
        if(lblTitleToolbar == null)return;
        lblTitleToolbar.setText(title);
    }*/
/*
    @OnClick(R.id.icon_back)
    public void onBack() {
        AppLogger.info(TAG, "click on back");
        ActivityUtils.hideKeyBoard(iconBack,this);
        onBackPressed();
    }
// */
    /*@Override
    public void onFragmentInteractionLoader(Boolean status, String message) {
        if (status)
            showLoader(message);
        else
            hideLoader();
    }*/



    /*Change Fragments */
    public void changeFragmentActivity(Fragment fragment, String TAG, Boolean isBackStack) {
        /*FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        *//**Existen dos fragments**//*
        if(!isBackStack)
            getSupportFragmentManager().popBackStack(null, getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
        fragmentTransaction.replace(getContainerFragment(), fragment, TAG);
        if(isBackStack)
            fragmentTransaction.addToBackStack(TAG);
        fragmentTransaction.setCustomAnimations(R.anim.enter_animation,
                R.anim.start_animation_fragment, R.anim.start_animation_fragment, R.anim.exit_animation);/*//*//*
        delayTransition(fragmentTransaction);*/
      //  fragmentTransaction.commit();
    }

    void delayTransition(final FragmentTransaction transition){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                transition.commit();
                }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 1000);
    }

}
