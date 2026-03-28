package com.example.oo_ohjelmointi_projekti;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CityForCompareActivity extends AppCompatActivity {
    EditText CityNameCompareEdit;
    Button CityCompareSearchButton;
    TextView StatusTextTwo;
    Button GoToCompare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_city_for_compare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        CityNameCompareEdit = findViewById(R.id.CityNameCompareEdit);
        CityCompareSearchButton = findViewById(R.id.CityCompareSearchButton);
        StatusTextTwo = findViewById(R.id.StatusTextTwo);
        GoToCompare.setEnabled(false);

        CityCompareSearchButton.setOnClickListener(View -> {
            String cityName = CityNameCompareEdit.getText().toString().trim();
            if (!cityName.isEmpty()) {
                getCompareData();
            }
        });
    }

    public void getCompareData() {
        Context context = this;
        DataRetriever dr = new DataRetriever();
        ExecutorService service = Executors.newSingleThreadExecutor();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                StatusTextTwo.setText("Haetaan");
            }
        });
        service.execute(new Runnable() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (fadklfaös == null) {
                            StatusTextTwo.setText("Haku epäonnistui, kaupunkia ei ole olemassa tai se on kirjoitettu väärin.");
                            return;
                        }

                        StatusTextTwo.setText("Haku onnistui");
                    }
                });
            }
        });
    }

    public void goToListInfoActivity(View view) {
        Intent intent = new Intent(this, ListInfoActivity.class);
        startActivity(intent);
    }
}