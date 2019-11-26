package com.example.emailapp;

import java.util.Comparator;

public class AlphabetComparator implements Comparator<Email> {
    @Override
    public int compare(Email t0, Email t1) {
        return t0.author.compareTo(t1.author);
    }
}
