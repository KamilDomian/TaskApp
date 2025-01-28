package com.example.taskapp.api

import com.example.taskapp.model.Task


class TaskNetworkRepository (private val taskService:TaskService){


    suspend fun addTask(task : Task ){

        taskService.add(task)
    }
    suspend fun getAllTasks(): List<Task> {
        return taskService.getAll().values.toList()
    }
}