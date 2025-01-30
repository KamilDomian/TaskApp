package com.example.taskapp.database

import androidx.room.Insert
import com.example.taskapp.model.Task

interface TaskDao {
    @Insert
     suspend fun insert(task : Task)
}
