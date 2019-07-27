package com.example.mytodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mytodo.adapter.ToDoListAdapter;
import com.example.mytodo.db.AppDatabase;
import com.example.mytodo.db.ToDo;
import com.example.mytodo.db.ToDoRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    private RecyclerView mToDoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToDoRecyclerView = findViewById(R.id.todo_recycler_view);
        mToDoRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        FloatingActionButton fab = findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddToDoActivity.class);
                startActivity(intent);
            }
        });

        //เรียกใช้
//        GetToDoTask getTask = new GetToDoTask(MainActivity.this);
//        getTask.execute();

    }


    @Override
    protected void onResume() {
        super.onResume();

        ToDoRepository repo = new ToDoRepository(MainActivity.this);
//        repo.addToDo("aaaa", "bbbbb");

        repo.getAllToDo(new ToDoRepository.CallBack() {
            @Override
            public void onGetTodo(List<ToDo> todoList) {

                for (ToDo todo : todoList) {
                    Log.i(TAG, todo.getTitle() + todo.getDetail());
                }
                ToDoListAdapter adapter = new ToDoListAdapter(MainActivity.this, todoList);
                mToDoRecyclerView.setAdapter(adapter);

            }
        });
    }
}
