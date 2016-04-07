package com.peter.vladimir.iknowwhatiameating;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;

/**
 * Created by Volodya on 26-Feb-16.
 */
public class DB_Helper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION=1;
    static  final String DATABASE_NAME="i_know.db";
    private Context _context;

    public DB_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        _context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_TABLE_FOOD_ITEMS = "CREATE TABLE FoodItems (" +
                                                "_id integer NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                                                "name text UNIQUE, " +
                                                "weight integer, " +
                                                "calories double " +
                                                "); ";
        final String CREATE_TABLE_DAILY_MENU = "CREATE TABLE DailyMenu ( " +
                                            "_id integer NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                                            "date text " +
                                            "); ";
        final String CREATE_TABLE_DAY_ITEM = "CREATE TABLE DayItems ( " +
                                            "_id integer NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                                            "day_id integer, " +
                                            "meal integer, " +
                                            "item_id integer, " +
                                            "weight_ratios double " +
                                            "); ";

        sqLiteDatabase.execSQL(CREATE_TABLE_FOOD_ITEMS);
        sqLiteDatabase.execSQL(CREATE_TABLE_DAILY_MENU);
        sqLiteDatabase.execSQL(CREATE_TABLE_DAY_ITEM);
        ContentValues values = new ContentValues();
        Date toDay = new Date(System.currentTimeMillis());
        values.put("date", toDay.toString());
//        Toast.makeText(_context, toDay.toString(), Toast.LENGTH_SHORT).show();
        sqLiteDatabase.insert("DailyMenu", "", values);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
//        onUpgrade(db, 1, 1);    // TODO: onUpgrade
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS FoodItems ");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DailyMenu ");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DayItems");
        onCreate(sqLiteDatabase);
    }
}
