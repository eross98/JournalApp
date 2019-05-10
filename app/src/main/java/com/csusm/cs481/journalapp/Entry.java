package com.csusm.cs481.journalapp;

public class Entry {

    private int id;
    private String title;
    private String desc;
    private int day;
    private int month;
    private int year;
    private String cateogry;
    private String location;


    public Entry(int i,String t, String d, int da, int m, int y, String c/*, String loc*/){
        id=i;
        title=t;
        desc=d;
        day=da;
        month=m;
        year=y;
        cateogry=c;
        //location=loc;
    }

    public String getTitle(){return title;}

    public String getDesc(){return desc;}

    public String getCategory(){return cateogry;}

    public int getDay(){return day;}

    public int getMonth(){return month;}

    public int getYear(){return year;}

   // public String getLocation(){return location;}



}
