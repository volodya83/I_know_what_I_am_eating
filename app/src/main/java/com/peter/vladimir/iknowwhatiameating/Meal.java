package com.peter.vladimir.iknowwhatiameating;

import java.util.ArrayList;

/**
 * Created by Volodya on 23-Feb-16.
 */
public class Meal {

    public static final int BREAKFAST = 0, LUNCH = 1, DINNER = 2, SNACK = 3;
    public static final String[] MEALS_NAME = {"BREAKFAST: \n", "LUNCH: \n", "DINNER: \n", "DINNER: \n"};

    private int _mealType;
    private String str;
    private ArrayList<FoodItem> _foodList;
    //private double _mealCalories;

    public Meal(int mealType){
        this._mealType = mealType;
        this.str = "\t" + MEALS_NAME[_mealType];
        this._foodList = new ArrayList<FoodItem>();
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

    public String toString(){
        for (FoodItem foodItem : _foodList){
            str = str.concat(foodItem.toString() + "\n");
        }
        str = str.concat("Calories to meal: " + get_mealCalories() + " kcal \n");
        return str;
    }

    public String toStringMenu(){
        for (FoodItem foodItem : _foodList){
            str = str.concat(foodItem.toStringMenu() + "\n");
        }
        str = str.concat("\t Calories to meal: " + get_mealCalories() + " kcal \n");
        return str;
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