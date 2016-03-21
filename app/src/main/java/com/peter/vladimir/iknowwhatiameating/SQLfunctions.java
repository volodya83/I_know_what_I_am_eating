package com.peter.vladimir.iknowwhatiameating;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by Volodya on 28-Feb-16.
 */
public abstract class SQLfunctions {

    private static Context _context;
    private static SQLiteDatabase _sqLiteDatabase;
    private static DB_Helper dbHelper;

    private final static String TABLE_FOOD_ITEMS = "FoodItems";
    private final static String TABLE_DAILY_MENU = "DailyMenu";


    public static void setContext(Context context) {
        dbHelper = new DB_Helper(context);
        _context = context;
        _sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public static void addFoodItem(String name, double weight, double calories) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("weight", weight);
        contentValues.put("calories", calories);
        _sqLiteDatabase.insert(TABLE_FOOD_ITEMS, "", contentValues);
    }

    public static Cursor getFoodItems() {
        return _sqLiteDatabase.rawQuery("SELECT * " +
                                        "FROM FoodItems ", null);
    }

    public static void deleteFoodItem(String item) {
        _sqLiteDatabase.delete(TABLE_FOOD_ITEMS, "name='"+item+"'", null);
    }

    public static void addDays() {
        Cursor cursor = _sqLiteDatabase.rawQuery("SELECT MAX(date) " +
                                                "FROM DailyMenu ", null);
        cursor.moveToFirst();
        Date date = Date.valueOf(cursor.getString(0));
        Date cur_date = Date_Helper.addDays(new Date(System.currentTimeMillis()), 28);

        if (cur_date.compareTo(date)>0){
            while (cur_date.compareTo(date)>0){
                date = Date_Helper.addDays(date, 1);
                ContentValues values = new ContentValues();
                values.put("date", date.toString());
                _sqLiteDatabase.insert(TABLE_DAILY_MENU, "", values);
            }
        }
    }

    public static Cursor getDayMenu() {
        return _sqLiteDatabase.rawQuery("SELECT * " +
                                        "FROM DailyMenu", null);
    }

    public static Cursor dayItems(String[] arg) {
        return _sqLiteDatabase.rawQuery("SELECT * " +
                                        "FROM DayItems " +
                                        "WHERE day_id = ?", arg);
    }
}