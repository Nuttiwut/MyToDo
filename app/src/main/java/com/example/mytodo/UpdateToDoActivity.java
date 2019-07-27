package com.example.mytodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mytodo.db.ToDo;
import com.example.mytodo.db.ToDoRepository;

public class UpdateToDoActivity extends AppCompatActivity {

    private EditText mTitleEditText, mDetailsEditText;
    private ToDo mToDo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_to_do);

        Intent intent = getIntent();
        mToDo = (ToDo) intent.getSerializableExtra("todo");

        mTitleEditText = findViewById(R.id.title_edit_text);
        mDetailsEditText = findViewById(R.id.details_edit_text);

        mTitleEditText.setText(mToDo.getTitle());
        mDetailsEditText.setText(mToDo.getDetail());

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateToDo();
            }
        });
    }

    private void updateToDo() {
        if (validateForm()) {
            String title = mTitleEditText.getText().toString().trim();
            String details = mDetailsEditText.getText().toString().trim();

            ToDoRepository repo = new ToDoRepository(UpdateToDoActivity.this);
            repo.updateToDo(mToDo.getId(),title,details);
            finish();
        }
    }

    private boolean validateForm() {
        boolean valid = true;

        String title = mTitleEditText.getText().toString().trim();
        String details = mDetailsEditText.getText().toString().trim();

        if (title.isEmpty()) {
            mTitleEditText.setError("กรุณากรอกห้วข้อ ToDo");
            valid = false;
        }
        if (details.isEmpty()) {
            mDetailsEditText.setError("กรุณากรอกรายละเอียด ToDo");
            valid = false;
        }
        return valid;
    }
}
