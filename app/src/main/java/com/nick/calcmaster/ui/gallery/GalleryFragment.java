package com.nick.calcmaster.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.nick.calcmaster.R; // Ensure this points to your actual package

public class GalleryFragment extends Fragment {

    // EditText fields for matrix input
    private EditText inputA11, inputA12, inputA21, inputA22;
    private EditText inputB11, inputB12, inputB21, inputB22;
    private TextView resultTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        // Initialize EditText and TextView
        inputA11 = root.findViewById(R.id.inputA11);
        inputA12 = root.findViewById(R.id.inputA12);
        inputA21 = root.findViewById(R.id.inputA21);
        inputA22 = root.findViewById(R.id.inputA22);
        inputB11 = root.findViewById(R.id.inputB11);
        inputB12 = root.findViewById(R.id.inputB12);
        inputB21 = root.findViewById(R.id.inputB21);
        inputB22 = root.findViewById(R.id.inputB22);
        Button calculateButton = root.findViewById(R.id.calculateButton);
        resultTextView = root.findViewById(R.id.resultTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateMatrices();
            }
        });

        return root;
    }

    private void calculateMatrices() {
        try {
            int[][] matrixA = {
                    {Integer.parseInt(inputA11.getText().toString()), Integer.parseInt(inputA12.getText().toString())},
                    {Integer.parseInt(inputA21.getText().toString()), Integer.parseInt(inputA22.getText().toString())}
            };
            int[][] matrixB = {
                    {Integer.parseInt(inputB11.getText().toString()), Integer.parseInt(inputB12.getText().toString())},
                    {Integer.parseInt(inputB21.getText().toString()), Integer.parseInt(inputB22.getText().toString())}
            };

            // Perform matrix addition and subtraction
            int[][] sum = new int[2][2];
            int[][] difference = new int[2][2];

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    sum[i][j] = matrixA[i][j] + matrixB[i][j];
                    difference[i][j] = matrixA[i][j] - matrixB[i][j];
                }
            }

            resultTextView.setText("Matrix A:\n" + matrixToString(matrixA) +
                    "\nMatrix B:\n" + matrixToString(matrixB) +
                    "\nSum:\n" + matrixToString(sum) +
                    "\nDifference:\n" + matrixToString(difference));
        } catch (NumberFormatException e) {
            resultTextView.setText("Please enter valid integers.");
        }
    }

    private String matrixToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            for (int value : row) {
                sb.append(value).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
