package com.nick.calcmaster.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.nick.calcmaster.R;

import java.util.Stack;

public class HomeFragment extends Fragment {

    private TextView resultTextView;
    private StringBuilder input = new StringBuilder();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        resultTextView = view.findViewById(R.id.resultTextView);
        initializeButtons(view);

        return view;
    }

    private void initializeButtons(View view) {
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonAdd, R.id.buttonSubtract,
                R.id.buttonMultiply, R.id.buttonDivide, R.id.buttonEquals,
                R.id.buttonC, R.id.buttonDot, R.id.buttonDelete
        };

        for (int id : buttonIds) {
            Button button = view.findViewById(id);
            button.setOnClickListener(this::onButtonClick);
        }
    }

    @SuppressLint("NonConstantResourceId")
    private void onButtonClick(View v) {
        String buttonText = ((Button) v).getText().toString();
        if (buttonText.equals("C")) {
            clearInput();
        } else if (buttonText.equals("=")) {
            calculateResult();
        } else if (buttonText.equals("DEL")) {
            deleteLastCharacter();
        } else {
            input.append(buttonText);
            resultTextView.setText(input.toString());
        }
    }

    private void clearInput() {
        input.setLength(0);
        resultTextView.setText("");
    }

    private void deleteLastCharacter() {
        if (input.length() > 0) {
            input.setLength(input.length() - 1); // Remove last character
            resultTextView.setText(input.toString());
        }
    }

    private void calculateResult() {
        String expression = input.toString();
        try {
            double result = simpleArithmeticEvaluation(expression);
            resultTextView.setText(String.valueOf(result));
            input.setLength(0); // Clear input after evaluation
        } catch (Exception e) {
            resultTextView.setText("Error");
        }
    }

    private double simpleArithmeticEvaluation(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == ' ') continue;

            if (Character.isDigit(c) || c == '.') {
                StringBuilder number = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    number.append(expression.charAt(i));
                    i++;
                }
                i--;
                numbers.push(Double.parseDouble(number.toString()));
            } else {
                while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek())) {
                    numbers.push(applyOperator(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.push(c);
            }
        }

        while (!operators.isEmpty()) {
            numbers.push(applyOperator(operators.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    private int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    private double applyOperator(char operator, double b, double a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) throw new UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }
}
