package com.peter.vladimir.iknowwhatiameating;

/**
 * Created by Volodya on 23-Feb-16.
 */
public class FoodItem {
    private String _name;
    private double _calories;
    private double _weight;
    private double _weight_ratio;
    private int _id;
    private String str;

    private static final String DELIMS = "[||/]";

    public FoodItem(String name, double weight, double calories){
        this._name = name;
        this._weight = weight;
        this._calories = calories;
        this._weight_ratio = 1;
    }

    public FoodItem(String name, double weight, double calories, double weight_ratio, int id){
        this(name,weight,calories);
        this._weight_ratio = weight_ratio;
        this._id = id;
    }

    public String toString(){
        str = _name+" {"+_weight+" gr / "+_calories+" kcal }";
        return str;
    }

    public String toStringMenu(){
        str = _name + " {" + _weight * _weight_ratio + " gr / " + _calories * _weight_ratio + " kcal }";
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

    public double get_weight_ratio() {
        return _weight_ratio;
    }

    public int get_id(){
        return _id;
    }
}
