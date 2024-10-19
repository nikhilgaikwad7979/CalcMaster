package com.nick.calcmaster.ui.HDFS;

import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nick.calcmaster.R;

public class HDFCFragment extends Fragment {

    private HDFCViewModel mViewModel;
    private EditText loanAmountInput;
    private EditText interestRateInput;
    private EditText tenureInput; // Tenure in years
    private Button calculateButton;
    private TextView emiResultText;

    public static HDFCFragment newInstance() {
        return new HDFCFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_h_d_f_c, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HDFCViewModel.class);

        // Initialize UI components
        loanAmountInput = getView().findViewById(R.id.loan_amount);
        interestRateInput = getView().findViewById(R.id.interest_rate);
        tenureInput = getView().findViewById(R.id.tenure); // Tenure in years
        calculateButton = getView().findViewById(R.id.calculate_button);
        emiResultText = getView().findViewById(R.id.emi_result);

        // Set click listener on the calculate button
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateEMI();
            }
        });
    }

    private void calculateEMI() {
        String loanAmountStr = loanAmountInput.getText().toString();
        String interestRateStr = interestRateInput.getText().toString();
        String tenureStr = tenureInput.getText().toString();

        // Check for empty inputs
        if (loanAmountStr.isEmpty() || interestRateStr.isEmpty() || tenureStr.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Parse inputs
        double loanAmount = Double.parseDouble(loanAmountStr);
        double annualInterestRate = Double.parseDouble(interestRateStr);
        int tenureYears = Integer.parseInt(tenureStr); // Tenure in years

        // Convert tenure from years to months
        int tenureMonths = tenureYears * 12;

        // Calculate monthly interest rate
        double monthlyInterestRate = annualInterestRate / 12 / 100;

        // Calculate EMI using the formula
        double emi = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenureMonths)) /
                (Math.pow(1 + monthlyInterestRate, tenureMonths) - 1);

        // Display result
        emiResultText.setText(String.format("Monthly EMI: %.2f INR", emi));
    }
}
