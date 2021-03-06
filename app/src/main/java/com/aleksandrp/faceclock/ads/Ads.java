package com.aleksandrp.faceclock.ads;

import android.app.Activity;
import android.view.View;

import com.aleksandrp.faceclock.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by Aleksandr on 25.10.2015.
 */
public class Ads {

    public static void showBanner(final Activity activity) {
        final AdView adView = (AdView) activity.findViewById(R.id.banner);
        final AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
//                setupContentViewPadding(activity, adView.getHeight());
            }
        });

    }

    public static void setupContentViewPadding(Activity activity, int paddinfg) {
        View view = activity.findViewById(R.id.coordinatir);
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), paddinfg);

    }

}
