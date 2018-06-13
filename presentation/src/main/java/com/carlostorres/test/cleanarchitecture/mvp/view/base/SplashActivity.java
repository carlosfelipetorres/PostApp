package com.carlostorres.test.cleanarchitecture.mvp.view.base;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.carlostorres.test.cleanarchitecture.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    private static final int ANIM_DELAY = 500;
    private static final int ANIM_DURATION = 1000;
    private static final int SPLASH_DELAY = 2500;
    @BindView(R.id.iv_logo) ImageView iv_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        init();
    }

    /**
     * Initializes the application
     */
    private void init() {
        iv_logo.setAlpha(0.0f);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animateLogo();
            }
        }, ANIM_DELAY);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                redirectActivity();
            }
        }, SPLASH_DELAY);

    }

    private void redirectActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void animateLogo() {
        ObjectAnimator scaleXAnimation = createObjectAnimator(iv_logo, "scaleX", 0.0F, 1.0F, ANIM_DURATION);
        ObjectAnimator scaleYAnimation = createObjectAnimator(iv_logo, "scaleY", 0.0F, 1.0F, ANIM_DURATION);
        ObjectAnimator alphaAnimation = createObjectAnimator(iv_logo, "alpha", 0.0F, 1.0F, ANIM_DURATION);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleXAnimation).with(scaleYAnimation).with(alphaAnimation);
        animatorSet.start();
    }

    public static ObjectAnimator createObjectAnimator(View view, String property, float init,
                                                      float end, long duration) {
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(view, property, init, end);
        scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleXAnimation.setDuration(duration);
        return scaleXAnimation;
    }

}
