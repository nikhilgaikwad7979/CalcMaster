package com.nick.calcmaster.ui.RootCalc;

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

public class RootCalcFragment extends Fragment {

    private RootCalcViewModel mViewModel;
    private EditText inputNumber;
    private TextView resultText;
    private Button calculateButton;

    public static RootCalcFragment newInstance() {
        return new RootCalcFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_root_calc, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RootCalcViewModel.class);

        inputNumber = getView().findViewById(R.id.inputNumber);
        resultText = getView().findViewById(R.id.resultText);
        calculateButton = getView().findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSquareRoot();
            }
        });
    }

    private void calculateSquareRoot() {
        String input = inputNumber.getText().toString();
        if (!input.isEmpty()) {
            try {
                double number = Double.parseDouble(input);
                double squareRoot = Math.sqrt(number);
                resultText.setText("Square Root: " + squareRoot);
            } catch (NumberFormatException e) {
                resultText.setText("Invalid number");
            }
        } else {
            resultText.setText("Please enter a number");
        }
    }
}
