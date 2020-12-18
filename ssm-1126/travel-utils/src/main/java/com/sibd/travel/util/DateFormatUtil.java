package com.sibd.travel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
    public static String date2String(Date date,String patten){
        SimpleDateFormat sdf=new SimpleDateFormat(patten);
        return sdf.format(date);
    }
    public static Date string2Date(String date,String patten) {
        SimpleDateFormat sdf=new SimpleDateFormat(patten);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
