package com.peter.vladimir.iknowwhatiameating;

/**
 * Created by Volodya on 23-Feb-16.
 */
public class FoodItem {
    private String _name;
    private double _calories;
    private double _weight;
    private String str;

    public FoodItem(String name, double weight, double calories){
        this._name = name;
        this._weight = weight;
        this._calories = calories;
    }

    public String toString(){
        str.concat(_name+"  "+_weight+" gr / "+_calories+" kcal /n");
        return str;
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

    public double get_weight() {
        return _weight;
    }

    public void set_weight(double _weight) {
        this._weight = _weight;
    }
}
