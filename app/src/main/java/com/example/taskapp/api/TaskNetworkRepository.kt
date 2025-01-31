package com.example.taskapp.api

import com.example.taskapp.model.Task
import com.example.taskapp.model.TasksIdResponse


class TaskNetworkRepository (private val taskService:TaskService){


    suspend fun addTask(task : Task ): TasksIdResponse{
        return taskService.add(task)
    }
    suspend fun getAllTasks(): List<Task> {
        return taskService.getAll().map { element -> element.value.copy(id = element.key) }
    }

    suspend fun deleteTask(taskId: String){
        taskService.deleteTask(taskId)
    }

    suspend fun editTask(task: Task){
        taskService.editTasks(task.id, task)
    }
}