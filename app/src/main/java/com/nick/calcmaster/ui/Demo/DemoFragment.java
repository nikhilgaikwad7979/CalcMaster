package com.nick.calcmaster.ui.Demo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.nick.calcmaster.R;

public class DemoFragment extends AppCompatActivity {

    private EditText angleInput;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_demo);

        angleInput = findViewById(R.id.angle_input);
        resultView = findViewById(R.id.result_view);
        Button calculateButton = findViewById(R.id.calculate_button);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = angleInput.getText().toString();
                if (!input.isEmpty()) {
                    double angleInDegrees = Double.parseDouble(input); // Get user input
                    double angleInRadians = Math.toRadians(angleInDegrees); // Convert to radians

                    double sinValue = Math.sin(angleInRadians);
                    double cosValue = Math.cos(angleInRadians);
                    double tanValue = Math.tan(angleInRadians);

                    resultView.setText("Sine: " + sinValue + "\n" +
                            "Cosine: " + cosValue + "\n" +
                            "Tangent: " + tanValue);
                } else {
                    resultView.setText("Please enter a valid angle.");
                }
            }
        });
    }
}
