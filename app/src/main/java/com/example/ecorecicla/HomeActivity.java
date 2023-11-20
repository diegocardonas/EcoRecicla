package com.example.ecorecicla;

// HomeActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button buttonLogout = findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Agrega la lógica para cerrar sesión
                // En este ejemplo, simplemente regresamos a la página de inicio de sesión
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish(); // Esto cierra la actividad actual (HomeActivity)
            }
        });
    }
}