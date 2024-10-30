package com.example.fragmentapplication;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    // Donde ubicaremos el texto:
    private FrameLayout fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        fragmentContainer = findViewById(R.id.fragment_container);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Cargar los dos fragmentos si estamos en orientación horizontal:
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_landscape);
            loadFragmentForHorizontal();

        } else {
            Button buttonOne = findViewById(R.id.button);
            buttonOne.setOnClickListener(v -> loadFragment(new FirstFragment()));

            Button buttonTwo = findViewById(R.id.button_two);
            buttonTwo.setOnClickListener(v -> loadFragment(new SecondFragment()));
        }
    }

    private void loadFragment(Fragment fragment) {
        // FragmentManager se utiliza para gestionar los fragmentos
        FragmentManager fragmentManager = getSupportFragmentManager();
        // FragmentTransaction se utiliza para realizar las operaciones de reemplazo
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment);

        fragmentTransaction.commit();
    }

    private void loadFragmentForHorizontal() {
        // FragmentManager se utiliza para gestionar los fragmentos
        FragmentManager fragmentManager = getSupportFragmentManager();
        // FragmentTransaction se utiliza para realizar las operaciones de reemplazo
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.left_fragment_container, new FirstFragment());
        fragmentTransaction.replace(R.id.right_fragment_container, new SecondFragment());

        fragmentTransaction.commit();
    }
}