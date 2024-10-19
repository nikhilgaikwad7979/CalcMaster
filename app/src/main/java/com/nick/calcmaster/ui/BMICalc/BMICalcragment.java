package com.nick.calcmaster.ui.BMICalc;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nick.calcmaster.R;

public class BMICalcragment extends Fragment {

    private BMICalcragmentViewModel mViewModel;

    private EditText weightInput;
    private EditText heightInput;
    private EditText ageInput;
    private Button calculateButton;
    private TextView resultTextView;

    public static BMICalcragment newInstance() {
        return new BMICalcragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b_m_i_calcragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(BMICalcragmentViewModel.class);

        weightInput = getView().findViewById(R.id.weightInput);
        heightInput = getView().findViewById(R.id.heightInput);
        ageInput = getView().findViewById(R.id.ageInput);
        calculateButton = getView().findViewById(R.id.calculateButton);
        resultTextView = getView().findViewById(R.id.resultTextView1);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = weightInput.getText().toString();
        String heightStr = heightInput.getText().toString();
        String ageStr = ageInput.getText().toString();

        if (!weightStr.isEmpty() && !heightStr.isEmpty() && !ageStr.isEmpty()) {
            double weight = Double.parseDouble(weightStr);
            double height = Double.parseDouble(heightStr) / 100; // Convert cm to meters
            double bmi = weight / (height * height);
            resultTextView.setText(String.format("Your BMI: %.2f", bmi));
        } else {
            resultTextView.setText("Please enter valid values");
        }
    }
}
