package com.example.andmoduleads.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.ads.control.admob.Admob;
import com.ads.control.admob.AppOpenManager;
import com.ads.control.ads.ITGAd;
import com.ads.control.config.ITGAdConfig;
import com.ads.control.funtion.AdCallback;
import com.example.andmoduleads.BuildConfig;
import com.example.andmoduleads.R;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    private List<String> list = new ArrayList<>();
    private String idAdSplash;

    private boolean isBackgroundRunning = false;

    @Override
    protected void onStart() {
        super.onStart();
        isBackgroundRunning = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (ITGAd.getInstance().getMediationProvider() == ITGAdConfig.PROVIDER_ADMOB)
            idAdSplash = BuildConfig.ad_interstitial_splash;
        else
            idAdSplash = getString(R.string.applovin_test_inter);

        AppOpenManager.getInstance().loadAdOpenSplash2id(SplashActivity.class, this,
                "ca-app-pub-3940256099942544/3419835294",
                "ca-app-pub-3940256099942544/3419835294", 25000, new AdCallback() {
                    @Override
                    public void onNextAction() {
                        super.onNextAction();
                        startMain();
                    }
                });

        /*Admob.getInstance().loadSplashInterstitialAds(this, "ca-app-pub-3940256099942544/1033173712", 25000, 5000, new AdCallback() {
            @Override
            public void onNextAction() {
                super.onNextAction();
                startMain();
            }
        });*/

    }

    private void startMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppOpenManager.getInstance().onCheckShowAppOpenSplashWhenFail(this, new AdCallback() {
            @Override
            public void onNextAction() {
                super.onNextAction();
                startMain();
            }
        }, 1000);

        /*Admob.getInstance().onCheckShowSplashWhenFail(this, new AdCallback() {
            @Override
            public void onNextAction() {
                super.onNextAction();
                startMain();
            }
        }, 1000);*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "Splash onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "Splash onStop: ");
        isBackgroundRunning = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}