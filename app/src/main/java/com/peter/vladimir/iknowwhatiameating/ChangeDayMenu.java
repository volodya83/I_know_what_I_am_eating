package com.peter.vladimir.iknowwhatiameating;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;

public class ChangeDayMenu extends AppCompatActivity implements View.OnClickListener {

    private static final int COL_ID = 0, COL_NAME = 1, COL_WEIGHT = 2, COL_CALORIES = 3;
    private static final int COL_MEAL = 2, COL_ITEM = 3;
    private static final int BREAKFAST = 0, LUNCH = 1, DINNER = 2, SNACK = 3;

    private DatePicker datePicker;
    private LinearLayout ll_breakfast, ll_lunch, ll_dinner, ll_snack;
    private Button btn_add_line_breakfast, btn_add_line_lunch,
            btn_add_line_dinner, btn_add_line_snack, btn_save, btn_clear;
    private static TextView tv_total_cal_day, tv_cal_breakfast, tv_cal_lunch, tv_cal_dinner, tv_cal_snack;
    private static ArrayList<MyLinearLayout> breakfast_list,
            lunch_list, dinner_list, snack_list;
    private ArrayAdapter<String> adapter;
    private Cursor cursor;
    private String[] arr_food_items;
    private double[] calArr;
    private int[] arr_ids;

    private Date date;
    private Meal meal;
    private FoodItem item;
    private ArrayList<FoodItem> list;
    private MyLinearLayout layout;

    ArrayList<ArrayList<MyLinearLayout>> arr_lists;
//    TextView[] tv_array;
    private double cal[] = new double[4];
    private double total_cal;
    private int size;
    private int _day_id;
    private ArrayList<LinearLayout> ll_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_day_menu);
        String date_str = getIntent().getExtras().getString("date");
        String day_id = String.valueOf((_day_id = getIntent().getExtras().getInt("_id")));
        String[] arg = new String[1];
        arg[0] = day_id;
        date = Date.valueOf(date_str);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        datePicker.init(date.getYear() + 1900, date.getMonth(), date.getDate(), null);

        tv_total_cal_day = (TextView) findViewById(R.id.tv_total_cal_day);
        tv_cal_breakfast = (TextView) findViewById(R.id.tv_calorie_breakfast);
        tv_cal_lunch = (TextView) findViewById(R.id.tv_calorie_lunch);
        tv_cal_dinner = (TextView) findViewById(R.id.tv_calorie_dinner);
        tv_cal_snack = (TextView) findViewById(R.id.tv_calorie_snack);

        ll_arr = new ArrayList<LinearLayout>(4);
        ll_breakfast = (LinearLayout) findViewById(R.id.ll_breakfast);
        ll_arr.add(0, ll_breakfast);
        ll_lunch = (LinearLayout) findViewById(R.id.ll_lunch);
        ll_arr.add(1, ll_lunch);
        ll_dinner = (LinearLayout) findViewById(R.id.ll_dinner);
        ll_arr.add(2, ll_dinner);
        ll_snack = (LinearLayout) findViewById(R.id.ll_snack);
        ll_arr.add(3, ll_snack);

        btn_add_line_breakfast = (Button) findViewById(R.id.btn_add_line_breakfast);
        btn_add_line_breakfast.setOnClickListener(this);
        btn_add_line_lunch = (Button) findViewById(R.id.btn_add_line_lunch);
        btn_add_line_lunch.setOnClickListener(this);
        btn_add_line_dinner = (Button) findViewById(R.id.btn_add_line_dinner);
        btn_add_line_dinner.setOnClickListener(this);
        btn_add_line_snack = (Button) findViewById(R.id.btn_add_line_snack);
        btn_add_line_snack.setOnClickListener(this);
        btn_save = (Button)findViewById(R.id.btn_save_menu);
        btn_save.setOnClickListener(this);
        btn_clear = (Button)findViewById(R.id.btn_clear_menu);
        btn_clear.setOnClickListener(this);

        createAdapter();
        createLists();
        fillFilds(SQLfunctions.getDailyItems(_day_id));
    }

    public static void fillCalories(){
        double cal = 0;
        tv_cal_breakfast.setText((cal += getSumOfCal(breakfast_list))+"");
        tv_cal_lunch.setText((cal += getSumOfCal(lunch_list))+"");
        tv_cal_dinner.setText((cal += getSumOfCal(dinner_list))+"");
        tv_cal_snack.setText((cal += getSumOfCal(snack_list))+"");
        tv_total_cal_day.setText(cal + "");
    }

    private static double getSumOfCal(ArrayList<MyLinearLayout> list) {
        double sum = 0;
        for (int i=0; i<list.size(); i++){
            sum +=list.get(i)._calories;
        }
        return sum;
    }

    private void fillFilds(DailyMenu menu) {
        if (menu!=null) {
            menu.set_date(date);

            for (int i = 0; i < 4; i++){
                meal = menu.get_meals().get(i);
                list = meal.get_foodList();
                for (int j=0; j < list.size(); j++){
                    item = list.get(j);
                    layout = addLine(arr_lists.get(i), ll_arr.get(i), i);
                    layout.setData(item.toString(), item.get_weight_ratio(), item.get_calories(), item.get_id());
                }
            }
            fillCalories();
        }else {
            Toast.makeText(this, "menu is null" , Toast.LENGTH_SHORT).show();
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
    }

    private void createAdapter() {
        cursor = SQLfunctions.getFoodItems();
        cursor.moveToFirst();
        int cnt = cursor.getCount();
        FoodItem item;
        arr_food_items = new String[cnt];
        calArr = new double[cnt];
        arr_ids = new int[cnt];


        for (int i = 0; !cursor.isAfterLast(); i++) {
            item = new FoodItem(cursor.getString(COL_NAME), cursor.getInt(COL_WEIGHT), cursor.getDouble(COL_CALORIES));
            arr_food_items[i] = item.toString();
            calArr[i] = item.get_calories();
            arr_ids[i] = cursor.getInt(COL_ID);
            cursor.moveToNext();
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arr_food_items);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_add_line_breakfast) {
            addLine(breakfast_list, ll_breakfast, 0);
        } else if (v == btn_add_line_lunch) {
            addLine(lunch_list, ll_lunch, 1);
        } else if (v == btn_add_line_dinner) {
            addLine(dinner_list, ll_dinner, 2);
        } else if (v == btn_add_line_snack){
            addLine(snack_list, ll_snack, 3);
        } else if (v == btn_save){
            saveMenu();
        } else if (v == btn_clear){
            clearMenu();
        }
    }

    private void clearMenu() {
        // TODO: 21-Mar-16
    }

    private void saveMenu() {
        ArrayList<MyLinearLayout> list;
        int size;
        int item_id;
        MyLinearLayout layout;
        SQLfunctions.deleteMenuItem(_day_id);
        for (int i = 0; i<4; i++){
            list = arr_lists.get(i);
            size = list.size();
            for (int j = 0; j < size; j++){
                layout = list.get(j);
                if (layout._auto_tv.getText().length() > 1) {
                    if (layout._arr_position > 0) {
                        item_id = arr_ids[layout._arr_position];
                    } else {
                        item_id = layout._item_id;
                    }
                    SQLfunctions.saveMenuItem(_day_id, item_id, Double.valueOf(layout._et_weight.getText().toString()), layout._meal);
                }
            }
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    private MyLinearLayout addLine(ArrayList<MyLinearLayout> list, LinearLayout layout, int meal) {

        MyLinearLayout myLayout = new MyLinearLayout(this, adapter, calArr, meal);

        list.add(myLayout);
        layout.addView(myLayout);
        return myLayout;

    }


}
