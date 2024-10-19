package com.nick.calcmaster.ui.CompoundIntrest;

import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.nick.calcmaster.R;

public class CompoundIntrestFragment extends Fragment {

    private CompoundIntrestViewModel mViewModel;
    private EditText principalInput;
    private EditText rateInput;
    private EditText timeInput;
    private EditText timesCompoundedInput;
    private Button calculateButton;
    private TextView compoundInterestText;

    public static CompoundIntrestFragment newInstance() {
        return new CompoundIntrestFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_compound_intrest, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CompoundIntrestViewModel.class);

        // Initialize UI components
        principalInput = getView().findViewById(R.id.principal);
        rateInput = getView().findViewById(R.id.rate);
        timeInput = getView().findViewById(R.id.time);
        timesCompoundedInput = getView().findViewById(R.id.times_compounded);
        calculateButton = getView().findViewById(R.id.calculate_button);
        compoundInterestText = getView().findViewById(R.id.compound_interest);

        // Set click listener on the calculate button
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateCompoundInterest();
            }
        });
    }

    private void calculateCompoundInterest() {
        String principalStr = principalInput.getText().toString();
        String rateStr = rateInput.getText().toString();
        String timeStr = timeInput.getText().toString();
        String timesCompoundedStr = timesCompoundedInput.getText().toString();

        if (!principalStr.isEmpty() && !rateStr.isEmpty() && !timeStr.isEmpty() && !timesCompoundedStr.isEmpty()) {
            double principal = Double.parseDouble(principalStr);
            double rate = Double.parseDouble(rateStr) / 100; // Convert percentage to decimal
            double time = Double.parseDouble(timeStr);
            int timesCompounded = Integer.parseInt(timesCompoundedStr);

            // Calculate compound interest
            double compoundInterest = principal * Math.pow(1 + (rate / timesCompounded), timesCompounded * time) - principal;

            // Display the result
            compoundInterestText.setText(String.format("Compound Interest: %.2f INR", compoundInterest));
        } else {
            compoundInterestText.setText("Please enter all fields.");
        }
    }
}
