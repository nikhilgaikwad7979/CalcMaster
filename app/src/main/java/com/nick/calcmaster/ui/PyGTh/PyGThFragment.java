package com.nick.calcmaster.ui.PyGTh;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModel;
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

public class PyGThFragment extends Fragment {

    private PyGThViewModel mViewModel;
    private EditText sideAEditText;
    private EditText sideBEditText;
    private TextView resultTextView;

    public static PyGThFragment newInstance() {
        return new PyGThFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_py_g_th, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PyGThViewModel.class);

        sideAEditText = getView().findViewById(R.id.sideA);
        sideBEditText = getView().findViewById(R.id.sideB);
        Button calculateButton = getView().findViewById(R.id.calculateButton);
        resultTextView = getView().findViewById(R.id.result);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateHypotenuse();
            }
        });
    }

    private void calculateHypotenuse() {
        String sideAString = sideAEditText.getText().toString();
        String sideBString = sideBEditText.getText().toString();

        if (!sideAString.isEmpty() && !sideBString.isEmpty()) {
            double sideA = Double.parseDouble(sideAString);
            double sideB = Double.parseDouble(sideBString);
            double hypotenuse = Math.sqrt((sideA * sideA) + (sideB * sideB));
            resultTextView.setText(String.format("Hypotenuse: %.2f", hypotenuse));
        } else {
            resultTextView.setText("Please enter both values.");
        }
    }
}
