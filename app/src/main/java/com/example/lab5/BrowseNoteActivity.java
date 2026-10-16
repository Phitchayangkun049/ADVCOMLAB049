package com.example.lab5;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class BrowseNoteActivity extends AppCompatActivity {

    TextView showNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_note);

        showNote = findViewById(R.id.textView4);
        Button backButton = findViewById(R.id.button4);
        
        if (backButton != null) {
            backButton.setOnClickListener(v -> finish());
        }

        // Load data from database using background thread
        Executors.newSingleThreadExecutor().execute(() -> {
            List<NoteEntity> entities = AppDatabase.getInstance(this).noteDao().getAll();
            List<Note> notes = new ArrayList<>();
            for (NoteEntity e : entities) {
                notes.add(NoteMapper.fromEntity(e));
            }

            // Display on UI thread
            runOnUiThread(() -> {
                StringBuilder sb = new StringBuilder();
                if (notes.isEmpty()) {
                    sb.append("No notes found.");
                } else {
                    for (Note n : notes) {
                        sb.append(n.display()).append("\n\n");
                    }
                }
                showNote.setText(sb.toString());
            });
        });
    }
}
