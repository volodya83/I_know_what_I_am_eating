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

    private static final int COL_MEAL = 2,COL_WEIGHT_RATIOS = 4, COL_ITEM_ID = 5, COL_NAME = 6, COL_WEIGHT = 7, COL_CALORIES = 8;
    private static final int BREAKFAST = 0, LUNCH = 1, DINNER = 2, SNACK = 3;
    private static Context _context;
    private static SQLiteDatabase _sqLiteDatabase;
    private static DB_Helper dbHelper;
    private static String[] _arg = new String[1];

    private final static String TABLE_FOOD_ITEMS = "FoodItems";
    private final static String TABLE_DAILY_MENU = "DailyMenu";
    private final static String TABLE_DAY_ITEMS = "DayItems";


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

    public static void deleteMenuItem(int day_id) {
        _arg[0] = day_id + "";
        _sqLiteDatabase.delete(TABLE_DAY_ITEMS, "day_id = ?", _arg);
    }

    public static void saveMenuItem(int day_id, int item_id, Double weight_rat, int meal) {
        ContentValues values = new ContentValues();
        values.put("day_id", day_id);
        values.put("meal", meal);
        values.put("item_id", item_id);
        values.put("weight_ratios", weight_rat);

        _sqLiteDatabase.insert(TABLE_DAY_ITEMS, null, values);
    }

    public static DailyMenu getDailyItems(int day_id) {
        DailyMenu menu = new DailyMenu();
        _arg[0] = String.valueOf(day_id);

        Cursor cursor = dayFoodItems(_arg);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                FoodItem item = new FoodItem(cursor.getString(COL_NAME), cursor.getDouble(COL_WEIGHT),
                        cursor.getDouble(COL_CALORIES), cursor.getDouble(COL_WEIGHT_RATIOS), cursor.getInt(COL_ITEM_ID));
                switch (cursor.getInt(COL_MEAL)) {
                    case BREAKFAST:
                        menu.get_breakfast().addFoodItem(item);
                        break;
                    case LUNCH:
                        menu.get_lunch().addFoodItem(item);
                        break;
                    case DINNER:
                        menu.get_dinner().addFoodItem(item);
                        break;
                    case SNACK:
                        menu.get_snack().addFoodItem(item);
                        break;
                }
                cursor.moveToNext();
            }
        }
        return menu;
    }

    private static Cursor dayFoodItems(String[] arg) {
        return _sqLiteDatabase.rawQuery("SELECT * " +
                                        "FROM DayItems, FoodItems " +
                                        "WHERE day_id = ? AND DayItems.day_id = FoodItems._id", arg);
    }

}