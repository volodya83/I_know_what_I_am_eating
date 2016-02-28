package com.peter.vladimir.iknowwhatiameating;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String CREATE_TABLE_FOOD_ITEMS = "CREATE TABLE FoodItems (" +
                                                "_id integer NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                                                "name text UNIQUE, " +
                                                "calories double, " +
                                                "weight integer " +
                                                "); ";
        final String CREATE_TABLE_DAILY_MENU = "CREATE TABLE DailyMenu ( " +
                                            "_id integer NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                                            "date date, " +
                                            "meal integer, " +
                                            "item integer, " +
                                            "num_of integer " +
                                            ")";

        sqLiteDatabase.execSQL(CREATE_TABLE_FOOD_ITEMS);
        sqLiteDatabase.execSQL(CREATE_TABLE_DAILY_MENU);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        //onUpgrade(db, 1, 1);
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS FoodItems ");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DailyMenu ");
        onCreate(sqLiteDatabase);
    }
}
