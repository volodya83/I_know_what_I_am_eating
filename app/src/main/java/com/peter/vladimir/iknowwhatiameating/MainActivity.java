package com.peter.vladimir.iknowwhatiameating;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView lst_day_menu;
    private MyCursorAdapter cursorAdapter;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SQLfunctions.setContext(getApplicationContext());
//        SQLfunctions.addDays();
//        cursor = SQLfunctions.getDayMenu();
//        lst_day_menu = (ListView)findViewById(R.id.lst_days);
//        cursorAdapter = new MyCursorAdapter(this, cursor, MyCursorAdapter.ID_DAY_ITEM);
//        lst_day_menu.setAdapter(cursorAdapter);
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
