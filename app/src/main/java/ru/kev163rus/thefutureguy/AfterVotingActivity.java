package ru.kev163rus.thefutureguy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.AsyncTask;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static android.R.attr.data;
import static android.R.attr.track;
import static android.text.TextUtils.concat;

public class AfterVotingActivity extends Activity implements View.OnClickListener {

    SoundPool mySounds;
    int soundFinishID;

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

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
                saveResults();
//                sendResultsToSite();
                sendResults();
                if (Appodeal.isLoaded(Appodeal.NON_SKIPPABLE_VIDEO)) {
                    Appodeal.show(AfterVotingActivity.this, Appodeal.NON_SKIPPABLE_VIDEO);
                }else if(Appodeal.isLoaded(Appodeal.INTERSTITIAL)) {
                    Appodeal.show(AfterVotingActivity.this, Appodeal.INTERSTITIAL);
                }else{
                    finish();
                    if (!Questions.itVoting){
                        startActivity(new Intent(AfterVotingActivity.this, FinishActivity.class));
                    } else {
                        startActivity(new Intent(AfterVotingActivity.this, RateAppActivity.class));
                    }
                }
                break;
            case R.id.textViewDialogAfterVotingNo:
                finish();
                startActivity(new Intent(AfterVotingActivity.this,  MenuActivity.class));
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
                if (!Questions.itVoting){
                    startActivity(new Intent(AfterVotingActivity.this, FinishActivity.class));
                } else {
                    startActivity(new Intent(AfterVotingActivity.this, RateAppActivity.class));
                }
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
                if (!Questions.itVoting){
                    startActivity(new Intent(AfterVotingActivity.this, FinishActivity.class));
                } else {
                    startActivity(new Intent(AfterVotingActivity.this, RateAppActivity.class));
                }
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

    public void saveResults() {

        SharedPreferences mSettings;
        String APP_PREFERENCES = "theFutureGuyVoting";

        try {

            String prefCounterVoting = "counterVoting";

            mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = mSettings.edit();
            int counterVoting = 0;
            counterVoting = mSettings.getInt(prefCounterVoting, counterVoting);
            editor.putInt(prefCounterVoting, ++counterVoting);
            editor.putString("voiting".concat(String.valueOf(counterVoting)).concat("_userID"), Questions.userId);
            editor.putString("voiting".concat(String.valueOf(counterVoting)).concat("_date"), new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()));
            editor.putString("voiting".concat(String.valueOf(counterVoting)).concat("_question1"), String.valueOf(Questions.arrayOfVotingResult[0]));
            editor.putString("voiting".concat(String.valueOf(counterVoting)).concat("_question2"), String.valueOf(Questions.arrayOfVotingResult[1]));
            editor.putString("voiting".concat(String.valueOf(counterVoting)).concat("_question3"), String.valueOf(Questions.arrayOfVotingResult[2]));
            editor.putString("voiting".concat(String.valueOf(counterVoting)).concat("_question4"), String.valueOf(Questions.arrayOfVotingResult[3]));
            editor.putString("voiting".concat(String.valueOf(counterVoting)).concat("_question5"), String.valueOf(Questions.arrayOfVotingResult[4]));
            editor.apply();

        } catch (Exception e) {/**/}

    }

    private void sendResultsToSite() {

        File file = new File("http:////parserpro.ru/tfg/resultVoting.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write("test");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                    String a="a";
                } else {
//                    String b="b";
                }

            } catch (Exception e) {
//                String c="c";
            }
            return null;
        }
    }

    private void sendResults(){

        String url = "http://parserpro.ru/tfg/saveResult.php?"
                .concat("userID=".concat(Questions.userId).concat("&"))
                .concat("dateVoting=").concat(String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))).concat("&")
                .concat("answer1=").concat(String.valueOf(Questions.arrayOfVotingResult[0])).concat("&")
                .concat("answer2=").concat(String.valueOf(Questions.arrayOfVotingResult[1])).concat("&")
                .concat("answer3=").concat(String.valueOf(Questions.arrayOfVotingResult[2])).concat("&")
                .concat("answer4=").concat(String.valueOf(Questions.arrayOfVotingResult[3])).concat("&")
                .concat("answer5=").concat(String.valueOf(Questions.arrayOfVotingResult[4]))
                ;

        new LongOperation().execute(url);

    }

}
