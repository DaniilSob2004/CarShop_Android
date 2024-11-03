package com.example.carshop_hw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.carshop_hw.model.CarModel;
import com.example.carshop_hw.utils.AppConstant;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CarAdapter carAdapter;
    private ListView carsList;
    

    // регистрирует новый ActivityResultLauncher, можно использ. для запуска другой Activity и получения результата
    ActivityResultLauncher<Intent> mStartFromResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    List<CarModel> carModels = intent.getParcelableArrayListExtra(AppConstant.ACCESS_CARS_FROM_ACTIVITY);
                    if (carModels != null) {
                        carAdapter.updateCarModels(carModels);  // обновляем список
                    }
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // настройка адаптера коллекции машин
        setCarAdapter();
    }


    public void clickSearch(View view) {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        mStartFromResult.launch(intent);
    }

    public void clickReset(View view) {
        // сбрасываем все фильтры
        carAdapter.updateCarModels(new ArrayList<>(AppConstant.CARS_MODEL));
    }


    private void setCarAdapter() {
        // получаем элемент ListView
        carsList = findViewById(R.id.cars_list);

        // создаём адаптер
        carAdapter = new CarAdapter(this, R.layout.car_item, new ArrayList<>(AppConstant.CARS_MODEL));

        // устанавливаем адаптер в список
        carsList.setAdapter(carAdapter);
    }
}