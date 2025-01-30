package com.example.taskapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.taskapp.model.Task
@Dao
interface TaskDao {
    @Insert
     suspend fun insert(task : Task)
@Query("SELECT * FROM task")
     suspend fun getAll(): List<Task>
}
