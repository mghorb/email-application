package com.example.emailapp;

public class EmailDate {

    int month;
    int day;
    int year;
    String m;

    public EmailDate(String d) {

        String date[] = d.split(" ", 3);
        m = date[0];

        switch (m){
            case "January": month = 1; break;
            case "February": month = 2; break;
            case "March": month = 3; break;
            case "April": month = 4; break;
            case "May": month = 5; break;
            case "June": month = 6; break;
            case "July": month = 7; break;
            case "August": month = 8; break;
            case "September": month = 9; break;
            case "October": month = 10; break;
            case "November": month = 11; break;
            case "December": month = 12; break;
        }

        day = Integer.valueOf(date[1]);
        year = Integer.valueOf(date[2]);

    }

    int compareTo(EmailDate d2){
        if(this.year>d2.year)
            return 1;
        if(this.year<d2.year)
            return -1;
        if(this.year==d2.year){
            if (this.month>d2.month)
                return 1;
            if(this.month<d2.month)
                return -1;
            if(this.month == d2.month){
                if(this.day>d2.day)
                    return 1;
                if(this.day<d2.day)
                    return -1;
                if(this.day == d2.day)
                    return 0;
            }
        }
        return 0;
    }

    public String toString(){
        return day + " " + m + " "  + year;
    }

}


