package com.example.emailapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewEmail extends AppCompatActivity {

    TextView textSubject, textAuthor, textBody, textDate;
    ImageView image;
    CardView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_view);
        Intent intent = getIntent();

        //-------------------- CARDVIEW & SETTING TEXT --------------------//
        cv = findViewById(R.id.cv);
        cv.setCardBackgroundColor(Color.TRANSPARENT);
        cv.setCardElevation(0);
        textSubject = findViewById(R.id.emailSubject);
        textAuthor = findViewById(R.id.emailAuthor);
        textBody = findViewById(R.id.emailBody);
        textDate = findViewById(R.id.emailDate);
        image = findViewById(R.id.emailPhoto);

        textSubject.setText(intent.getStringExtra("Subject"));
        textAuthor.setText(intent.getStringExtra("Author"));
        textBody.setText(intent.getStringExtra("Body"));
        textDate.setText(intent.getStringExtra("Date"));
        image.setImageResource(intent.getIntExtra("Image", 1));

        LinearLayout back = findViewById(R.id.email_view);

        //-------------------- SET BACKGROUND --------------------//
        SharedPreferences pref =  getApplicationContext().getSharedPreferences("MyPref",0);
        String background = pref.getString("key","Data Not Found");

        switch (background){
            case "Synth": back.setBackground(getResources().getDrawable(R.drawable.synth));
                textSubject.setTextColor(getResources().getColor(R.color.colorWhite));
                textAuthor.setTextColor(getResources().getColor(R.color.colorWhite));
                textBody.setTextColor(getResources().getColor(R.color.colorWhite));
                textDate.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
            case "Night": back.setBackground(getResources().getDrawable(R.drawable.night));
                textSubject.setTextColor(getResources().getColor(R.color.colorWhite));
                textAuthor.setTextColor(getResources().getColor(R.color.colorWhite));
                textBody.setTextColor(getResources().getColor(R.color.colorWhite));
                textDate.setTextColor(getResources().getColor(R.color.colorWhite));
                break;
            case "Clouds": back.setBackground(getResources().getDrawable(R.drawable.clouds));
                break;
            case "White": back.setBackground(null);
                break;
            default:back.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
        }

        //-------------------- BACK BUTTON --------------------//
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
       finish();
       return true;
    }

}
