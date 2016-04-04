package com.peter.vladimir.iknowwhatiameating;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import android.widget.LinearLayout.LayoutParams;


/**
 * Created by Volodya on 19-Mar-16.
 */
public class MyLinearLayout extends LinearLayout implements AdapterView.OnItemClickListener {
    AutoCompleteTextView _auto_tv;
    EditText _et_weight;
    TextView _tv_calories;
    double[] _calArr;
    double _calories;
    Context _context;
    int _arr_position;
    LinearLayout.LayoutParams params;
    public int _meal;

    public MyLinearLayout(Context context, ArrayAdapter adapter, double[] calArr, int meal) {
        super(context);

        this._context = context;
        this._calArr = calArr;
        this._meal = meal;
        this._arr_position = -1;

        params = new LayoutParams(0, -1);

        params.weight = 4;
        _auto_tv = new AutoCompleteTextView(context);
        _auto_tv.setAdapter(adapter);
        _auto_tv.setBackgroundColor(Color.WHITE);
        _auto_tv.setLayoutParams(params);
        _auto_tv.setOnItemClickListener(this);

        params = new LayoutParams(0, -1);
        params.setMargins(3, 0, 0, 0);
        params.weight =1;
        params.gravity = Gravity.CENTER;
        _et_weight = new EditText(context);
        _et_weight.setBackgroundColor(Color.WHITE);
        _et_weight.setLayoutParams(params);

        _tv_calories = new TextView(context);
        _tv_calories.setBackgroundColor(Color.parseColor("#ceedf4"));
        _tv_calories.setLayoutParams(params);

        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(0, 3, 0, 0);
        setLayoutParams(params);
        setOrientation(HORIZONTAL);
        addView(_auto_tv);
        addView(_et_weight);
        addView(_tv_calories);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        _arr_position = position;
//        Toast.makeText(_context, "position: "+position+" id: "+id+_calArr[position], Toast.LENGTH_SHORT).show();
        double cal = _calArr[position];
        setData("", 1, cal);
        ChangeDayMenu.fillCalories();
    }

    public void setData(String str, double weight, double calories) {
        if (str.length()>0){
            _auto_tv.setText(str);
        }
        _et_weight.setText(weight+"");
        _tv_calories.setText((_calories = calories*weight)+"");
    }
}
