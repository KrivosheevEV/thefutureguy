package ru.kev163rus.thefutureguy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.appodeal.ads.NonSkippableVideoCallbacks;

public class AfterVotingActivity extends Activity implements View.OnClickListener {

    SoundPool mySounds;
    int soundFinishID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_after_voting);

        TextView textViewDialogAfterVotingText = (TextView) findViewById(R.id.textViewDialogAfterVotingText);
        textViewDialogAfterVotingText.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewDialogAfterVotingText.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        TextView textViewDialogAfterVotingYes = (TextView) findViewById(R.id.textViewDialogAfterVotingYes);
        textViewDialogAfterVotingYes.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewDialogAfterVotingYes.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        TextView textViewDialogAfterVotingNo = (TextView) findViewById(R.id.textViewDialogAfterVotingNo);
        textViewDialogAfterVotingNo.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewDialogAfterVotingNo.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewDialogAfterVotingYes.setOnClickListener(this);
        textViewDialogAfterVotingNo.setOnClickListener(this);

        mySounds = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundFinishID = mySounds.load(this,R.raw.finish, 1);
    }

    @Override
    public void onClick(View v) {

        mySounds.play(soundFinishID, 1, 1, 1, 0, 1);

        switch(v.getId()) {
            case R.id.textViewDialogAfterVotingYes:
                if (Appodeal.isLoaded(Appodeal.NON_SKIPPABLE_VIDEO)) {
                    Appodeal.show(AfterVotingActivity.this, Appodeal.NON_SKIPPABLE_VIDEO);
                }else if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)) {
                    Appodeal.show(AfterVotingActivity.this, Appodeal.INTERSTITIAL);
                }else{
                    finish();
                    startActivity(new Intent(AfterVotingActivity.this, FinishActivity.class));
                }
                break;
            case R.id.textViewDialogAfterVotingNo:
                finish();
                startActivity(new Intent(this,  MenuActivity.class));
                break;
        }

        Appodeal.setNonSkippableVideoCallbacks(new NonSkippableVideoCallbacks() {
            private Toast mToast;

            @Override
            public void onNonSkippableVideoLoaded() {
                if (Questions.isDebuging) showToast("onNonSkippableLoaded");
            }

            @Override
            public void onNonSkippableVideoFailedToLoad() {
                if (Questions.isDebuging) showToast("onNonSkippableFailedToLoad");
//                finish();
//                startActivity(new Intent(AfterTestActivity.this, FinishActivity.class));
            }

            @Override
            public void onNonSkippableVideoShown() {
                if (Questions.isDebuging) showToast("onNonSkippableShown");
//                finish();
//                startActivity(new Intent(AfterTestActivity.this, FinishActivity.class));
            }

            @Override
            public void onNonSkippableVideoFinished() {
                if (Questions.isDebuging) showToast("onNonSkippableClicked");

            }

            @Override
            public void onNonSkippableVideoClosed(boolean b) {
                if (Questions.isDebuging) showToast("onNonSkippableClosed");
                finish();
                startActivity(new Intent(AfterVotingActivity.this, FinishActivity.class));
            }

//            @Override
//            public void onNonSkippableVideoClosed() {
//                if (Questions.isDebuging) showToast("onNonSkippableClosed");
//                finish();
//                startActivity(new Intent(AfterTestActivity.this, FinishActivity.class));
//            }

            void showToast(final String text) {
                if (mToast == null) {
                    mToast = Toast.makeText(AfterVotingActivity.this, text, Toast.LENGTH_SHORT);
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
                if (Questions.isDebuging) showToast("onInterstitialLoaded");
            }

            @Override
            public void onInterstitialFailedToLoad() {
                if (Questions.isDebuging) showToast("onInterstitialFailedToLoad");

            }

            @Override
            public void onInterstitialShown() {
                if (Questions.isDebuging) showToast("onInterstitialShown");
//                finish();
//                startActivity(new Intent(AfterTestActivity.this, FinishActivity.class));
            }

            @Override
            public void onInterstitialClicked() {
                if (Questions.isDebuging) showToast("onInterstitialClicked");
                //startActivity(new Intent(AfterTestActivity.this, FinishActivity.class));
            }

            @Override
            public void onInterstitialClosed() {
                if (Questions.isDebuging) showToast("onInterstitialClosed");
                finish();
                startActivity(new Intent(AfterVotingActivity.this, FinishActivity.class));
            }

            void showToast(final String text) {
                if (mToast == null) {
                    mToast = Toast.makeText(AfterVotingActivity.this, text, Toast.LENGTH_SHORT);
                }
                mToast.setText(text);
                mToast.setDuration(Toast.LENGTH_SHORT);
                mToast.show();
            }
        });

    }
}
