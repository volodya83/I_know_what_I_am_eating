package com.peter.vladimir.iknowwhatiameating;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.sql.Date;

public class MainActivity extends AppCompatActivity {

    private static final int COL_DATE = 1;

    private ListView lst_day_menu;
    private MyCursorAdapter cursorAdapter;
    private Cursor cursor;
    private int position;
    private String cur_date, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLfunctions.setContext(getApplicationContext());
        cur_date = new Date(System.currentTimeMillis()).toString();
        cursor = SQLfunctions.getDayMenu();
        position = get_position(cursor);
        lst_day_menu = (ListView)findViewById(R.id.lst_days);
        cursorAdapter = new MyCursorAdapter(this, cursor, MyCursorAdapter.ID_DAY_ITEM);
        lst_day_menu.setAdapter(cursorAdapter);
        lst_day_menu.setSelection(position);
    }

    private int get_position(Cursor cursor) {
        cursor.moveToFirst();
        int position = 0;
        while (!cursor.isAfterLast() && position == 0){
            date = cursor.getString(COL_DATE);
            if (date.compareTo(cur_date) == 0){
                position = cursor.getPosition();
            }
            cursor.moveToNext();
        }
        return position;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.mbtn_update_list: {
                this.startActivity(new Intent(this, UpdateFoodItems.class));
                break;
            }
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
