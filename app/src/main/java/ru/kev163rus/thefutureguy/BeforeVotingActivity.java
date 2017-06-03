package ru.kev163rus.thefutureguy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class BeforeVotingActivity extends Activity implements View.OnClickListener {

    private TextView textViewDialogBeforeVotingText, textViewDialogBeforeVotingYes, textViewDialogBeforeVotingNo;
    private EditText editTextUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_before_voting);

        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextUserName.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        editTextUserName.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewDialogBeforeVotingText = (TextView) findViewById(R.id.textViewDialogBeforeVotingText);
        textViewDialogBeforeVotingText.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewDialogBeforeVotingText.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewDialogBeforeVotingYes = (TextView) findViewById(R.id.textViewDialogBeforeVotingYes);
        textViewDialogBeforeVotingYes.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewDialogBeforeVotingYes.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewDialogBeforeVotingNo = (TextView) findViewById(R.id.textViewDialogBeforeVotingNo);
        textViewDialogBeforeVotingNo.setShadowLayer(7f, 25f, 15f, ContextCompat.getColor(this, R.color.color1));
        textViewDialogBeforeVotingNo.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/dsgoose.ttf"));

        textViewDialogBeforeVotingYes.setOnClickListener(this);
        textViewDialogBeforeVotingNo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.textViewDialogBeforeVotingYes:
                Questions.userName = editTextUserName.getText().toString();
                startActivity(new Intent(this, QuestionsActivity.class));
                finish();
                break;
            case R.id.textViewDialogBeforeVotingNo:
                finish();
                startActivity(new Intent(this, MenuActivity.class));
                break;
        }

    }
}
