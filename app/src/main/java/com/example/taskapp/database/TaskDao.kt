package com.example.taskapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskapp.model.Task
@Dao
interface TaskDao {

    @Insert
     suspend fun insert(task : Task)

     @Insert(onConflict = OnConflictStrategy.REPLACE)
          suspend fun insertAll(taskList: List<Task>)

    @Query("SELECT * FROM task")
     suspend fun getAll(): List<Task>
}
