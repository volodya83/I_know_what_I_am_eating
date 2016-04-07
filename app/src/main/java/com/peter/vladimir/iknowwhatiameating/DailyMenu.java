package com.peter.vladimir.iknowwhatiameating;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Volodya on 23-Feb-16.
 */
public class DailyMenu implements Serializable{

    private Date _date;
    private Meal _breakfast, _lunch, _dinner, _snack;
    private ArrayList<Meal> _meals;
//    private ArrayList<FoodItem> _other;
    private double _calories;
    private String str;

    public String toString(){
        str = str.concat(_date.toString() + "\n");
        str = str.concat(_breakfast.toString() + _lunch.toString() + _dinner.toString() + _snack.toString() );
        return str;
    }

    public String toStringMenu(){
        str = str.concat("\t\t\t\t\t\t\t\t\t\t" + _date.toString() + "\n");
        str = str.concat(_breakfast.toStringMenu() + _lunch.toStringMenu() + _dinner.toStringMenu() + _snack.toStringMenu() );
        // TODO: 08-Apr-16 daily calories

        return str;
    }

    public DailyMenu(){
        super();
        _meals = new ArrayList<Meal>(4);
        _breakfast = new Meal(0);
        _meals.add(0, _breakfast);
        _lunch = new Meal(1);
        _meals.add(1, _lunch);
        _dinner = new Meal(2);
        _meals.add(2, _dinner);
        _snack = new Meal(3);
        _meals.add(3, _snack);
        str = new String();
    }

    public DailyMenu(Date date){
        _date = date;
        str = _date.toString() + "\n";
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

    public ArrayList<Meal> get_meals(){
        return _meals;
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

    public double get_calories() {
        return _calories;
    }

    public void set_calories(double _calories) {
        this._calories = _calories;
    }

    public void set_date(java.sql.Date date){
        _date = date;
    }

    public Date get_date() {
        return _date;
    }
}
