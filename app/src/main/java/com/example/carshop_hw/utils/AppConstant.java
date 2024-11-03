package com.example.carshop_hw.utils;

import com.example.carshop_hw.model.CarModel;
import java.util.List;

public class AppConstant {
    public static final String ACCESS_CARS_FROM_ACTIVITY = "carModels";

    public static final int MIN_YEAR = 1950;
    public static final int MAX_YEAR = 2024;
    public static final int MIN_COST = 10000;
    public static final int MAX_COST = 100000;
    public static final int COUNT_CARS = 20;
    public static final List<String> COSTS;
    public static final List<CarModel> CARS_MODEL;

    static {
        CARS_MODEL = TaskFactory.getCarModels(COUNT_CARS);
        COSTS = TaskFactory.getCostsList(MIN_COST, MAX_COST);
    }
}
