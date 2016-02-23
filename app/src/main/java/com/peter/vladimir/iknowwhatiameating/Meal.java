package com.peter.vladimir.iknowwhatiameating;

import java.util.ArrayList;

/**
 * Created by Volodya on 23-Feb-16.
 */
public class Meal {

    public static final int BREAKFAST = 1, LUNCH = 2, DINNER = 3, SNACK = 4;

    private int _mealType;
    private ArrayList<FoodItem> _foodList;
    //private double _mealCalories;

    public Meal(int mealType){
        this._mealType = mealType;
    }

    public void addFoodItem(FoodItem food){
        _foodList.add(food);
    }

    public double get_mealCalories(){
        double tmp = 0;
        if (!_foodList.isEmpty()){
            for (FoodItem foodItem:_foodList){
                tmp += foodItem.get_calories();
            }
        }
        return tmp;
    }

    public int get_mealType() {
        return _mealType;
    }

    public ArrayList<FoodItem> get_foodList(){
        return _foodList;
    }

    public void set_mealType(int _mealType) {
        this._mealType = _mealType;
    }
}