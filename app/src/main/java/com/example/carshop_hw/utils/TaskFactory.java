package com.example.carshop_hw.utils;

import com.example.carshop_hw.model.CarModel;
import java.util.ArrayList;
import java.util.List;

public class TaskFactory {
    public static CarModel getCarModel() {
        return new CarModel(
                TestUtils.getRandomPhoto(),
                TestUtils.getRandomBrand(),
                TestUtils.getRandomModel(),
                TestUtils.getRandomInteger(AppConstant.MIN_YEAR, AppConstant.MAX_YEAR),
                TestUtils.getRandomDescription(),
                TestUtils.getRandomInteger(1, 10) * 10000
        );
    }

    public static List<CarModel> getCarModels(int count) {
        ArrayList<CarModel> cars = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            cars.add(getCarModel());
        }

        return cars;
    }

    public static List<String> getCostsList(int min, int max) {
        List<String> costs = new ArrayList<>();

        for (int i = min; i <= max; i += 5000) {
            costs.add(String.valueOf(i));
        }

        return costs;
    }
}
