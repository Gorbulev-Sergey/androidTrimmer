package ru.gorbulevsv.androidtrimmer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    NumberPicker pickerProportion, pickerPetrolLiter, pickerPetrolMililiter;
    TextView textOil, textOilMililiters;
    String[] propotions = {"1/25", "1/30", "1/35", "1/40", "1/45", "1/50"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(R.string.app_description);

        textOil = findViewById(R.id.textOil);
        textOilMililiters = findViewById(R.id.textOilMililiters);
        textOil.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textOilMililiters.setText(String.valueOf(Math.round(oil()*1000)));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        pickerProportion = findViewById(R.id.pickerProportion);
        pickerProportion.setDisplayedValues(propotions);
        pickerProportion.setMinValue(0);
        pickerProportion.setMaxValue(propotions.length - 1);
        pickerProportion.setValue(0);
        pickerProportion.setOnValueChangedListener((numberPicker, i, value) -> textOil.setText(String.valueOf(oil())));

        pickerPetrolLiter = findViewById(R.id.pickerPetrolLiter);
        pickerPetrolLiter.setMinValue(0);
        pickerPetrolLiter.setMaxValue(100);
        pickerPetrolLiter.setValue(5);
        pickerPetrolLiter.setOnValueChangedListener((numberPicker, i, value) -> textOil.setText(String.valueOf(oil())));

        pickerPetrolMililiter = findViewById(R.id.pickerPetrolMililiter);
        pickerPetrolMililiter.setMinValue(0);
        pickerPetrolMililiter.setMaxValue(9);
        pickerPetrolMililiter.setOnValueChangedListener((numberPicker, i, value) -> textOil.setText(String.valueOf(oil())));

        textOil.setText(String.valueOf(oil()));
    }

    public Double oil() {
        Double petrol = Double.valueOf(pickerPetrolLiter.getValue()) + Double.valueOf("0." + pickerPetrolMililiter.getValue());
        Double x = Double.valueOf(String.valueOf(propotions[pickerProportion.getValue()]).replace("1/", ""));
        // ?????????????????? ???? 3 ???????????? ?????????? ??????????????
        return new BigDecimal(petrol / x).setScale(3, RoundingMode.HALF_EVEN).doubleValue();
    }
}