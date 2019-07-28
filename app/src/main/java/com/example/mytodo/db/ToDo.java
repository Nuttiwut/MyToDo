package com.example.mytodo.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "todo")
public class ToDo implements Serializable {

    //ตั้งฟิว 4 ตัว
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String title;

    @ColumnInfo(name = "detail")
    @SerializedName("details")
    private String detail;

    @ColumnInfo(name = "checked")
    @SerializedName("finished")
    private boolean checked;

//    @ColumnInfo(name = "due_date")
//    private Date dueDate;

    //Gen geter และ Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    //    public Date getDueDate() {
//        return dueDate;
//    }
//
//    public void setDueDate(Date dueDate) {
//        this.dueDate = dueDate;
//    }
}
