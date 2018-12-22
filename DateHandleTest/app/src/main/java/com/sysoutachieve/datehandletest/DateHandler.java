package com.sysoutachieve.datehandletest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler {

    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat mDayFormat = new SimpleDateFormat("dd");
    SimpleDateFormat mYearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat mMonFormat = new SimpleDateFormat("MM");

    public Date stringToDate(String dateStr){
        Date date = null;
        try {
            date = mFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String dateToString(Date date){
        return mFormat.format(date);
    }

    public String dayToString(Date date){
        return mDayFormat.format(date);
    }
    public String yearToString(Date date){
        return mYearFormat.format(date);
    }
    public String monToString(Date date){
        return mMonFormat.format(date);
    }

}
