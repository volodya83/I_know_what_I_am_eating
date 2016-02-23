package com.peter.vladimir.iknowwhatiameating;

/**
 * Created by Volodya on 23-Feb-16.
 */
public class FoodItem {
    private String _name;
    private double _calories;
    private String _weight;

    public FoodItem(String name, double calories){
        this._name = name;
        this._calories = calories;
    }


    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public double get_calories() {
        return _calories;
    }

    public void set_calories(double _calories) {
        this._calories = _calories;
    }

    public String get_weight() {
        return _weight;
    }

    public void set_weight(String _weight) {
        this._weight = _weight;
    }
}
