package com.example.lab5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BrowseNoteActivity extends AppCompatActivity {

    private EditText searchUserEditText;
    private Button searchButton;
    private ProgressBar browseProgressBar;
    private TextView searchResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_note);

        searchUserEditText = findViewById(R.id.searchUserEditText);
        searchButton = findViewById(R.id.searchButton);
        browseProgressBar = findViewById(R.id.browseProgressBar);
        searchResultTextView = findViewById(R.id.searchResultTextView);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });

        Button backButton = findViewById(R.id.button4);
        backButton.setOnClickListener(v -> finish());
    }

    private void performSearch() {
        String query = searchUserEditText.getText().toString().trim();
        if (query.isEmpty()) {
            searchResultTextView.setText("กรุณากรอกชื่อผู้ใช้");
            return;
        }

        searchResultTextView.setText("");
        browseProgressBar.setVisibility(View.VISIBLE);

        // Multi-tasking using Thread for Search
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Simulate search delay of 2 seconds
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Update UI after search
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        browseProgressBar.setVisibility(View.GONE);
                        // Per requirements, always show "ไม่พบข้อมูล" (Data not found)
                        searchResultTextView.setText("ไม่พบข้อมูล");
                    }
                });
            }
        }).start();
    }
}
