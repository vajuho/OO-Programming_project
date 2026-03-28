package com.example.oo_ohjelmointi_projekti;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.oo_ohjelmointi_projekti.fragments.CityInfoFragment;
import com.example.oo_ohjelmointi_projekti.fragments.CompareFragment;

import java.util.ArrayList;

public class ListInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fragmentSwapMethod(new CityInfoFragment());

        if () {
            fragmentSwapMethod(new CompareFragment());
        }
    }
    private void fragmentSwapMethod(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.InfoFragmentLayout, fragment)
                .commit();
    }
    public void goToCityForCompareActivity(View view) {
        Intent intent = new Intent(this, CityForCompareActivity.class);
        startActivity(intent);
    }
    public void goToHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}