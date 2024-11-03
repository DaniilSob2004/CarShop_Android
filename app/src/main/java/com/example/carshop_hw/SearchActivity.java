package com.example.carshop_hw;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.carshop_hw.model.CarModel;
import com.example.carshop_hw.utils.AppConstant;
import com.example.carshop_hw.utils.CarRespository;
import com.example.carshop_hw.utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private List<CarModel> findCarModels;

    private AutoCompleteTextView brandAutoTextView;
    private AutoCompleteTextView modelAutoTextView;
    private Spinner yearFromSpinner;
    private Spinner yearToSpinner;
    private Spinner costFromSpinner;
    private Spinner costToSpinner;
    private Button matchesBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // объект, управляет обратными вызовами (callbacks) при нажатии кнопки "Назад"
        OnBackPressedDispatcher onBackPressedDispatcher = getOnBackPressedDispatcher();
        onBackPressedDispatcher.addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() { finish(); }
        });

        // находим виджеты
        init();

        // настройка списка brands и models
        setBrandsView();
        setModelsView();

        // настройка spinner по году
        List<String> years = CarRespository.getYearsList();
        setSpinnerValues(yearFromSpinner, years, false);
        setSpinnerValues(yearToSpinner, years, true);

        // настройка spinner по цене
        List<String> costs = AppConstant.COSTS;
        setSpinnerValues(costFromSpinner, costs, false);
        setSpinnerValues(costToSpinner, costs, true);

        // настраиваем слушатели для поиска
        setupListeners();
    }

    public void clickBack(View view) {
        finish();
    }

    public void clickMatches(View view) {
        // устанавливаем результат и завершаем SearchActivity
        Intent resultIntent = new Intent();
        resultIntent.putParcelableArrayListExtra(AppConstant.ACCESS_CARS_FROM_ACTIVITY, new ArrayList<>(findCarModels));
        setResult(RESULT_OK, resultIntent);
        finish();
    }


    private void setupListeners() {
        // TextWatcher для AutoCompleteTextViews
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                performSearch();  // поиск
            }

            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override public void afterTextChanged(Editable s) {}
        };

        brandAutoTextView.addTextChangedListener(textWatcher);
        modelAutoTextView.addTextChangedListener(textWatcher);

        // OnItemSelectedListener для Spinners
        yearFromSpinner.setOnItemSelectedListener(onSpinnerItemSelected);
        yearToSpinner.setOnItemSelectedListener(onSpinnerItemSelected);
        costFromSpinner.setOnItemSelectedListener(onSpinnerItemSelected);
        costToSpinner.setOnItemSelectedListener(onSpinnerItemSelected);
    }

    private final AdapterView.OnItemSelectedListener onSpinnerItemSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            performSearch();  // поиск
        }

        @Override public void onNothingSelected(AdapterView<?> parent) {}
    };

    private void performSearch() {
        String brand = brandAutoTextView.getText().toString();
        String model = modelAutoTextView.getText().toString();
        int fromYear = Integer.parseInt(yearFromSpinner.getSelectedItem().toString());
        int toYear = Integer.parseInt(yearToSpinner.getSelectedItem().toString());
        int fromCost = Integer.parseInt(costFromSpinner.getSelectedItem().toString());
        int toCost = Integer.parseInt(costToSpinner.getSelectedItem().toString());

        findCarModels = CarRespository.search(brand, model, fromYear, toYear, fromCost, toCost);

        // настраиваем кнопку
        int size = findCarModels.size();
        matchesBtn.setText(size + " matches");
        matchesBtn.setEnabled(size > 0);
    }


    private void init() {
        brandAutoTextView = findViewById(R.id.brand_auto_text_view);
        modelAutoTextView = findViewById(R.id.model_auto_text_view);
        yearFromSpinner = findViewById(R.id.year_from_spinner);
        yearToSpinner = findViewById(R.id.year_to_spinner);
        costFromSpinner = findViewById(R.id.cost_from_spinner);
        costToSpinner = findViewById(R.id.cost_to_spinner);
        matchesBtn = findViewById(R.id.matches_btn);
    }

    private void setBrandsView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, TestUtils.BRANDS);
        brandAutoTextView.setAdapter(adapter);
        brandAutoTextView.setThreshold(1);  // начинать предлагать варианты после ввода 1 символа
    }

    private void setModelsView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, TestUtils.MODELS);
        modelAutoTextView.setAdapter(adapter);
        modelAutoTextView.setThreshold(1);  // начинать предлагать варианты после ввода 1 символа
    }

    private void setSpinnerValues(Spinner spinner, List<String> list, boolean isSetLast) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                list
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        if (isSetLast) {
            int lastIndex = adapter.getCount() - 1;
            spinner.setSelection(lastIndex);
        }
    }
}