package com.nick.calcmaster.ui.ElectricityBill;

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

import com.nick.calcmaster.R;

public class ElectricityBillFragment extends Fragment {

    private ElectricityBillViewModel mViewModel;
    private EditText currentUnitsEditText;
    private EditText previousUnitsEditText;
    private EditText rateEditText;
    private Button calculateButton;
    private TextView billAmountTextView;

    public static ElectricityBillFragment newInstance() {
        return new ElectricityBillFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_electricity_bill, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ElectricityBillViewModel.class);

        // Bind views
        currentUnitsEditText = getView().findViewById(R.id.current_units);
        previousUnitsEditText = getView().findViewById(R.id.previous_units);
        rateEditText = getView().findViewById(R.id.rate);
        calculateButton = getView().findViewById(R.id.calculate_button);
        billAmountTextView = getView().findViewById(R.id.bill_amount);

        // Set click listener for the button
        calculateButton.setOnClickListener(v -> calculateBill());
    }

    private void calculateBill() {
        String currentUnitsString = currentUnitsEditText.getText().toString();
        String previousUnitsString = previousUnitsEditText.getText().toString();
        String rateString = rateEditText.getText().toString();

        // Check for empty fields
        if (currentUnitsString.isEmpty() || previousUnitsString.isEmpty() || rateString.isEmpty()) {
            billAmountTextView.setText("Please fill in all fields.");
            return;
        }

        try {
            // Parse input values
            int currentUnits = Integer.parseInt(currentUnitsString);
            int previousUnits = Integer.parseInt(previousUnitsString);
            double rate = Double.parseDouble(rateString);

            // Calculate the total units consumed
            int totalUnits = currentUnits - previousUnits;

            // Check for negative consumption
            if (totalUnits < 0) {
                billAmountTextView.setText("Current units must be greater than previous units.");
                return;
            }

            // Calculate the bill amount
            double billAmount = totalUnits * rate;

            // Display the result
            billAmountTextView.setText(String.format("Total Bill Amount: %.2f INR", billAmount));
        } catch (NumberFormatException e) {
            billAmountTextView.setText("Invalid input. Please check your numbers.");
        }
    }
}
