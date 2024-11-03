package com.example.carshop_hw.utils;

import com.example.carshop_hw.model.CarModel;

import java.util.List;
import java.util.stream.Collectors;

public class CarRespository {

    // получение годов
    public static List<String> getYearsList() {
        return AppConstant.CARS_MODEL
                .stream()
                .map(carModel -> String.valueOf(carModel.getYear()))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    // поиск
    public static List<CarModel> search(String brand, String model, int fromYear, int toYear, int fromCost, int toCost) {
        return AppConstant.CARS_MODEL
                .stream()
                .filter(car ->
                    ((!brand.isEmpty() && car.getBrand().trim().contains(brand)) ||
                    (!model.isEmpty() && car.getModel().trim().contains(model))) &&
                    (car.getYear() >= fromYear && car.getYear() <= toYear) &&
                    (car.getCost() >= fromCost && car.getCost() <= toCost)
                )
                .collect(Collectors.toList());
    }
}
