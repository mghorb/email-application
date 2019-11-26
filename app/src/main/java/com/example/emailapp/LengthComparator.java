package com.example.emailapp;

import java.util.Comparator;

public class LengthComparator implements Comparator <Email> {

    @Override
    public int compare(Email e1, Email e2) {
        if(e1.body_length > e2.body_length){
            return -1;
        }
        else if(e1.body_length < e2.body_length){
            return 1;
        }
        else
            return 0;
    }
}
