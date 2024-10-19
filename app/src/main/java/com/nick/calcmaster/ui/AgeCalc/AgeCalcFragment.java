package com.nick.calcmaster.ui.AgeCalc;

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

import java.time.LocalDate;

public class AgeCalcFragment extends Fragment {

    private AgeCalcViewModel mViewModel;
    private EditText yearEditText;
    private EditText monthEditText;
    private EditText dayEditText;
    private Button calculateButton;
    private TextView resultTextView;

    public static AgeCalcFragment newInstance() {
        return new AgeCalcFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_age_calc, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AgeCalcViewModel.class);

        yearEditText = getView().findViewById(R.id.yearEditText);
        monthEditText = getView().findViewById(R.id.monthEditText);
        dayEditText = getView().findViewById(R.id.dayEditText);
        calculateButton = getView().findViewById(R.id.calculateButton);
        resultTextView = getView().findViewById(R.id.resultTextView);

        calculateButton.setOnClickListener(v -> calculateAge());
    }

    private void calculateAge() {
        String yearString = yearEditText.getText().toString();
        String monthString = monthEditText.getText().toString();
        String dayString = dayEditText.getText().toString();

        if (yearString.isEmpty() || monthString.isEmpty() || dayString.isEmpty()) {
            resultTextView.setText("Please fill in all fields.");
            return;
        }

        try {
            int year = Integer.parseInt(yearString);
            int month = Integer.parseInt(monthString);
            int day = Integer.parseInt(dayString);

            // Validate month and day
            if (month < 1 || month > 12 || day < 1 || day > 31) {
                resultTextView.setText("Please enter a valid month and day.");
                return;
            }

            LocalDate birthdate = LocalDate.of(year, month, day);
            LocalDate today = LocalDate.now();

            // Calculate age
            int ageYears = today.getYear() - birthdate.getYear();
            int ageMonths = today.getMonthValue() - birthdate.getMonthValue();
            int ageDays = today.getDayOfMonth() - birthdate.getDayOfMonth();

            // Adjust if necessary
            if (ageDays < 0) {
                ageMonths--;
                LocalDate lastMonth = today.minusMonths(1);
                ageDays += lastMonth.lengthOfMonth(); // Add days from the last month
            }

            if (ageMonths < 0) {
                ageYears--;
                ageMonths += 12; // Adjust months
            }

            resultTextView.setText(String.format("Your age is: %d years, %d months, and %d days", ageYears, ageMonths, ageDays));
        } catch (IllegalArgumentException e) {
            resultTextView.setText("Invalid date. Please check your input.");
        } catch (Exception e) {
            resultTextView.setText("Invalid input. Please check your numbers.");
        }
    }
}
