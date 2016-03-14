package com.peter.vladimir.iknowwhatiameating;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ChangeDayMenu extends AppCompatActivity {

    private DatePicker datePicker;
    private LinearLayout ll_breakfast, ll_lunch, ll_dinner, ll_snack;
    private Button btn_add_line_breakfast, btn_add_line_lunch,
            btn_add_line_dinner, btn_add_line_snack;
    private ArrayList<AutoCompleteTextView> breakfast_list,
            lunch_list, dinner_list, snack_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_day_menu);

        datePicker = (DatePicker)findViewById(R.id.datePicker);

        ll_breakfast = (LinearLayout)findViewById(R.id.ll_breakfast);
        ll_lunch = (LinearLayout)findViewById(R.id.ll_lunch);
        ll_dinner = (LinearLayout)findViewById(R.id.ll_dinner);
        ll_snack = (LinearLayout)findViewById(R.id.ll_snack);

        btn_add_line_breakfast = (Button)findViewById(R.id.btn_add_line_breakfast);
        btn_add_line_lunch = (Button)findViewById(R.id.btn_add_line_lunch);
        btn_add_line_dinner = (Button)findViewById(R.id.btn_add_line_dinner);
        btn_add_line_snack = (Button)findViewById(R.id.btn_add_line_snack);

        breakfast_list.add(new AutoCompleteTextView(this));
        lunch_list.add(new AutoCompleteTextView(this));
    }
}
