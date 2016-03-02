package com.peter.vladimir.iknowwhatiameating;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
}
