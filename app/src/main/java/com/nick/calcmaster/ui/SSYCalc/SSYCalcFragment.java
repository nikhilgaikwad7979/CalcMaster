package com.nick.calcmaster.ui.SSYCalc;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nick.calcmaster.R;

public class SSYCalcFragment extends Fragment {

    private SSYCalcViewModel mViewModel;

    // UI components
    private EditText editInvest, editAge, editYear;
    private Button calculateButton;
    private TextView totalInvest, totalInterest, maturityYear, maturityValue;
    private double yearlyInvestment;
    private int age;
    private int startYear;
    private final int maturityPeriod = 21;
    private final double rateOfInterest =8; // Fixed interest rate
    private final int maxInvestmentYears = 15;

    public static SSYCalcFragment newInstance() {
        return new SSYCalcFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_s_s_y_calc, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SSYCalcViewModel.class);
        initUI();
    }

    private void initUI() {
        editInvest = getView().findViewById(R.id.editinvest);
        editAge = getView().findViewById(R.id.editage);
        editYear = getView().findViewById(R.id.edityear);
        calculateButton = getView().findViewById(R.id.calculate);
        totalInvest = getView().findViewById(R.id.totalinvest);
        totalInterest = getView().findViewById(R.id.totalinterest);
        maturityYear = getView().findViewById(R.id.maturityyear);
        maturityValue = getView().findViewById(R.id.maturityvalue);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateInvestment();
            }
        });
    }

    private void calculateInvestment() {
        yearlyInvestment = Double.parseDouble(editInvest.getText().toString());
        age = Integer.parseInt(editAge.getText().toString());
        startYear = Integer.parseInt(editYear.getText().toString());
        int investmentYears = Math.min(maxInvestmentYears, maturityPeriod - age);
        int maturityYearValue = startYear + maturityPeriod;
        double totalInvestment = yearlyInvestment * investmentYears;
        double maturityValueTotal = 0.0;

        for (int i = 0; i < investmentYears; i++) {
            maturityValueTotal += yearlyInvestment * Math.pow(1 + (rateOfInterest / 100), maturityPeriod - i);
        }

        double totalInterestValue = maturityValueTotal - totalInvestment;
        long totalInterestLong = Math.round(totalInterestValue);
        long maturityValueLong = Math.round(maturityValueTotal);

        totalInvest.setText("Total Investment: " + totalInvestment);
        totalInterest.setText("Total Interest: " + totalInterestLong);
        maturityYear.setText("Maturity Year: " + maturityYearValue);
        maturityValue.setText("Maturity Value: " + maturityValueLong);
    }
}
