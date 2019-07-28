package com.example.mytodo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.mytodo.db.ToDo;
import com.example.mytodo.db.ToDoRepository;

public class UpdateToDoActivity extends AppCompatActivity {

    private EditText mTitleEditText, mDetailsEditText;
    private ToDo mToDo;
    private CheckBox mCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_to_do);

        Intent intent = getIntent();
        mToDo = (ToDo) intent.getSerializableExtra("todo");

        mTitleEditText = findViewById(R.id.title_edit_text);
        mDetailsEditText = findViewById(R.id.details_edit_text);
        mCheckBox = findViewById(R.id.finish_check_box);

        mTitleEditText.setText(mToDo.getTitle());
        mDetailsEditText.setText(mToDo.getDetail());
        mCheckBox.setChecked(mToDo.getChecked());


        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateToDo();
            }
        });

        Button deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(UpdateToDoActivity.this)
                        .setTitle(R.string.app_name)
                        .setMessage("ยืนยันการลบ ToDo นี้หรือไม่?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ToDoRepository repo = new ToDoRepository(UpdateToDoActivity.this);
                                repo.deleteToDo(mToDo.getId());
                                finish();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();
            }
        });


    }

    private void updateToDo() {
        if (validateForm()) {
            String title = mTitleEditText.getText().toString().trim();
            String details = mDetailsEditText.getText().toString().trim();
            Boolean checked = mCheckBox.isChecked();


            ToDoRepository repo = new ToDoRepository(UpdateToDoActivity.this);
            repo.updateToDo(mToDo.getId(),title,details,checked);
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
