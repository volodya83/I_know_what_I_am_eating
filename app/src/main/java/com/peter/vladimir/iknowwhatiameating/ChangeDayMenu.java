package com.peter.vladimir.iknowwhatiameating;

import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class ChangeDayMenu extends AppCompatActivity implements View.OnClickListener {

    private static final int COL_NAME = 1, COL_WEIGHT = 2, COL_CALORIES = 3;
    private static final int COL_MEAL = 2, COL_ITEM = 3;
    private static final int BREAKFAST = 0, LUNCH = 1, DINNER = 2, SNACK = 3;

    private DatePicker datePicker;
    private LinearLayout ll_breakfast, ll_lunch, ll_dinner, ll_snack;
    private Button btn_add_line_breakfast, btn_add_line_lunch,
            btn_add_line_dinner, btn_add_line_snack;
    private TextView tv_total_cal_day, tv_cal_breakfast, tv_cal_lunch, tv_cal_dinner, tv_cal_snack;
    private ArrayList<MyLinearLayout> breakfast_list,
            lunch_list, dinner_list, snack_list;
    private ArrayAdapter<String> adapter;
    private Cursor cursor;
    private String[] arr_food_items;
    private double[] calArr;

    ArrayList<ArrayList<MyLinearLayout>> arr_lists;
    TextView[] tv_array;
    double cal[] = new double[4];
    double total_cal;
    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_day_menu);
        String date_str = getIntent().getExtras().getString("date");
        String _id = getIntent().getExtras().getString("_id");
        String[] arg = new String[1];
        arg[0] = _id;
        Date date = Date.valueOf(date_str);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        datePicker.init(date.getYear() + 1900, date.getMonth(), date.getDate(), null);

        tv_total_cal_day = (TextView) findViewById(R.id.tv_total_cal_day);
        tv_cal_breakfast = (TextView) findViewById(R.id.tv_calorie_breakfast);
        tv_cal_lunch = (TextView) findViewById(R.id.tv_calorie_lunch);
        tv_cal_dinner = (TextView) findViewById(R.id.tv_calorie_dinner);
        tv_cal_snack = (TextView) findViewById(R.id.tv_calorie_snack);

        ll_breakfast = (LinearLayout) findViewById(R.id.ll_breakfast);
        ll_lunch = (LinearLayout) findViewById(R.id.ll_lunch);
        ll_dinner = (LinearLayout) findViewById(R.id.ll_dinner);
        ll_snack = (LinearLayout) findViewById(R.id.ll_snack);

        btn_add_line_breakfast = (Button) findViewById(R.id.btn_add_line_breakfast);
        btn_add_line_breakfast.setOnClickListener(this);
        btn_add_line_lunch = (Button) findViewById(R.id.btn_add_line_lunch);
        btn_add_line_lunch.setOnClickListener(this);
        btn_add_line_dinner = (Button) findViewById(R.id.btn_add_line_dinner);
        btn_add_line_dinner.setOnClickListener(this);
        btn_add_line_snack = (Button) findViewById(R.id.btn_add_line_snack);
        btn_add_line_snack.setOnClickListener(this);

        createAdapter();
        createLists();
//        fillFilds(SQLfunctions.dayItems(arg));


    }

    private void fillFilds(Cursor cursor) {
        cursor.moveToFirst();
        int meal;
        MyLinearLayout myLayout;
        while (cursor.isAfterLast()) {
            meal = cursor.getInt(COL_MEAL);
            switch (meal) {
                case BREAKFAST:
                    myLayout = addLine(breakfast_list, ll_breakfast);
                    myLayout._auto_tv.setText(cursor.getString(COL_ITEM).toString());
                    tv_cal_breakfast.setText((cursor.getDouble(COL_CALORIES) + Double.valueOf(tv_cal_breakfast.getText().toString())) + "");
                    break;
                case LUNCH:
                    myLayout = addLine(lunch_list, ll_lunch);
                    myLayout._auto_tv.setText(cursor.getString(COL_ITEM).toString());
                    tv_cal_lunch.setText((cursor.getDouble(COL_CALORIES) + Double.valueOf(tv_cal_lunch.getText().toString())) + "");
                    break;
                case DINNER:
                    myLayout = addLine(dinner_list, ll_dinner);
                    myLayout._auto_tv.setText(cursor.getString(COL_ITEM).toString());
                    tv_cal_dinner.setText((cursor.getDouble(COL_CALORIES) + Double.valueOf(tv_cal_dinner.getText().toString())) + "");
                    break;
                case SNACK:
                    myLayout = addLine(snack_list, ll_snack);
                    myLayout._auto_tv.setText(cursor.getString(COL_ITEM).toString());
                    tv_cal_snack.setText((cursor.getDouble(COL_CALORIES) + Double.valueOf(tv_cal_breakfast.getText().toString())) + "");
                    break;
            }
        }
    }

    private void createLists() {
        breakfast_list = new ArrayList<MyLinearLayout>();
        lunch_list = new ArrayList<MyLinearLayout>();
        dinner_list = new ArrayList<MyLinearLayout>();
        snack_list = new ArrayList<MyLinearLayout>();

        arr_lists = new ArrayList<ArrayList<MyLinearLayout>>(4);
        arr_lists.add(0, breakfast_list);
        arr_lists.add(1, lunch_list);
        arr_lists.add(2, dinner_list);
        arr_lists.add(3, snack_list);
        tv_array = new TextView[4];
        tv_array[0] = tv_cal_breakfast;
        tv_array[1] = tv_cal_lunch;
        tv_array[2] = tv_cal_dinner;
        tv_array[3] = tv_cal_snack;
    }

    private void createAdapter() {
        cursor = SQLfunctions.getFoodItems();
        cursor.moveToFirst();
        int cnt = cursor.getCount();
        FoodItem item;
        arr_food_items = new String[cnt];
        calArr = new double[cnt];


        for (int i = 0; !cursor.isAfterLast(); i++) {
            item = new FoodItem(cursor.getString(COL_NAME), cursor.getInt(COL_WEIGHT), cursor.getDouble(COL_CALORIES));
            arr_food_items[i] = item.toString();
            calArr[i] = item.get_calories();
            cursor.moveToNext();
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arr_food_items);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_add_line_breakfast) {
            addLine(breakfast_list, ll_breakfast);
        } else if (v == btn_add_line_lunch) {
            addLine(lunch_list, ll_lunch);
        } else if (v == btn_add_line_dinner) {
            addLine(dinner_list, ll_dinner);
        } else {
            addLine(snack_list, ll_snack);
        }
    }


    private MyLinearLayout addLine(ArrayList<MyLinearLayout> list, LinearLayout layout) {

        MyLinearLayout myLayout = new MyLinearLayout(this, adapter, calArr);

        list.add(myLayout);
        layout.addView(myLayout);
        return myLayout;

    }


}
