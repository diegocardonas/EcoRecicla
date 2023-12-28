package com.example.ecorecicla;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileWriter;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    private static final String FILE_NAME = "usuarios.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Configurar ActionBar con botón de regreso y título personalizado
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Registro");
        }

        final EditText editTextRegisterUsername = findViewById(R.id.editTextRegisterUsername);
        final EditText editTextRegisterPassword = findViewById(R.id.editTextRegisterPassword);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextRegisterUsername.getText().toString().trim();
                String password = editTextRegisterPassword.getText().toString().trim();

                // Validaciones
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    if (registerUser(username, password)) {
                        // Registro exitoso, por ahora simplemente regresamos a la página de inicio de sesión
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    } else {
                        // Manejar el caso en que el registro falla
                        Toast.makeText(RegisterActivity.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // Método para registrar usuario
    private boolean registerUser(String username, String password) {
        try {
            // Abre el archivo en modo de adición para no sobrescribir los usuarios existentes
            FileWriter writer = new FileWriter(getFileStreamPath(FILE_NAME), true);
            writer.write(username + ":" + password + "\n");
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
