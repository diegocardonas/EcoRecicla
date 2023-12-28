package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ImageView imageViewCategories = findViewById(R.id.imageViewCategories);
        ImageView imageViewStatistics = findViewById(R.id.imageViewStatistics);
        ImageView imageViewTips = findViewById(R.id.imageViewTips);

        imageViewCategories.setOnClickListener(view -> openCategoriesActivity());

        imageViewStatistics.setOnClickListener(view -> openStatisticsActivity());

        imageViewTips.setOnClickListener(view -> openTipsActivity());

        Button buttonLogout = findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(view -> closeSession());
    }

    private void openCategoriesActivity() {
        Intent intent = new Intent(this, CategoriesActivity.class);
        startActivity(intent);
    }

    private void openStatisticsActivity() {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }

    private void openTipsActivity() {
        Intent intent = new Intent(this, TipsActivity.class);
        startActivity(intent);
    }

    private void closeSession() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish(); // Cierra la actividad actual para evitar que el usuario vuelva atrás
    }
}
