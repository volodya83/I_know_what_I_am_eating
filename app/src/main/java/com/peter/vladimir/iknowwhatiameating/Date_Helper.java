package com.peter.vladimir.iknowwhatiameating;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by Volodya on 11-Mar-16.
 */
public abstract class Date_Helper {
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return new java.sql.Date(cal.getTime().getTime());
    }
}
