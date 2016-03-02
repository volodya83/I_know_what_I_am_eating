package com.peter.vladimir.iknowwhatiameating;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

/**
 * Created by Volodya on 03-Mar-16.
 */
public class MyCursorAdapter extends CursorAdapter{
    private Context _context;

    public MyCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
        _context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }

    private class ViewHolderList{

    }
}
