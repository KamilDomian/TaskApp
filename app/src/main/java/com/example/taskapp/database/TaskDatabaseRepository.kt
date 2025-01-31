package com.example.taskapp.database

import com.example.taskapp.model.Task

class TaskDatabaseRepository(private val db: AppDatabase) {
    suspend fun insertTask(task: Task){
        db.taskDao().insert(task)
    }

    suspend fun insertAllTasks(taskList: List<Task>){
        db.taskDao().insertAll(taskList)
    }
    suspend fun getAllTasks(): List<Task>{
        return db.taskDao().getAll()
    }
}