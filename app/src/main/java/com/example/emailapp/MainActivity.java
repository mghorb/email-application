/**********************************
 * Names:
 * Christof Belise
 * Benjamin Collier
 * Manel Ghorbal
 *********************************/
package com.example.emailapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Notification;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import java.util.Timer;
import java.util.TimerTask;
import static androidx.core.app.NotificationCompat.PRIORITY_HIGH;
import static androidx.core.app.NotificationCompat.PRIORITY_LOW;
import static com.example.emailapp.App.CHANNEL_1_ID;

public class MainActivity extends AppCompatActivity {

    // recycler view, adapter, and layout
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    // determines whether to load emails from xml
    private static boolean getEmails = true;
    // notification manager for alert
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // if opening the app, load emails from database, & set it false for rest of session
        if (getEmails){
            AssignmentEmail.getEmails(this);
            getEmails = false;
        }

        //-------------------- RECYCLER VIEW --------------------//
        // set recycler view & adapter
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new RecyclerAdapter(Email.list);
        layoutManager = new LinearLayoutManager(this);
        // set layout & adapter
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        //-------------------- SET BACKGROUND --------------------//
        RelativeLayout back = findViewById(R.id.activity);
        // get the selected background preference from the activity
        SharedPreferences pref =  getApplicationContext().getSharedPreferences("MyPref",0);
        String background = pref.getString("key","Data Not Found");
        // switch case: sets background based on selection
        switch (background){
            case "Synth": back.setBackground(getResources().getDrawable(R.drawable.synth)); break;
            case "Night": back.setBackground(getResources().getDrawable(R.drawable.night)); break;
            case "Clouds": back.setBackground(getResources().getDrawable(R.drawable.clouds)); break;
            case "White": back.setBackground(null); break;
            default:back.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark)); break;
        }

        //-------------------- NOTIFICATION --------------------//
        notificationManager = NotificationManagerCompat.from(this);

        // create a timer, schedule notification for every x seconds
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // sends notification
                sendOnChannel1();

            }
        }, 0, 100000);   // 1000 Millisecond  = 1 second

    }

    // creates options menu
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // selection for options menu selection
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.pref:
                // start Settings Activity
                Intent intent = new Intent(this, SettingsActivity.class);
                this.startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // sends a notification
    public void sendOnChannel1(){
        String title = "You've Got Mail!";
        String message = "I wonder who its from...";

        Notification notification = new NotificationCompat.Builder(this,CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(title)
                .setContentText(message)
                .setPriority(PRIORITY_HIGH)
                .build();
        notificationManager.notify(1,notification);
    }

}
