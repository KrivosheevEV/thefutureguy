package ru.kev163rus.thefutureguy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RatingBar;
import android.widget.TextView;

import ru.kev163rus.thefutureguy.R;

public class RateAppActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_rate_app);

        TextView textViewRateAppText = (TextView) findViewById(R.id.textViewRateAppText);
        textViewRateAppText.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color3));
        textViewRateAppText.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        TextView textViewAgain = (TextView) findViewById(R.id.textViewAgain);
        textViewAgain.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color3));
        textViewAgain.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        TextView textViewExit = (TextView) findViewById(R.id.textViewExit);
        textViewExit.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color3));
        textViewExit.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        textViewAgain.setOnClickListener(this);
        textViewExit.setOnClickListener(this);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                ratingForApp();
            }});


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ratingBar:
                ratingForApp();
                finish();
                System.exit(0);
                break;
            case R.id.textViewAgain:
                finish();
                startActivity(new Intent(this, MenuActivity.class));
                break;
            case R.id.textViewExit:
                finish();
                System.exit(0);
                break;
        }
    }

    private void ratingForApp(){

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("market://details?id=ru.kev163rus.thefutureguy"));
        this.startActivity(i);

    }
}
