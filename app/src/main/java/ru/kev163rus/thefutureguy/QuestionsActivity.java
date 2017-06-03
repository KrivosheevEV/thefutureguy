package ru.kev163rus.thefutureguy;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


import com.appodeal.ads.Appodeal;
import com.google.android.gms.common.internal.GetServiceRequest;

import java.lang.reflect.Array;
import java.util.Random;

import ru.kev163rus.thefutureguy.R;

import static ru.kev163rus.thefutureguy.Questions.indexOfQuestion;

public class QuestionsActivity extends Activity implements View.OnClickListener {

    private TextView textViewQuestionCountOfQuestions, textViewQuestionFinish, textViewQuestionText, textViewQuestionAnswer1, textViewQuestionAnswer2, textViewQuestionAnswer3,
            textViewQuestionBack, textViewQuestionSkip;
    boolean bannerAppodealIsShowed;
    Handler h;
    int[] arrayOfDialogs;
    SoundPool mySounds;
    int soundClickItemID, soundAlertID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_questions);

        textViewQuestionCountOfQuestions = (TextView) findViewById(R.id.textViewCountOfQuestions);
        textViewQuestionCountOfQuestions.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewQuestionCountOfQuestions.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewQuestionFinish = (TextView) findViewById(R.id.textViewQuestionFinish);
        textViewQuestionFinish.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewQuestionFinish.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewQuestionText = (TextView) findViewById(R.id.textViewQuestionText);
        textViewQuestionText.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewQuestionText.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewQuestionAnswer1 = (TextView) findViewById(R.id.textViewQuestionAnswer1);
        textViewQuestionAnswer1.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewQuestionAnswer1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewQuestionAnswer2 = (TextView) findViewById(R.id.textViewQuestionAnswer2);
        textViewQuestionAnswer2.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewQuestionAnswer2.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewQuestionAnswer3 = (TextView) findViewById(R.id.textViewQuestionAnswer3);
        textViewQuestionAnswer3.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewQuestionAnswer3.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewQuestionBack = (TextView) findViewById(R.id.textViewQuestionBack);
        textViewQuestionBack.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewQuestionBack.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewQuestionSkip = (TextView) findViewById(R.id.textViewQuestionSkip);
        textViewQuestionSkip.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewQuestionSkip.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewQuestionFinish.setOnClickListener(this);
        textViewQuestionAnswer1.setOnClickListener(this);
        textViewQuestionAnswer2.setOnClickListener(this);
        textViewQuestionAnswer3.setOnClickListener(this);
        textViewQuestionBack.setOnClickListener(this);
        textViewQuestionSkip.setOnClickListener(this);

        setQuestionAndAnswers(indexOfQuestion);

        arrayOfDialogs = new int[10];
        fillArrayOfDialogs(arrayOfDialogs);

        mySounds = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundClickItemID = mySounds.load(this,R.raw.clickitem, 1);
        soundAlertID = mySounds.load(this,R.raw.alert, 1);

        Questions.isDebuging = false;    // set "false" in release.

        // Реклама.
        //Appodeal.disableLocationPermissionCheck();
        Appodeal.setTesting(Questions.isDebuging);

        h = new Handler() {
            public void handleMessage(android.os.Message msg) {

                String appKey = "a3e070f65cf3e387ed1234a2dc0a5777fcbe0a3b940a52b6";
                Appodeal.initialize(QuestionsActivity.this, appKey, Appodeal.NON_SKIPPABLE_VIDEO);
                Appodeal.initialize(QuestionsActivity.this, appKey, Appodeal.INTERSTITIAL);
                Appodeal.initialize(QuestionsActivity.this, appKey, Appodeal.BANNER);

            }
        };
        h.sendEmptyMessage(0);

        bannerAppodealIsShowed = false;
        showBannerAppodeal(false);
        ///

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mySounds.release();
    }

    @Override
    public void onClick(View v) {

        showBannerAppodeal(bannerAppodealIsShowed);

        switch(v.getId()) {
            case R.id.textViewQuestionAnswer1:
                setNewQuestion(true, 1);
                break;
            case R.id.textViewQuestionAnswer2:
                setNewQuestion(true, 2);
                break;
            case R.id.textViewQuestionAnswer3:
                setNewQuestion(true, 3);
                break;
            case R.id.textViewQuestionFinish:
                finishTest();
                break;
            case R.id.textViewQuestionBack:
                setNewQuestion(false, 0);
                break;
            case R.id.textViewQuestionSkip:
                setNewQuestion(true, 0);
                break;
        }

        mySounds.play(soundClickItemID, 1, 1, 1, 0, 1);
    }

    private void fillArrayOfDialogs(int[] givenArray) {

        for (int countOfArray = 0; countOfArray < givenArray.length; countOfArray++) {
            givenArray[countOfArray] = countOfArray;
        }

        Random rnd = new Random();
        for (int i = givenArray.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = givenArray[index];
            givenArray[index] = givenArray[i];
            givenArray[i] = a;
        }
    }

    private void showBannerAppodeal(boolean bannerIsShowed) {
        if (Appodeal.isLoaded(Appodeal.BANNER) & !bannerIsShowed) {
            Appodeal.show(this, Appodeal.BANNER_BOTTOM);
            bannerAppodealIsShowed = true;
        }
    }

    private void setQuestionAndAnswers(int indexOfQuestion){

        String textToTextView = String.valueOf(indexOfQuestion) + " " + getString(R.string.textViewQuestionCountOfQuestions) + " " + String.valueOf(Questions.countOfQuestions);
        textViewQuestionCountOfQuestions.setText(textToTextView);
        try {
            if (!Questions.itVoting){
                textViewQuestionText.setText(getQuestionText(Questions.arrayOfNumQuestions[indexOfQuestion - 1]));
            } else {
                textViewQuestionText.setText(getVotingQuestionText(Questions.arrayOfNumQuestions[indexOfQuestion - 1]));
            }
        } catch (Exception e) {/**/}
        try {
            if (!Questions.itVoting){
                textViewQuestionAnswer1.setText(getAnswer1Text(Questions.arrayOfNumQuestions[indexOfQuestion - 1]));
            } else {
                textViewQuestionAnswer1.setText(getVotingAnswer1Text(Questions.arrayOfNumQuestions[indexOfQuestion - 1]));
            }
        } catch (Exception e) {/**/}
        try {
            if (!Questions.itVoting){
                textViewQuestionAnswer2.setText(getAnswer2Text(Questions.arrayOfNumQuestions[indexOfQuestion - 1]));
            } else {
                textViewQuestionAnswer2.setText(getVotingAnswer2Text(Questions.arrayOfNumQuestions[indexOfQuestion - 1]));
            }
        } catch (Exception e) {/**/}
        try {
            if (!Questions.itVoting){
                textViewQuestionAnswer3.setText(getAnswer3Text(Questions.arrayOfNumQuestions[indexOfQuestion - 1]));
            } else {
                textViewQuestionAnswer3.setText(getVotingAnswer3Text(Questions.arrayOfNumQuestions[indexOfQuestion - 1]));
            }
        } catch (Exception e) {/**/}

    }

    private String getQuestionText(int indexOfQuestion) {

        String result = "";

        switch (indexOfQuestion) {
            case 1:
                result = getString(R.string.question1);
                break;
            case 2:
                result = getString(R.string.question2);
                break;
            case 3:
                result = getString(R.string.question3);
                break;
            case 4:
                result = getString(R.string.question4);
                break;
            case 5:
                result = getString(R.string.question5);
                break;
            case 6:
                result = getString(R.string.question6);
                break;
            case 7:
                result = getString(R.string.question7);
                break;
            case 8:
                result = getString(R.string.question8);
                break;
            case 9:
                result = getString(R.string.question9);
                break;
            case 10:
                result = getString(R.string.question10);
                break;
            case 11:
                result = getString(R.string.question11);
                break;
            case 12:
                result = getString(R.string.question12);
                break;
            case 13:
                result = getString(R.string.question13);
                break;
            case 14:
                result = getString(R.string.question14);
                break;
            case 15:
                result = getString(R.string.question15);
                break;
            case 16:
                result = getString(R.string.question16);
                break;
            case 17:
                result = getString(R.string.question17);
                break;
            case 18:
                result = getString(R.string.question18);
                break;
            case 19:
                result = getString(R.string.question19);
                break;
            case 20:
                result = getString(R.string.question20);
                break;
            case 21:
                result = getString(R.string.question21);
                break;
            case 22:
                result = getString(R.string.question22);
                break;
            case 23:
                result = getString(R.string.question23);
                break;
            case 24:
                result = getString(R.string.question24);
                break;
            case 25:
                result = getString(R.string.question25);
                break;
            case 26:
                result = getString(R.string.question26);
                break;
            case 27:
                result = getString(R.string.question27);
                break;
            case 28:
                result = getString(R.string.question28);
                break;
            case 29:
                result = getString(R.string.question29);
                break;
            case 30:
                result = getString(R.string.question30);
                break;
            case 31:
                result = getString(R.string.question31);
                break;
            case 32:
                result = getString(R.string.question32);
                break;
            case 33:
                result = getString(R.string.question33);
                break;
            case 34:
                result = getString(R.string.question34);
                break;
            case 35:
                result = getString(R.string.question35);
                break;
            default:
                result = "--(" + String.valueOf(indexOfQuestion) + ")--";
                break;
        }

        return result;

    }

    private String getAnswer1Text(int indexOfQuestion) {

        String result = "";

        switch (indexOfQuestion) {
            case 1:
                result = getString(R.string.answer1_1);
                break;
            case 2:
                result = getString(R.string.answer2_1);
                break;
            case 3:
                result = getString(R.string.answer3_1);
                break;
            case 4:
                result = getString(R.string.answer4_1);
                break;
            case 5:
                result = getString(R.string.answer5_1);
                break;
            case 6:
                result = getString(R.string.answer6_1);
                break;
            case 7:
                result = getString(R.string.answer7_1);
                break;
            case 8:
                result = getString(R.string.answer8_1);
                break;
            case 9:
                result = getString(R.string.answer9_1);
                break;
            case 10:
                result = getString(R.string.answer10_1);
                break;
            case 11:
                result = getString(R.string.answer11_1);
                break;
            case 12:
                result = getString(R.string.answer12_1);
                break;
            case 13:
                result = getString(R.string.answer13_1);
                break;
            case 14:
                result = getString(R.string.answer14_1);
                break;
            case 15:
                result = getString(R.string.answer15_1);
                break;
            case 16:
                result = getString(R.string.answer16_1);
                break;
            case 17:
                result = getString(R.string.answer17_1);
                break;
            case 18:
                result = getString(R.string.answer18_1);
                break;
            case 19:
                result = getString(R.string.answer19_1);
                break;
            case 20:
                result = getString(R.string.answer20_1);
                break;
            case 21:
                result = getString(R.string.answer21_1);
                break;
            case 22:
                result = getString(R.string.answer22_1);
                break;
            case 23:
                result = getString(R.string.answer23_1);
                break;
            case 24:
                result = getString(R.string.answer24_1);
                break;
            case 25:
                result = getString(R.string.answer25_1);
                break;
            case 26:
                result = getString(R.string.answer26_1);
                break;
            case 27:
                result = getString(R.string.answer27_1);
                break;
            case 28:
                result = getString(R.string.answer28_1);
                break;
            case 29:
                result = getString(R.string.answer29_1);
                break;
            case 30:
                result = getString(R.string.answer30_1);
                break;
            case 31:
                result = getString(R.string.answer31_1);
                break;
            case 32:
                result = getString(R.string.answer32_1);
                break;
            case 33:
                result = getString(R.string.answer33_1);
                break;
            case 34:
                result = getString(R.string.answer34_1);
                break;
            case 35:
                result = getString(R.string.answer35_1);
                break;
            default:
                result = "--(" + String.valueOf(indexOfQuestion) + ")--";
                break;

        }

        return result;

    }

    private String getAnswer2Text(int indexOfQuestion) {

        String result = "";

        switch (indexOfQuestion) {
            case 1:
                result = getString(R.string.answer1_2);
                break;
            case 2:
                result = getString(R.string.answer2_2);
                break;
            case 3:
                result = getString(R.string.answer3_2);
                break;
            case 4:
                result = getString(R.string.answer4_2);
                break;
            case 5:
                result = getString(R.string.answer5_2);
                break;
            case 6:
                result = getString(R.string.answer6_2);
                break;
            case 7:
                result = getString(R.string.answer7_2);
                break;
            case 8:
                result = getString(R.string.answer8_2);
                break;
            case 9:
                result = getString(R.string.answer9_2);
                break;
            case 10:
                result = getString(R.string.answer10_2);
                break;
            case 11:
                result = getString(R.string.answer11_2);
                break;
            case 12:
                result = getString(R.string.answer12_2);
                break;
            case 13:
                result = getString(R.string.answer13_2);
                break;
            case 14:
                result = getString(R.string.answer14_2);
                break;
            case 15:
                result = getString(R.string.answer15_2);
                break;
            case 16:
                result = getString(R.string.answer16_2);
                break;
            case 17:
                result = getString(R.string.answer17_2);
                break;
            case 18:
                result = getString(R.string.answer18_2);
                break;
            case 19:
                result = getString(R.string.answer19_2);
                break;
            case 20:
                result = getString(R.string.answer20_2);
                break;
            case 21:
                result = getString(R.string.answer21_2);
                break;
            case 22:
                result = getString(R.string.answer22_2);
                break;
            case 23:
                result = getString(R.string.answer23_2);
                break;
            case 24:
                result = getString(R.string.answer24_2);
                break;
            case 25:
                result = getString(R.string.answer25_2);
                break;
            case 26:
                result = getString(R.string.answer26_2);
                break;
            case 27:
                result = getString(R.string.answer27_2);
                break;
            case 28:
                result = getString(R.string.answer28_2);
                break;
            case 29:
                result = getString(R.string.answer29_2);
                break;
            case 30:
                result = getString(R.string.answer30_2);
                break;
            case 31:
                result = getString(R.string.answer31_2);
                break;
            case 32:
                result = getString(R.string.answer32_2);
                break;
            case 33:
                result = getString(R.string.answer33_2);
                break;
            case 34:
                result = getString(R.string.answer34_2);
                break;
            case 35:
                result = getString(R.string.answer35_2);
                break;
            default:
                result = "--(" + String.valueOf(indexOfQuestion) + ")--";
                break;
        }

        return result;

    }

    private String getAnswer3Text(int indexOfQuestion) {

        String result = "";

        switch (indexOfQuestion) {
            case 1:
                result = getString(R.string.answer1_3);
                break;
            case 2:
                result = getString(R.string.answer2_3);
                break;
            case 3:
                result = getString(R.string.answer3_3);
                break;
            case 4:
                result = getString(R.string.answer4_3);
                break;
            case 5:
                result = getString(R.string.answer5_3);
                break;
            case 6:
                result = getString(R.string.answer6_3);
                break;
            case 7:
                result = getString(R.string.answer7_3);
                break;
            case 8:
                result = getString(R.string.answer8_3);
                break;
            case 9:
                result = getString(R.string.answer9_3);
                break;
            case 10:
                result = getString(R.string.answer10_3);
                break;
            case 11:
                result = getString(R.string.answer11_3);
                break;
            case 12:
                result = getString(R.string.answer12_3);
                break;
            case 13:
                result = getString(R.string.answer13_3);
                break;
            case 14:
                result = getString(R.string.answer14_3);
                break;
            case 15:
                result = getString(R.string.answer15_3);
                break;
            case 16:
                result = getString(R.string.answer16_3);
                break;
            case 17:
                result = getString(R.string.answer17_3);
                break;
            case 18:
                result = getString(R.string.answer18_3);
                break;
            case 19:
                result = getString(R.string.answer19_3);
                break;
            case 20:
                result = getString(R.string.answer20_3);
                break;
            case 21:
                result = getString(R.string.answer21_3);
                break;
            case 22:
                result = getString(R.string.answer22_3);
                break;
            case 23:
                result = getString(R.string.answer23_3);
                break;
            case 24:
                result = getString(R.string.answer24_3);
                break;
            case 25:
                result = getString(R.string.answer25_3);
                break;
            case 26:
                result = getString(R.string.answer26_3);
                break;
            case 27:
                result = getString(R.string.answer27_3);
                break;
            case 28:
                result = getString(R.string.answer28_3);
                break;
            case 29:
                result = getString(R.string.answer29_3);
                break;
            case 30:
                result = getString(R.string.answer30_3);
                break;
            case 31:
                result = getString(R.string.answer31_3);
                break;
            case 32:
                result = getString(R.string.answer32_3);
                break;
            case 33:
                result = getString(R.string.answer33_3);
                break;
            case 34:
                result = getString(R.string.answer34_3);
                break;
            case 35:
                result = getString(R.string.answer35_3);
                break;
            default:
                result = "--(" + String.valueOf(indexOfQuestion) + ")--";
                break;
        }

        return result;

    }

    private String getVotingQuestionText(int indexOfQuestion){

        String result = "";

        switch (indexOfQuestion) {
            case 1:
                result = getString(R.string.votingQuestion1);
                break;
            case 2:
                result = getString(R.string.votingQuestion2);
                break;
            case 3:
                result = getString(R.string.votingQuestion3);
                break;
            case 4:
                result = getString(R.string.votingQuestion4);
                break;
            case 5:
                result = getString(R.string.votingQuestion5);
                break;
            default:
                result = "--(" + String.valueOf(indexOfQuestion) + ")--";
                break;
        }

        return result;
    }

    private String getVotingAnswer1Text(int indexOfQuestion){

        String result = "";

        switch (indexOfQuestion) {
            case 1:
                result = getString(R.string.votingAnswer1_1);
                break;
            case 2:
                result = getString(R.string.votingAnswer2_1);
                break;
            case 3:
                result = getString(R.string.votingAnswer3_1);
                break;
            case 4:
                result = getString(R.string.votingAnswer4_1);
                break;
            case 5:
                result = getString(R.string.votingAnswer5_1);
                break;
            default:
                result = "--(" + String.valueOf(indexOfQuestion) + ")--";
                break;
        }

        return result;
    }

    private String getVotingAnswer2Text(int indexOfQuestion){

        String result = "";

        switch (indexOfQuestion) {
            case 1:
                result = getString(R.string.votingAnswer1_2);
                break;
            case 2:
                result = getString(R.string.votingAnswer2_2);
                break;
            case 3:
                result = getString(R.string.votingAnswer3_2);
                break;
            case 4:
                result = getString(R.string.votingAnswer4_2);
                break;
            case 5:
                result = getString(R.string.votingAnswer5_2);
                break;
            default:
                result = "--(" + String.valueOf(indexOfQuestion) + ")--";
                break;
        }

        return result;
    }

    private String getVotingAnswer3Text(int indexOfQuestion){

        String result = "";

        switch (indexOfQuestion) {
            case 1:
                result = getString(R.string.votingAnswer1_3);
                break;
            case 2:
                result = getString(R.string.votingAnswer2_3);
                break;
            case 3:
                result = getString(R.string.votingAnswer3_3);
                break;
            case 4:
                result = getString(R.string.votingAnswer4_3);
                break;
            case 5:
                result = getString(R.string.votingAnswer5_3);
                break;
            default:
                result = "--(" + String.valueOf(indexOfQuestion) + ")--";
                break;
        }

        return result;
    }


    public void setNewQuestion(boolean isIncrement, int userChoise){

//        if (arrayOfDialogs != null && Questions.indexOfQuestion % 4 == 0) setDialog(arrayOfDialogs[(Questions.indexOfQuestion / 4) - 1]);

        if (!Questions.itVoting){
            Questions.setUserResult(indexOfQuestion - 1, userChoise);
        } else if (Questions.arrayOfVotingResult.length != 0 && indexOfQuestion > 0 & indexOfQuestion <= Questions.arrayOfVotingResult.length){
            Questions.arrayOfVotingResult[indexOfQuestion - 1] = userChoise;
        }

        if (isIncrement & indexOfQuestion < Questions.countOfQuestions){
            indexOfQuestion++;
        }else if (!isIncrement & indexOfQuestion > 1) {
            indexOfQuestion--;
        }else if (isIncrement & indexOfQuestion == Questions.countOfQuestions){
            finishTest();
        }

        switch (userChoise){
            case 1:
                textViewQuestionAnswer2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.textviewfadeout));
                textViewQuestionAnswer3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.textviewfadeout));
                break;
            case 2:
                textViewQuestionAnswer1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.textviewfadeout));
                textViewQuestionAnswer3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.textviewfadeout));
                break;
            case 3:
                textViewQuestionAnswer1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.textviewfadeout));
                textViewQuestionAnswer2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.textviewfadeout));
                break;
            default: break;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setQuestionAndAnswers(indexOfQuestion);
            }
        }, 1500);
    }

    public void setDialog(int givenIndexOfDialogsArray) {

        AlertDialog.Builder builder = new AlertDialog.Builder(QuestionsActivity.this);
        builder.setTitle(getString(R.string.dialog1_title))
                .setIcon(R.drawable.twoheart1)
                .setCancelable(false)
                .setNegativeButton(getString(R.string.dialog1_buttonPositive),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                mySounds.play(soundAlertID, 1, 1, 1, 0, 1);
                            }
                        });
        AlertDialog alert = builder.create();


        switch (givenIndexOfDialogsArray){
            case 1: alert.setMessage(getString(R.string.dialog1_text)); break;
            case 2: alert.setMessage(getString(R.string.dialog2_text)); break;
            case 3: alert.setMessage(getString(R.string.dialog3_text)); break;
            case 4: alert.setMessage(getString(R.string.dialog4_text)); break;
            case 5: alert.setMessage(getString(R.string.dialog5_text)); break;
            case 6: alert.setMessage(getString(R.string.dialog6_text)); break;
            case 7: alert.setMessage(getString(R.string.dialog7_text)); break;
            case 8: alert.setMessage(getString(R.string.dialog8_text)); break;
            case 9: alert.setMessage(getString(R.string.dialog9_text)); break;
            case 10: alert.setMessage(getString(R.string.dialog10_text)); break;
            default: alert.setMessage(getString(R.string.dialog10_text)); break;
        }

        alert.show();

    }

    private void finishTest(){

        finish();
        if (!Questions.itVoting){
            startActivity(new Intent(this, AfterTestActivity.class));
        } else {
            startActivity(new Intent(this, AfterVotingActivity.class));
        }
    }
}


