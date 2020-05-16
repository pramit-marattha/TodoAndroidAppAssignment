package com.np.pramitmarattha.database;
import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public  class TaskEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description;
    private String taskDate;
    private int priority;
    @ColumnInfo(name = "updated_at")
    private Date updatedAt;

    public TaskEntry(int id, String description, String taskDate, int priority, Date updatedAt) {
        this.id = id;
        this.description = description;
        this.taskDate = taskDate;
        this.priority = priority;
        this.updatedAt = updatedAt;
    }

    @Ignore
    public TaskEntry(String description, String  taskDate, int priority, Date updatedAt) {
        this.description = description;
        this.taskDate = taskDate;
        this.priority = priority;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
