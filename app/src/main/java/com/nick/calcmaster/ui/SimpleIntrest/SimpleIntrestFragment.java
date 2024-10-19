package com.nick.calcmaster.ui.SimpleIntrest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class SimpleIntrestFragment extends Fragment {

    private SimpleIntrestViewModel mViewModel;
    private EditText principalInput;
    private EditText rateInput;
    private EditText timeInput;
    private Button calculateButton;
    private TextView simpleInterestText;

    public static SimpleIntrestFragment newInstance() {
        return new SimpleIntrestFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simple_intrest, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SimpleIntrestViewModel.class);

        // Initialize UI components
        principalInput = getView().findViewById(R.id.principal);
        rateInput = getView().findViewById(R.id.rate);
        timeInput = getView().findViewById(R.id.time);
        calculateButton = getView().findViewById(R.id.calculate_button);
        simpleInterestText = getView().findViewById(R.id.simple_interest);

        // Set click listener on the calculate button
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSimpleInterest();
            }
        });
    }

    private void calculateSimpleInterest() {
        String principalStr = principalInput.getText().toString();
        String rateStr = rateInput.getText().toString();
        String timeStr = timeInput.getText().toString();

        if (!principalStr.isEmpty() && !rateStr.isEmpty() && !timeStr.isEmpty()) {
            double principal = Double.parseDouble(principalStr);
            double rate = Double.parseDouble(rateStr);
            double time = Double.parseDouble(timeStr);

            // Calculate simple interest
            double simpleInterest = (principal * rate * time) / 100;

            // Display the result
            simpleInterestText.setText(String.format("Simple Interest: %.2f INR", simpleInterest));
        } else {
            simpleInterestText.setText("Please enter all fields.");
        }
    }
}
