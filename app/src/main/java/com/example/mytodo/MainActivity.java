package com.example.mytodo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mytodo.adapter.ToDoListAdapter;
import com.example.mytodo.db.AppDatabase;
import com.example.mytodo.db.ToDo;
import com.example.mytodo.db.ToDoRepository;
import com.example.mytodo.net.ApiClient;
import com.example.mytodo.net.GetToDoResponse;
import com.example.mytodo.net.MyRetrofitCallback;
import com.example.mytodo.net.WebServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    private RecyclerView mToDoRecyclerView;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToDoRecyclerView = findViewById(R.id.todo_recycler_view);
        mToDoRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.GONE);

        FloatingActionButton fab = findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddToDoActivity.class);
                startActivity(intent);
            }
        });

//        OkHttpClient client = new OkHttpClient();
//
//        Request request = new Request.Builder()
//                .url("http://10.0.2.2:3000")
//                .build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e(TAG, e.getMessage());
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String result = response.body().string();
//                Log.i(TAG,"Internet " + result);
//            }
//        });

        //เรียกใช้
//        GetToDoTask getTask = new GetToDoTask(MainActivity.this);
//        getTask.execute();

    }


    @Override
    protected void onResume() {
        super.onResume();

//        ToDoRepository repo = new ToDoRepository(MainActivity.this);
////        repo.addToDo("aaaa", "bbbbb");
//
//        repo.getAllToDo(new ToDoRepository.CallBack() {
//            @Override
//            public void onGetTodo(List<ToDo> todoList) {
//
//                for (ToDo todo : todoList) {
//                    Log.i(TAG, todo.getTitle() + todo.getDetail());
//                }
//                ToDoListAdapter adapter = new ToDoListAdapter(MainActivity.this, todoList);
//                mToDoRecyclerView.setAdapter(adapter);
//
//            }
//        });

        mProgressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = ApiClient.getClient();
        WebServices services = retrofit.create(WebServices.class);
        Call<GetToDoResponse> call = services.getAllTodo();
        call.enqueue(new MyRetrofitCallback<>(
                MainActivity.this,
                null,
                mProgressBar,
                new MyRetrofitCallback.MyRetrofitCallbackListener<GetToDoResponse>() {
                    @Override
                    public void onSuccess(GetToDoResponse responseBody) {
                        List<ToDo> toDoList = responseBody.data;
                        ToDoListAdapter adapter = new ToDoListAdapter(MainActivity.this, toDoList);
                        mToDoRecyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(String errorMessage) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Error")
                                .setMessage(errorMessage)
                                .setPositiveButton("OK", null)
                                .show();
                    }
                }
        ));

    }
}
