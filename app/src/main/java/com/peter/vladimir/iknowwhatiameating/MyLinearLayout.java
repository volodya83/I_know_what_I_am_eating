package com.peter.vladimir.iknowwhatiameating;

import android.content.Context;
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
    Context _context;
    LinearLayout.LayoutParams params;

    public MyLinearLayout(Context context, ArrayAdapter adapter, double[] calArr) {
        super(context);

        this._context = context;
        this._calArr = calArr;

        params.weight = 3;
        _auto_tv = new AutoCompleteTextView(context);
        _auto_tv.setAdapter(adapter);
        _auto_tv.setBackgroundColor(Color.WHITE);
        _auto_tv.setLayoutParams(params);
        _auto_tv.setOnItemClickListener(this);

        params.weight =
        _et_weight = new EditText(context);
        _et_weight.setBackgroundColor(Color.WHITE);
        _tv_calories = new TextView(context);

        setOrientation(HORIZONTAL);
        addView(_auto_tv);
        addView(_et_weight);
        addView(_tv_calories);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(_context, "position: "+position+" id: "+id+_calArr[position], Toast.LENGTH_SHORT).show();
        double cal = _calArr[position];
        setData("", 1, cal);
    }

    public void setData(String str, int weight, double calories) {
        if (str.length()>0){
            _auto_tv.setText(str);
        }
        _et_weight.setText(weight+"");
        _tv_calories.setText((calories*weight)+"");
    }
}
