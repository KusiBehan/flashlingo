package com.example.flashlingo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class LearnsetSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_learnset_selection);
        LinearLayout buttonLayout = findViewById(R.id.BtnLayout);

        HashMap<String, ArrayList<Card>> learnset = Learnset.getInstance().getLearnset();

        learnset.forEach((key, value) -> {
            Button button = new Button(this);
            button.setText(key);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent learningIntent = new Intent(LearnsetSelectionActivity.this, LearningActivity.class);
                    learningIntent.putExtra("learnsetName", key);
                    learningIntent.putExtra("learnsetCards", (Serializable) value);
                    startActivity(learningIntent);
                }
            });
            buttonLayout.addView(button);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
