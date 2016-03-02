package com.peter.vladimir.iknowwhatiameating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class UpdateFoodItems extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name, et_weight, et_calories;
    private Button btn_add, btn_clear;
    private ListView lst_foodList;
    private String name;
    private double weight, calories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_food_items);

        et_name = (EditText) findViewById(R.id.et_item_name);
        et_weight = (EditText) findViewById(R.id.et_weight);
        et_calories = (EditText) findViewById(R.id.et_calories);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(this);
        lst_foodList = (ListView) findViewById(R.id.lst_foodList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_food_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_clear) {
            et_name.setText("");
        } else if (v == btn_add) {
            name = et_name.getText().toString();
            weight = Double.parseDouble(et_weight.getText().toString());
            calories = Double.parseDouble(et_calories.getText().toString());
            if (name.length() < 5) {
                Toast.makeText(this, "Name must contain more than 5 letters", Toast.LENGTH_SHORT).show();
            } else if (weight < 0) {
                Toast.makeText(this, "Weight must be a number", Toast.LENGTH_SHORT).show();
            } else if (calories < 1) {
                Toast.makeText(this, "Write calories", Toast.LENGTH_SHORT).show();
            } else {
                SQLfunctions.addFoodItem(name, weight, calories);
            }
        }
    }
}