package com.example.emailapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    // comparators
    private DateComparator aDate = new DateComparator();
    private LengthComparator aLength = new LengthComparator();
    private AlphabetComparator aSorter = new AlphabetComparator();
    // sort buttons
    private RadioGroup sortGroup;
    private RadioButton alpha, date, length;
    private Button sortbutton;
    // background buttons
    private RadioGroup bgGroup;
    private RadioButton synth,night,white,clouds;
    private Button background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();

        //-------------------- BACK BUTTON --------------------//
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //-------------------- SORTING --------------------//
        sortGroup = findViewById(R.id.myRadioGroup);
        // sends toast based on what is selected
        sortGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.sortAlpha) {
                    Toast.makeText(getApplicationContext(), "choice: by Alphabetical",
                            Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.sortDate) {
                    Toast.makeText(getApplicationContext(), "choice: by Date",
                            Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.sortLength) {
                    Toast.makeText(getApplicationContext(), "choice: by Length",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });

        alpha = findViewById(R.id.sortAlpha);
        date = findViewById(R.id.sortDate);
        length = findViewById(R.id.sortLength);
        // set sort button listener to sort emails in desired way
        sortbutton = findViewById(R.id.sortbutton);
        sortbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = sortGroup.getCheckedRadioButtonId();
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                // find which radioButton is checked by id
                if (selectedId == alpha.getId()) {
                    Email.list.sort(aSorter);
                } else if (selectedId == date.getId()) {
                    Email.list.sort(aDate);
                } else {
                    Email.list.sort(aLength);
                }
                finish();
                startActivity(intent);
            }
        });


        //-------------------- BACKGROUND SETTING --------------------//
        bgGroup = findViewById(R.id.backgroundRadioGroup);
        // sends toast based on choice
        bgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.synthB) {
                    Toast.makeText(getApplicationContext(), "choice: Synthwave",
                            Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.nightB) {
                    Toast.makeText(getApplicationContext(), "choice: Night",
                            Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.cloudsB) {
                    Toast.makeText(getApplicationContext(), "choice: Cloudy",
                            Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.whiteB) {
                    Toast.makeText(getApplicationContext(), "choice: White",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });

        synth = findViewById(R.id.synthB);
        night = findViewById(R.id.nightB);
        clouds = findViewById(R.id.cloudsB);
        white = findViewById(R.id.whiteB);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",0);
        final SharedPreferences.Editor editor = pref.edit();
        // add listener to button to set backgrounf
        background = findViewById(R.id.setB);
        background.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                int selectedId = bgGroup.getCheckedRadioButtonId();
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                if (selectedId == synth.getId()) {
                    editor.putString("key","Synth");
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",0);
                    editor.apply();
                } else if (selectedId == night.getId()) {
                    editor.putString("key","Night");
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",0);
                    editor.apply();
                } else if (selectedId == clouds.getId()) {
                    editor.putString("key","Clouds");
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",0);
                    editor.apply();
                } else if (selectedId == white.getId()) {
                    editor.putString("key","White");
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref",0);
                    editor.apply();
                }
                finish();
                startActivity(intent);
            }
        });
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }

    }

   public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

}