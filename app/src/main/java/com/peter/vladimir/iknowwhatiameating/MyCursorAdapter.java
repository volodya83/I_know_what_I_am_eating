package com.peter.vladimir.iknowwhatiameating;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Volodya on 03-Mar-16.
 */
public class MyCursorAdapter extends CursorAdapter{
    private static final int COL_ID = 0, COL_DATE = 1;
    private Context _context;
    private int _resource;
    public static final int ID_FOOD_ITEM_LIST = R.layout.fooditem_list_item;
    public static final int ID_DAY_ITEM = R.layout.day_item;
    private final int COL_NAME = 1, COL_WEIGHT = 2, COL_CALORIES = 3;

    public MyCursorAdapter(Context context, Cursor cursor, int resource) {
        super(context, cursor, resource);
        _context = context;
        _resource = resource;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(_resource, parent, false);
        if (_resource == ID_FOOD_ITEM_LIST) {
            ViewHolderList viewHolder = new ViewHolderList(view);
            view.setTag(viewHolder);
        }else {
            ViewHolderListDayMenu viewHolder = new ViewHolderListDayMenu(view);
            view.setTag(viewHolder);
        }
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (_resource == ID_FOOD_ITEM_LIST) {
            ViewHolderList viewHolder = (ViewHolderList) view.getTag();
            String name = cursor.getString(COL_NAME);
            viewHolder.tv_name.setText(name);
            String weight = cursor.getString(COL_WEIGHT);
            viewHolder.tv_weight.setText(weight + "gr");
            String calories = cursor.getString(COL_CALORIES);
            viewHolder.tv_calories.setText(calories + "kcal");
        }else {
            ViewHolderListDayMenu viewHolder = (ViewHolderListDayMenu)view.getTag();
            DailyMenu menu = SQLfunctions.getDailyItems(viewHolder._id = cursor.getInt(COL_ID));
            viewHolder.date = cursor.getString(COL_DATE);
            menu.set_date(Date.valueOf(viewHolder.date));
//            viewHolder.menu = menu;
//            viewHolder._id = cursor.getInt(COL_ID);
            viewHolder.tv_menu.setText(menu.toString());
        }
    }

    private class ViewHolderList{
        protected TextView tv_name, tv_weight, tv_calories;
        protected ImageButton ibtn_delete;

        public ViewHolderList(View view) {
            tv_name = (TextView)view.findViewById(R.id.tv_name);
            tv_weight = (TextView)view.findViewById(R.id.tv_weight);
            tv_calories = (TextView)view.findViewById(R.id.tv_calories);
            ibtn_delete = (ImageButton)view.findViewById(R.id.ibtn_delete);
            ibtn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLfunctions.deleteFoodItem(tv_name.getText().toString());
                    UpdateFoodItems.refreshList();
                }
            });
        }
    }

    private class ViewHolderListDayMenu {
        protected TextView tv_menu;
        protected Button btn_change;
        protected String date;
//        protected DailyMenu menu;
        protected int _id;

        public ViewHolderListDayMenu(View view) {
            tv_menu = (TextView)view.findViewById(R.id.tv_day_menu);
            btn_change = (Button)view.findViewById(R.id.btn_change);
            btn_change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(_context, ChangeDayMenu.class);
                    intent.putExtra("date", date);
                    intent.putExtra("_id", _id);
//                    intent.putExtra("menu", (Serializable)menu);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    _context.startActivity(intent);
                }
            });
        }
    }
}
