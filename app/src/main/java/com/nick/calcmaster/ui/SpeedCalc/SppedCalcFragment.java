package com.nick.calcmaster.ui.SpeedCalc;

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

public class SppedCalcFragment extends Fragment {

    private SppedCalcViewModel mViewModel;
    private EditText distanceInput;
    private EditText timeInput;
    private TextView speedOutput;
    private Button calculateButton;

    public static SppedCalcFragment newInstance() {
        return new SppedCalcFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_spped_calc, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SppedCalcViewModel.class);

        // Bind views
        distanceInput = getView().findViewById(R.id.distance_input);
        timeInput = getView().findViewById(R.id.time_input);
        speedOutput = getView().findViewById(R.id.speed_output);
        calculateButton = getView().findViewById(R.id.calculate_button);

        // Set button click listener
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSpeed();
            }
        });
    }

    private void calculateSpeed() {
        String distanceStr = distanceInput.getText().toString();
        String timeStr = timeInput.getText().toString();

        if (!distanceStr.isEmpty() && !timeStr.isEmpty()) {
            try {
                double distance = Double.parseDouble(distanceStr);
                double time = Double.parseDouble(timeStr);

                if (time > 0) {
                    double speed = distance / time;
                    speedOutput.setText("Speed: " + speed + " km/h");
                } else {
                    speedOutput.setText("Time must be greater than 0");
                }
            } catch (NumberFormatException e) {
                speedOutput.setText("Invalid input. Please enter numeric values.");
            }
        } else {
            speedOutput.setText("Please enter both distance and time.");
        }
    }
}
