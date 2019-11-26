package com.example.emailapp;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class Email {

    //TODO change to date object
    public static ArrayList<Email> list = new ArrayList<Email>();
    public EmailDate date;
    public String subject;
    public String author;
    public String body;
    public int image;
    public int body_length;


    public Email(String d, String s, String a, String b, int R){


        date = new EmailDate(d);
        subject = s;
        author = a;
        body = b;
        body_length = b.length();
        image = R;

        list.add(this);

    }

    public static ArrayList<Email> getList(){
        return list;
    }

    public void deleteEmail(){
        list.remove(this);
    }


}

