package com.example.mytodo.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ToDo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "todo.db";

    public abstract ToDoDao toDoDao();
    private static AppDatabase mInstance;
    //รูปแบบการสร้างแบบ singleton คือสร้างออฟเจกครั้งแรกที่ถูกเรียก ถ้าเรียกอีกไม่ต้องสร้างแล้ว
    public static synchronized AppDatabase getInstance(Context context) {
        if (mInstance == null){
            mInstance = Room.databaseBuilder(
                    context,
                    AppDatabase.class,
                    DB_NAME
            ).build();

        }
        return mInstance;
    }



}
