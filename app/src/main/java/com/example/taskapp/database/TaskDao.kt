package com.example.taskapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.taskapp.model.Task

@Dao
interface TaskDao {

     @Insert
     suspend fun insert(task : Task)

     @Upsert
     suspend fun insertAll(taskList: List<Task>)

     @Query("SELECT * FROM task")
     suspend fun getAll(): List<Task>

     @Delete
     suspend fun delete(task: Task)

     @Update
     suspend fun edit(task: Task)
}
