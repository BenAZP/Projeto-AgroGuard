package com.example.agroguard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        dbHelper = new DBHelper(this);
        displayUserProfile();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configura o botão de sair
        ImageView logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redireciona para a LoginActivity
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Opcional: fecha a HomeActivity para evitar que o usuário volte pressionando o botão Voltar
            }
        });

        // Configura o botão para ir para a SecondMainActivity
        Button quizButton = findViewById(R.id.quiz);
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SecondMainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayUserProfile() {
        ImageView profileIcon = findViewById(R.id.profile_icon);
        TextView usernameText = findViewById(R.id.username_text);

        // Obter o nome do usuário do banco de dados
        String username = dbHelper.getUsername();

        // Definir o nome do usuário no TextView
        usernameText.setText(username);
    }
}
