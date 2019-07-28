package com.example.mytodo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mytodo.adapter.ToDoListAdapter;
import com.example.mytodo.db.ToDo;
import com.example.mytodo.db.ToDoRepository;
import com.example.mytodo.net.AddToDoResponse;
import com.example.mytodo.net.ApiClient;
import com.example.mytodo.net.GetToDoResponse;
import com.example.mytodo.net.MyRetrofitCallback;
import com.example.mytodo.net.WebServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class AddToDoActivity extends AppCompatActivity {


    private EditText mTitleEditText, mDetailsEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        mTitleEditText = findViewById(R.id.title_edit_text);
        mDetailsEditText = findViewById(R.id.details_edit_text);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToDo();
            }
        });
    }

    private void addToDo() {
        if (validateForm()) {
            String title = mTitleEditText.getText().toString().trim();
            String details = mDetailsEditText.getText().toString().trim();

//            ติดต่อกับฐานข้อมูลภายใน
//            ToDoRepository repo = new ToDoRepository(AddToDoActivity.this);
//            repo.addToDo(title,details);
//            finish();

//            ติดต่อกับฐานข้อมูลออนไลน์
            Retrofit retrofit = ApiClient.getClient();
            WebServices services = retrofit.create(WebServices.class);
            Call<AddToDoResponse> call = services.addTodo(title,details);
            call.enqueue(new MyRetrofitCallback<>(
                    AddToDoActivity.this,
                    null,
                    null,
                    new MyRetrofitCallback.MyRetrofitCallbackListener<AddToDoResponse>() {
                        @Override
                        public void onSuccess(AddToDoResponse responseBody) {
                            finish();
                        }

                        @Override
                        public void onError(String errorMessage) {
                            new AlertDialog.Builder(AddToDoActivity.this)
                                    .setTitle("Error")
                                    .setMessage(errorMessage)
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    }
            ));



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
