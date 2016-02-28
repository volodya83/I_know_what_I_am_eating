package com.peter.vladimir.iknowwhatiameating;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Volodya on 23-Feb-16.
 */
public class DailyMenu {

    private Date _date;
    private Meal _breakfast, _lunch, _dinner, _snack;
    private ArrayList<FoodItem> _other;
    private double _calories;

    public DailyMenu(Date date){
        _date = date;
    }

    public Meal get_breakfast() {
        return _breakfast;
    }

    public void set_breakfast(Meal _breakfast) {
        this._breakfast = _breakfast;
    }

    public Meal get_lunch() {
        return _lunch;
    }

    public void set_lunch(Meal _lunch) {
        this._lunch = _lunch;
    }

    public Meal get_dinner() {
        return _dinner;
    }

    public void set_dinner(Meal _dinner) {
        this._dinner = _dinner;
    }

    public Meal get_snack() {
        return _snack;
    }

    public void set_snack(Meal _snack) {
        this._snack = _snack;
    }

    public ArrayList<FoodItem> get_other() {
        return _other;
    }

    public void add_other(FoodItem item) {
        this._other.add(item);
    }

    public double get_calories() {
        return _calories;
    }

    public void set_calories(double _calories) {
        this._calories = _calories;
    }

    public Date get_date() {
        return _date;
    }
}
