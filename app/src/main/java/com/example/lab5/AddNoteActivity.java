package com.example.lab5;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class AddNoteActivity extends AppCompatActivity {

    private EditText userNameEditText;
    private EditText noteTitleEditText;
    private RadioGroup noteTypeRadioGroup;
    private RadioButton textNoteRadioButton;
    private EditText noteContentEditText;
    private Button saveNoteButton;
    private Button backButton;
    private TextView displayNoteTextView;

    // The Controller in MVC
    private NoteController noteController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        // Initialize Controller
        noteController = new NoteController();

        userNameEditText = findViewById(R.id.userNameEditText);
        noteTitleEditText = findViewById(R.id.noteTitleEditText);
        noteTypeRadioGroup = findViewById(R.id.noteTypeRadioGroup);
        textNoteRadioButton = findViewById(R.id.textNoteRadioButton);
        noteContentEditText = findViewById(R.id.noteContentEditText);
        saveNoteButton = findViewById(R.id.saveNoteButton);
        backButton = findViewById(R.id.button2);
        displayNoteTextView = findViewById(R.id.displayNoteTextView);

        saveNoteButton.setOnClickListener(v -> {
            handleAddNote();
        });

        backButton.setOnClickListener(v -> {
            finish();
        });
    }

    private void handleAddNote() {
        String userName = userNameEditText.getText().toString().trim();
        String title = noteTitleEditText.getText().toString().trim();
        String content = noteContentEditText.getText().toString().trim();

        if (userName.isEmpty() || title.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Note note;
        if (textNoteRadioButton.isChecked()) {
            // View calls Controller to handle logic
            note = noteController.addTextNote(title, userName, content);
        } else {
            List<String> items = Arrays.asList(content.split(",\\s*"));
            // View calls Controller to handle logic
            note = noteController.addCheckListNote(title, userName, items);
        }

        // View asks Controller for data to display
        String details = noteController.getNoteDetails(note);
        displayNoteTextView.setText(details);
    }
}
