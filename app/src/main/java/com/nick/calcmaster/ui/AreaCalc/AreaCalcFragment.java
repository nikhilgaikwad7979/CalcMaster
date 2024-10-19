package com.nick.calcmaster.ui.AreaCalc;

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

public class AreaCalcFragment extends Fragment {

    private AreaCalcViewModel mViewModel;
    private EditText editTextLength;
    private EditText editTextWidth;
    private EditText editTextBase;
    private EditText editTextHeight;
    private Button buttonCalculate;
    private TextView textViewResult;

    public static AreaCalcFragment newInstance() {
        return new AreaCalcFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_area_calc, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AreaCalcViewModel.class);

        // Bind views
        editTextLength = getView().findViewById(R.id.editTextLength);
        editTextWidth = getView().findViewById(R.id.editTextWidth);
        editTextBase = getView().findViewById(R.id.editTextBase);
        editTextHeight = getView().findViewById(R.id.editTextHeight);
        buttonCalculate = getView().findViewById(R.id.buttonCalculate);
        textViewResult = getView().findViewById(R.id.textViewResult);

        // Set click listener for the button
        buttonCalculate.setOnClickListener(v -> calculateArea());
    }

    private void calculateArea() {
        String lengthString = editTextLength.getText().toString();
        String widthString = editTextWidth.getText().toString();
        String baseString = editTextBase.getText().toString();
        String heightString = editTextHeight.getText().toString();

        // Initialize area variable
        double area = 0;

        // Get values
        if (!lengthString.isEmpty()) {
            double length = Double.parseDouble(lengthString);
            if (!widthString.isEmpty()) {
                // Rectangle area calculation
                double width = Double.parseDouble(widthString);
                area = length * width;
                textViewResult.setText(String.format("Area of Rectangle: %.2f", area));
            } else if (!baseString.isEmpty() && !heightString.isEmpty()) {
                // Triangle area calculation
                double base = Double.parseDouble(baseString);
                double height = Double.parseDouble(heightString);
                area = 0.5 * base * height;
                textViewResult.setText(String.format("Area of Triangle: %.2f", area));
            } else {
                // Circle area calculation
                area = Math.PI * Math.pow(length, 2);
                textViewResult.setText(String.format("Area of Circle: %.2f", area));
            }
        } else {
            textViewResult.setText("Please enter a length, width, base, or height.");
        }
    }
}
