package com.example.mytodo.db;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class ToDoRepository {

//    private static final String TAG = ToDoRepository.class.getName();

    private Context mContext;

    public ToDoRepository(Context context) {
        this.mContext = context;

    }

    public void addToDo(String title, String details)
    {
        AddToDoTask addTask = new AddToDoTask(mContext);
        ToDo todo = new ToDo();
        todo.setTitle(title);
        todo.setDetail(details);
        addTask.execute(todo);
    }

    public List<ToDo> getAllToDo(CallBack callback) {
        GetToDoTask getTask = new GetToDoTask(mContext, callback);
        getTask.execute();
        return null;
    }

    public void updateToDo(int id, String title, String details) {
        UpdateToDoTask updateTask = new UpdateToDoTask(mContext);
        ToDo todo = new ToDo();
        todo.setId(id);
        todo.setTitle(title);
        todo.setDetail(details);
        updateTask.execute(todo);
    }

    public void deleteToDo(int id) {
        DeleteToDoTask deleteTask = new DeleteToDoTask(mContext);
        ToDo todo = new ToDo();
        todo.setId(id);
        deleteTask.execute(todo);
    }

    private static class UpdateToDoTask extends AsyncTask<ToDo, Void, Void> {

        private Context mContext;

        UpdateToDoTask(Context context) {
            this.mContext = context;

        }

        @Override
        protected Void doInBackground(ToDo... ToDos) {
            AppDatabase db = AppDatabase.getInstance(mContext);

            for (ToDo ToDo : ToDos) {
                db.toDoDao().update(ToDo);
            }

            return null;
        } // ปิด doInBackground
    }// ปิด

    private static class DeleteToDoTask extends AsyncTask<ToDo, Void, Void> {

        private Context mContext;

        DeleteToDoTask(Context context) {
            this.mContext = context;

        }

        @Override
        protected Void doInBackground(ToDo... ToDos) {
            AppDatabase db = AppDatabase.getInstance(mContext);

            for (ToDo ToDo : ToDos) {
                db.toDoDao().delete(ToDo);
            }

            return null;
        } // ปิด doInBackground
    }// ปิด

    private static class AddToDoTask extends AsyncTask<ToDo, Void, Void> {

        private Context mContext;

        AddToDoTask(Context context) {
            this.mContext = context;

        }

        @Override
        protected Void doInBackground(ToDo... ToDos) {
            AppDatabase db = AppDatabase.getInstance(mContext);

            for (ToDo ToDo : ToDos) {
                db.toDoDao().insert(ToDo);
            }

            return null;
        } // ปิด doInBackground
    }// ปิด

    private static class GetToDoTask extends AsyncTask<Void, Void, List<ToDo>> {

        private Context mContext;
        private CallBack mCallback;

        GetToDoTask(Context context, CallBack callback) {
            this.mContext = context;
            this.mCallback = callback;

        }

        @Override
        protected List<ToDo> doInBackground(Void... ToDos) {
            AppDatabase db = AppDatabase.getInstance(mContext);

            return db.toDoDao().getAll();
        } // ปิด doInBackground

        @Override
        protected void onPostExecute(List<ToDo> toDos) {
            super.onPostExecute(toDos);

            mCallback.onGetTodo(toDos);
        }
    }// ปิด

    public interface CallBack {
        void onGetTodo(List<ToDo> todoList);
    }

}
