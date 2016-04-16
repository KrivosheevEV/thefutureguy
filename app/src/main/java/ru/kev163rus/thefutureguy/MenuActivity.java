package ru.kev163rus.thefutureguy;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogRecord;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.appodeal.ads.Appodeal;

import ru.kev163rus.thefutureguy.R;

public class MenuActivity extends FragmentActivity implements View.OnClickListener {

    private Timer mTimer;
    private MyTimerTask mMyTimerTask;
    private TextView textViewLogo;
    private Animation handFadeInAnimation;
    private Animation handFadeOutAnimation;
    private ImageView imageViewTwoHeart;
    static Intent activityQuestion, activityMenu;
    Intent activityBeforeTest;
    Dialog dialog;
    SoundPool mySounds;
    int musicStartID, soundClickMenuID;
    MediaPlayer myMusic;
    Handler handlerOfSounds;

    private int stateMediaPlayer;
    private final int stateMP_NotStarter = 0;
    private final int stateMP_Playing = 1;
    private final int stateMP_Pausing = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_menu);

        textViewLogo = (TextView) findViewById(R.id.textViewLogo);
        textViewLogo.setShadowLayer(10f, 60f, 30f, ContextCompat.getColor(this, R.color.color1));
        textViewLogo.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/pudelinka.ttf"));

        TextView textViewStartFastTest = (TextView) findViewById(R.id.textViewStartFastTest);
        textViewStartFastTest.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewStartFastTest.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        TextView textViewStartFullTest = (TextView) findViewById(R.id.textViewStartFullTest);
        textViewStartFullTest.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewStartFullTest.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        TextView textViewStartAddTest = (TextView) findViewById(R.id.textViewStartAddTest);
        textViewStartAddTest.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewStartAddTest.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        TextView textViewExit = (TextView) findViewById(R.id.textViewExit);
        textViewExit.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewExit.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

//        if (mTimer != null) {
//            mTimer.cancel();
//        }
//        mTimer = new Timer();
//        mMyTimerTask = new MyTimerTask();
//        mTimer.schedule(mMyTimerTask, 300, 2000);

        imageViewTwoHeart = (ImageView) findViewById(R.id.imageViewTwoHeart);

        handFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.handfadein);
        handFadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.handfadeout);
        Animation logoFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.logofadein);
        Animation logoFadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.logofadeout);

        handFadeInAnimation.setAnimationListener(animationFadeInListener);
        handFadeOutAnimation.setAnimationListener(animationFadeOutListener);
        logoFadeInAnimation.setAnimationListener(animationFadeInListener);
        logoFadeOutAnimation.setAnimationListener(animationFadeOutListener);

        imageViewTwoHeart.startAnimation(handFadeInAnimation);

        activityQuestion = new Intent(this, QuestionsActivity.class);
        activityMenu = new Intent(this, MenuActivity.class);
        activityBeforeTest = new Intent(this, BeforeTestActivity.class);

        textViewStartFastTest.setOnClickListener(this);
        textViewStartFullTest.setOnClickListener(this);
        textViewStartAddTest.setOnClickListener(this);
        textViewExit.setOnClickListener(this);

        resetData();

        mySounds = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundClickMenuID = mySounds.load(this,R.raw.clickmenu, 1);
        myMusic = new MediaPlayer();
        myMusic = MediaPlayer.create(this, R.raw.start);
        stateMediaPlayer = stateMP_NotStarter;

        handlerOfSounds = new Handler() {
            public void handleMessage(android.os.Message msg) {

                switch (msg.what) {
                    case 1:
                        myMusic.start();
                    case 2:
                        mySounds.play(soundClickMenuID, 1, 1, 1, 0, 1);
                    default: break;
                }
            }
        };

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                myMusic.start();
            }
        }, 1500);

    }

    @Override
    public void onResume()
    {
        super.onResume();
        //mySounds.play(soundStartID, 1, 1, 1, 0, 1);
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mySounds.release();
        myMusic.stop();
        myMusic.release();
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.textViewStartFastTest:
                setData("Fast");
                startActivity(activityBeforeTest);
                finish();
                break;
            case R.id.textViewStartFullTest:
                setData("Full");
                startActivity(activityBeforeTest);
                finish();
                break;
            case R.id.textViewStartAddTest:
                setData("Add");
                startActivity(activityBeforeTest);
                finish();
                break;
            case R.id.textViewExit:
                finish();
                System.exit(0);
                break;
        }

        handlerOfSounds.sendEmptyMessageDelayed(2, 100);
    }

    @Override
    protected void onPause() {
        super.onPause();
        imageViewTwoHeart.clearAnimation();
    }

    private void resetData(){
        Questions.indexOfQuestion = 1;
        Questions.itFastTest = false;
        Questions.itFullTest = false;
        Questions.itAddTest = false;
        Questions.arrayUserResult = null;
        Questions.arrayOfNumQuestions = null;
    }

    private void setData(String itemMenu){

        switch (itemMenu){
            case "Fast":
                Questions.itFastTest = true;
                Questions.countOfQuestions = 10;
                Questions.fillArrayOfNumQuestions(Questions.countOfQuestions);
                break;
            case "Full":
                Questions.itFullTest = true;
                Questions.countOfQuestions = 25;
                Questions.fillArrayOfNumQuestions(Questions.countOfQuestions);
                break;
            case "Add":
                Questions.itAddTest = true;
                Questions.countOfQuestions = 0;
                Questions.fillArrayOfNumQuestions(Questions.countOfQuestions);
                break;
            default:
                Questions.itFastTest = true;
                Questions.countOfQuestions = 10;
                Questions.fillArrayOfNumQuestions(Questions.countOfQuestions);
                break;
        }

        Questions.arrayUserResult = new int[Questions.countOfQuestions];

    }

    Animation.AnimationListener animationFadeOutListener = new Animation.AnimationListener() {

        @Override
        public void onAnimationEnd(Animation animation) {
            imageViewTwoHeart.startAnimation(handFadeInAnimation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub
        }
    };

    Animation.AnimationListener animationFadeInListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationEnd(Animation animation) {
            imageViewTwoHeart.startAnimation(handFadeOutAnimation);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub
        }
    };

    // Timer.
    class MyTimerTask extends TimerTask {

        @Override
        public void run() {

//            final String RefreshingTextLogoLine1 = CreateLogoText(getString(R.string.LogoTextLine1), 15);
//            final String RefreshingTextLogoLine2 = CreateLogoText(getString(R.string.LogoTextLine2), 10);

            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Random mRandom = new Random();

//                    if (textViewLogo.getVisibility() == View.VISIBLE) {
////                        Animation out = AnimationUtils.makeOutAnimation(MenuActivity.this, true);
//                        Animation logoOut = AnimationUtils.loadAnimation(MenuActivity.this, R.anim.logofadeout);
//                        textViewLogo.startAnimation(logoOut);
//                        textViewLogoLine2.startAnimation(logoOut);
//                        textViewLogoLine1.setVisibility(View.INVISIBLE);
//                        textViewLogoLine2.setVisibility(View.INVISIBLE);
//                    } else {
//                        Animation logoIn = AnimationUtils.loadAnimation(MenuActivity.this, R.anim.logofadein);
//                        textViewLogoLine1.startAnimation(logoIn);
//                        textViewLogoLine2.startAnimation(logoIn);
//                        textViewLogoLine1.setVisibility(View.VISIBLE);
//                        textViewLogoLine2.setVisibility(View.VISIBLE);
//
//                        int LogoLine1Left = mRandom.nextInt(textViewLogoLine1.getWidth() - 55);
//                        int LogoLine2Left = mRandom.nextInt((textViewLogoLine2.getWidth() / 2) - 125);
//
//                        int LogoLine1Top = mRandom.nextInt(textViewLogoLine1.getHeight() - 80);
//                        int LogoLine2Top = mRandom.nextInt(textViewLogoLine2.getHeight() - 80);
//
//                        if (LogoLine1Left < 0) LogoLine1Left = 0;
//                        if (LogoLine2Left < 0) LogoLine2Left = 0;
//                        if (LogoLine1Top < 0) LogoLine1Top = 0;
//                        if (LogoLine2Top < 0) LogoLine2Top = 0;
//
//                        textViewLogoLine1.setPadding(LogoLine1Left, LogoLine1Top, 0, 0);
//                        textViewLogoLine2.setPadding(LogoLine2Left, LogoLine2Top, 0, 0);
//                    }
                }
            });
        }
    }

    private String CreateLogoText(String givenString, int givemMaxChars){
        Random mRandom = new Random();
        int PrefixLogoText = mRandom.nextInt(givemMaxChars);
        String sResult = createString(PrefixLogoText, " ") + givenString;
        return sResult;
    }

    public String createString(int givenLengthString, String givenChar ) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < givenLengthString; i++) {
//            int number = random.nextInt(mCHAR.length());
            builder.append(givenChar);
        }
        return builder.toString();
    }
}
