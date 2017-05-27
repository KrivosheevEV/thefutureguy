package ru.kev163rus.thefutureguy;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import ru.kev163rus.thefutureguy.R;

public class BeforeTestActivity extends Activity implements View.OnClickListener {

    private TextView textViewDialogBeforeTestText, textViewDialogBeforeTestYes, textViewDialogBeforeTestNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_before_test);

        textViewDialogBeforeTestText = (TextView) findViewById(R.id.textViewDialogBeforeTestText);
        textViewDialogBeforeTestText.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewDialogBeforeTestText.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewDialogBeforeTestYes = (TextView) findViewById(R.id.textViewDialogBeforeTestYes);
        textViewDialogBeforeTestYes.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewDialogBeforeTestYes.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewDialogBeforeTestNo = (TextView) findViewById(R.id.textViewDialogBeforeTestNo);
        textViewDialogBeforeTestNo.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewDialogBeforeTestNo.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewDialogBeforeTestYes.setOnClickListener(this);
        textViewDialogBeforeTestNo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.textViewDialogBeforeTestYes:
                startActivity(MenuActivity.activityQuestion);
                finish();
                break;
            case R.id.textViewDialogBeforeTestNo:
                startActivity(MenuActivity.activityMenu);
                finish();
                break;
        }

    }
}
