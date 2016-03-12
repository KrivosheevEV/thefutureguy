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

import ru.kev163rus.thefutureguy.R;

public class FinishActivity extends Activity implements View.OnClickListener{

    private TextView textViewResult, textViewResultText, textViewAgain, textViewExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_finish);

        textViewResult = (TextView) findViewById(R.id.textViewResult);
        textViewResult.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewResult.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewResultText = (TextView) findViewById(R.id.textViewResultText);
        textViewResultText.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewResultText.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewAgain = (TextView) findViewById(R.id.textViewAgain);
        textViewAgain.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewAgain.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewExit = (TextView) findViewById(R.id.textViewExit);
        textViewExit.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewExit.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewAgain.setOnClickListener(this);
        textViewExit.setOnClickListener(this);

        setText();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.textViewAgain:
                startActivity(new Intent(this, MenuActivity.class));
                finish();
                break;
            case R.id.textViewExit:
                startActivity(new Intent(this, RateAppActivity.class));
                finish();
                break;
        }
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        startActivity(new Intent(this, RateAppActivity.class));
//    }

    private void setText(){

        int userResult = Questions.getUserResult() % 50;

        switch (userResult){
            case 1:
                textViewResult.setText(getText(R.string.result1));
                textViewResultText.setText(getText(R.string.result1Text));
                break;
            case 2:
                textViewResult.setText(getText(R.string.result2));
                textViewResultText.setText(getText(R.string.result2Text));
                break;
            case 3:
                textViewResult.setText(getText(R.string.result3));
                textViewResultText.setText(getText(R.string.result3Text));
                break;
            case 4:
                textViewResult.setText(getText(R.string.result4));
                textViewResultText.setText(getText(R.string.result4Text));
                break;
            case 5:
                textViewResult.setText(getText(R.string.result5));
                textViewResultText.setText(getText(R.string.result5Text));
                break;
            case 6:
                textViewResult.setText(getText(R.string.result6));
                textViewResultText.setText(getText(R.string.result6Text));
                break;
            case 7:
                textViewResult.setText(getText(R.string.result7));
                textViewResultText.setText(getText(R.string.result7Text));
                break;
            case 8:
                textViewResult.setText(getText(R.string.result8));
                textViewResultText.setText(getText(R.string.result8Text));
                break;
            case 9:
                textViewResult.setText(getText(R.string.result9));
                textViewResultText.setText(getText(R.string.result9Text));
                break;
            case 10:
                textViewResult.setText(getText(R.string.result10));
                textViewResultText.setText(getText(R.string.result10Text));
                break;
            case 11:
                textViewResult.setText(getText(R.string.result11));
                textViewResultText.setText(getText(R.string.result11Text));
                break;
            case 12:
                textViewResult.setText(getText(R.string.result12));
                textViewResultText.setText(getText(R.string.result12Text));
                break;
            case 13:
                textViewResult.setText(getText(R.string.result13));
                textViewResultText.setText(getText(R.string.result13Text));
                break;
            case 14:
                textViewResult.setText(getText(R.string.result14));
                textViewResultText.setText(getText(R.string.result14Text));
                break;
            case 15:
                textViewResult.setText(getText(R.string.result15));
                textViewResultText.setText(getText(R.string.result15Text));
                break;
            case 16:
                textViewResult.setText(getText(R.string.result16));
                textViewResultText.setText(getText(R.string.result16Text));
                break;
            case 17:
                textViewResult.setText(getText(R.string.result17));
                textViewResultText.setText(getText(R.string.result17Text));
                break;
            case 18:
                textViewResult.setText(getText(R.string.result18));
                textViewResultText.setText(getText(R.string.result18Text));
                break;
            case 19:
                textViewResult.setText(getText(R.string.result19));
                textViewResultText.setText(getText(R.string.result19Text));
                break;
            case 20:
                textViewResult.setText(getText(R.string.result20));
                textViewResultText.setText(getText(R.string.result20Text));
                break;
            case 21:
                textViewResult.setText(getText(R.string.result21));
                textViewResultText.setText(getText(R.string.result21Text));
                break;
            case 22:
                textViewResult.setText(getText(R.string.result22));
                textViewResultText.setText(getText(R.string.result22Text));
                break;
            case 23:
                textViewResult.setText(getText(R.string.result23));
                textViewResultText.setText(getText(R.string.result23Text));
                break;
            case 24:
                textViewResult.setText(getText(R.string.result24));
                textViewResultText.setText(getText(R.string.result24Text));
                break;
            case 25:
                textViewResult.setText(getText(R.string.result25));
                textViewResultText.setText(getText(R.string.result25Text));
                break;
            case 26:
                textViewResult.setText(getText(R.string.result26));
                textViewResultText.setText(getText(R.string.result26Text));
                break;
            case 27:
                textViewResult.setText(getText(R.string.result27));
                textViewResultText.setText(getText(R.string.result27Text));
                break;
            case 28:
                textViewResult.setText(getText(R.string.result28));
                textViewResultText.setText(getText(R.string.result28Text));
                break;
            case 29:
                textViewResult.setText(getText(R.string.result29));
                textViewResultText.setText(getText(R.string.result29Text));
                break;
            case 30:
                textViewResult.setText(getText(R.string.result30));
                textViewResultText.setText(getText(R.string.result30Text));
                break;
            case 31:
                textViewResult.setText(getText(R.string.result31));
                textViewResultText.setText(getText(R.string.result31Text));
                break;
            case 32:
                textViewResult.setText(getText(R.string.result32));
                textViewResultText.setText(getText(R.string.result32Text));
                break;
            case 33:
                textViewResult.setText(getText(R.string.result33));
                textViewResultText.setText(getText(R.string.result33Text));
                break;
            case 34:
                textViewResult.setText(getText(R.string.result34));
                textViewResultText.setText(getText(R.string.result34Text));
                break;
            case 35:
                textViewResult.setText(getText(R.string.result35));
                textViewResultText.setText(getText(R.string.result35Text));
                break;
            case 36:
                textViewResult.setText(getText(R.string.result36));
                textViewResultText.setText(getText(R.string.result36Text));
                break;
            case 37:
                textViewResult.setText(getText(R.string.result37));
                textViewResultText.setText(getText(R.string.result37Text));
                break;
            case 38:
                textViewResult.setText(getText(R.string.result38));
                textViewResultText.setText(getText(R.string.result38Text));
                break;
            case 39:
                textViewResult.setText(getText(R.string.result39));
                textViewResultText.setText(getText(R.string.result39Text));
                break;
            case 40:
                textViewResult.setText(getText(R.string.result40));
                textViewResultText.setText(getText(R.string.result40Text));
                break;
            case 41:
                textViewResult.setText(getText(R.string.result41));
                textViewResultText.setText(getText(R.string.result41Text));
                break;
            case 42:
                textViewResult.setText(getText(R.string.result42));
                textViewResultText.setText(getText(R.string.result42Text));
                break;
            case 43:
                textViewResult.setText(getText(R.string.result43));
                textViewResultText.setText(getText(R.string.result43Text));
                break;
            case 44:
                textViewResult.setText(getText(R.string.result44));
                textViewResultText.setText(getText(R.string.result44Text));
                break;
            case 45:
                textViewResult.setText(getText(R.string.result45));
                textViewResultText.setText(getText(R.string.result45Text));
                break;
            case 46:
                textViewResult.setText(getText(R.string.result46));
                textViewResultText.setText(getText(R.string.result46Text));
                break;
            case 47:
                textViewResult.setText(getText(R.string.result47));
                textViewResultText.setText(getText(R.string.result47Text));
                break;
            case 48:
                textViewResult.setText(getText(R.string.result48));
                textViewResultText.setText(getText(R.string.result48Text));
                break;
            case 50:
                textViewResult.setText(getText(R.string.result50));
                textViewResultText.setText(getText(R.string.result50Text));
                break;
            default:
                textViewResult.setText(getText(R.string.result1));
                textViewResultText.setText(getText(R.string.result1Text));
                break;
        }
    }
}
