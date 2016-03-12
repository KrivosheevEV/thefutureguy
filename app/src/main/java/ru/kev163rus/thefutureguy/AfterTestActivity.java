package ru.kev163rus.thefutureguy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.appodeal.ads.NonSkippableVideoCallbacks;

import ru.kev163rus.thefutureguy.R;

public class AfterTestActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_after_test);

        TextView textViewDialogAfterTestText = (TextView) findViewById(R.id.textViewDialogAfterTestText);
        textViewDialogAfterTestText.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewDialogAfterTestText.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        TextView textViewDialogAfterTestYes = (TextView) findViewById(R.id.textViewDialogAfterTestYes);
        textViewDialogAfterTestYes.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewDialogAfterTestYes.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        TextView textViewDialogAfterTestNo = (TextView) findViewById(R.id.textViewDialogAfterTestNo);
        textViewDialogAfterTestNo.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewDialogAfterTestNo.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewDialogAfterTestYes.setOnClickListener(this);
        textViewDialogAfterTestNo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.textViewDialogAfterTestYes:
//                if (Appodeal.isLoaded(Appodeal.NON_SKIPPABLE_VIDEO)) {
//                    finish();
//                    Appodeal.show(AfterTestActivity.this, Appodeal.NON_SKIPPABLE_VIDEO);
//                }else if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)) {
//                    finish();
//                    Appodeal.show(AfterTestActivity.this, Appodeal.INTERSTITIAL);
//                }else{
                    finish();
                    startActivity(new Intent(AfterTestActivity.this, FinishActivity.class));
//                }
                break;
            case R.id.textViewDialogAfterTestNo:
                startActivity(new Intent(this,  MenuActivity.class));
                finish();
                break;
        }

        /*Appodeal.setNonSkippableVideoCallbacks(new NonSkippableVideoCallbacks() {
            private Toast mToast;

            @Override
            public void onNonSkippableVideoLoaded() {
//                showToast("onInterstitialLoaded");
            }

            @Override
            public void onNonSkippableVideoFailedToLoad() {
//                showToast("onInterstitialFailedToLoad");
                startActivity(new Intent(AfterTestActivity.this, FinishActivity.class));
            }

            @Override
            public void onNonSkippableVideoShown() {
//                showToast("onInterstitialShown");
            }

            @Override
            public void onNonSkippableVideoFinished() {
//                showToast("onInterstitialClicked");
                //startActivity(new Intent(AfterTestActivity.this, FinishActivity.class));
            }

            @Override
            public void onNonSkippableVideoClosed() {
//                showToast("onInterstitialClosed");
                startActivity(new Intent(AfterTestActivity.this, FinishActivity.class));
            }

            void showToast(final String text) {
                if (mToast == null) {
                    mToast = Toast.makeText(AfterTestActivity.this, text, Toast.LENGTH_SHORT);
                }
                mToast.setText(text);
                mToast.setDuration(Toast.LENGTH_SHORT);
                mToast.show();
            }
        });

        Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
            private Toast mToast;

            @Override
            public void onInterstitialLoaded(boolean isPrecache)  {
//                showToast("onInterstitialLoaded");
            }

            @Override
            public void onInterstitialFailedToLoad() {
//                showToast("onInterstitialFailedToLoad");
                startActivity(new Intent(AfterTestActivity.this, FinishActivity.class));
            }

            @Override
            public void onInterstitialShown() {
//                showToast("onInterstitialShown");
            }

            @Override
            public void onInterstitialClicked() {
//                showToast("onInterstitialClicked");
                //startActivity(new Intent(AfterTestActivity.this, FinishActivity.class));
            }

            @Override
            public void onInterstitialClosed() {
//                showToast("onInterstitialClosed");
                startActivity(new Intent(AfterTestActivity.this, FinishActivity.class));
            }

            void showToast(final String text) {
                if (mToast == null) {
                    mToast = Toast.makeText(AfterTestActivity.this, text, Toast.LENGTH_SHORT);
                }
                mToast.setText(text);
                mToast.setDuration(Toast.LENGTH_SHORT);
                mToast.show();
            }
        });*/

    }
}