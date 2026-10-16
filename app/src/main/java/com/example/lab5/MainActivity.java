package com.example.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        progressBar = findViewById(R.id.progressBar);

        // Clear notes on run
        Executors.newSingleThreadExecutor().execute(() -> {
            AppDatabase.getInstance(getApplicationContext()).noteDao().deleteAll();
        });

        Button addNoteButton = findViewById(R.id.addNoteButton);
        addNoteButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivity(intent);
        });

        Button browseNoteButton = findViewById(R.id.browseNoteButton);
        browseNoteButton.setOnClickListener(v -> {
            loadBrowseNoteActivity();
        });

        Button clickButton = findViewById(R.id.button3);
        clickButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Click.class);
            startActivity(intent);
        });
    }

    private void loadBrowseNoteActivity() {
        if (progressBar != null) progressBar.setVisibility(View.VISIBLE);
        
        // Multi-tasking using Thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Simulate loading delay of 2 seconds
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // UI updates must be on UI thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (progressBar != null) progressBar.setVisibility(View.GONE);

                        Intent intent = new Intent(MainActivity.this, BrowseNoteActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }).start();
    }
}
