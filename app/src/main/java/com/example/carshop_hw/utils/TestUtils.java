package com.example.carshop_hw.utils;

import com.example.carshop_hw.R;
import java.util.Random;

public class TestUtils {
    private static final Random RAND = new Random();
    private static final int MIN_INT = 1;
    private static final int MAX_INT = 100_000_000;

    public static final String[] BRANDS = {
            "Toyota", "Ford", "Chevrolet", "Honda", "Nissan",
            "BMW", "Mercedes-Benz", "Volkswagen", "Audi", "Hyundai",
            "Kia", "Mazda", "Subaru", "Lexus", "Jeep"
    };
    public static final String[] MODELS = {
            "Camry", "F-150", "Silverado", "Civic", "Altima",
            "X5", "C-Class", "Golf", "A4", "Elantra",
            "Sorento", "CX-5", "Outback", "RX", "Wrangler"
    };
    private static final String[] DESCRIPTIONS = {
            "комфортный", "быстрый", "экономичный", "мощный", "стильный",
            "надежный", "просторный", "удобный", "спортивный", "экологичный",
            "компактный", "современный", "безопасный", "маневренный", "привлекательный",
            "роскошный", "инновационный", "динамичный", "вместительный", "эффективный"
    };
    private static final int[] PHOTOS = {
            R.drawable.car1, R.drawable.car2, R.drawable.car3, R.drawable.car4,
            R.drawable.car5, R.drawable.car6, R.drawable.car7, R.drawable.car8,
            R.drawable.car9, R.drawable.car10
    };


    public static String getRandomBrand() {
        return BRANDS[RAND.nextInt(BRANDS.length)];
    }

    public static String getRandomModel() {
        return MODELS[RAND.nextInt(MODELS.length)];
    }

    public static String getRandomDescription() {
        StringBuilder sb = new StringBuilder();

        int len = getRandomInteger(5, 15);
        for (int i = 0; i < len; i++) {
            sb.append(DESCRIPTIONS[RAND.nextInt(DESCRIPTIONS.length)]);
            if (i < len - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    public static int getRandomPhoto() {
        return PHOTOS[RAND.nextInt(PHOTOS.length)];
    }


    public static int getRandomInteger() {
        return getRandomInteger(MIN_INT, MAX_INT);
    }

    public static int getRandomInteger(int min) {
        return getRandomInteger(min, MAX_INT);
    }

    public static int getRandomInteger(final int min, final int max) {
        return RAND.nextInt((max - min) + 1) + min;
    }
}
