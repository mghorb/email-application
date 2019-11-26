package com.example.emailapp;

import android.content.Context;
import java.util.ArrayList;


public class AssignmentEmail {

    public static void getEmails(Context context){

        // array of the rIDs for all the drawables of the contact pictures
        int[] rIdArray = {R.drawable.michaelgoldbloom, R.drawable.donaldtrump, R.drawable.hillaryclinton, R.drawable.arthurschopenhauer, R.drawable.alberteinstein, R.drawable.johnadams, R.drawable.mariecurie, R.drawable.richardfeynman, R.drawable.deniselauziere, R.drawable.winstonchurchill, R.drawable.martinluther, R.drawable.georgestpierre, R.drawable.selenagomez, R.drawable.richardfeynman, R.drawable.donaldtrump, R.drawable.malalala, R.drawable.franciscrick, R.drawable.alberteinstein, R.drawable.juliusceasar, R.drawable.mariecurie, R.drawable.isaacnewton};

        ArrayList<String[]> rawEmails = new ArrayList<>();
        rawEmails.add(context.getResources().getStringArray(R.array.email_0));
        rawEmails.add(context.getResources().getStringArray(R.array.email_1));
        rawEmails.add(context.getResources().getStringArray(R.array.email_2));
        rawEmails.add(context.getResources().getStringArray(R.array.email_3));
        rawEmails.add(context.getResources().getStringArray(R.array.email_4));
        rawEmails.add(context.getResources().getStringArray(R.array.email_5));
        rawEmails.add(context.getResources().getStringArray(R.array.email_6));
        rawEmails.add(context.getResources().getStringArray(R.array.email_7));
        rawEmails.add(context.getResources().getStringArray(R.array.email_8));
        rawEmails.add(context.getResources().getStringArray(R.array.email_9));
        rawEmails.add(context.getResources().getStringArray(R.array.email_10));
        rawEmails.add(context.getResources().getStringArray(R.array.email_11));
        rawEmails.add(context.getResources().getStringArray(R.array.email_12));
        rawEmails.add(context.getResources().getStringArray(R.array.email_13));
        rawEmails.add(context.getResources().getStringArray(R.array.email_14));
        rawEmails.add(context.getResources().getStringArray(R.array.email_15));
        rawEmails.add(context.getResources().getStringArray(R.array.email_16));
        rawEmails.add(context.getResources().getStringArray(R.array.email_17));
        rawEmails.add(context.getResources().getStringArray(R.array.email_18));
        rawEmails.add(context.getResources().getStringArray(R.array.email_19));
        rawEmails.add(context.getResources().getStringArray(R.array.email_20));

        ArrayList<Email> assignmentEmails = new ArrayList<>();

        int i = 0;
        for(String[] rawEmail: rawEmails) {
            // creates a new email, and adds it to the array
            assignmentEmails.add(new Email(rawEmail[0], rawEmail[1], rawEmail[2], rawEmail[3], rIdArray[i]));
            i++;
        }

        // sets the lisw of emails to Email.list static list
        Email.list = assignmentEmails;

    }

}
