package com.example.taskapp.api

import com.example.taskapp.model.Task
import com.example.taskapp.model.TasksIdResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TaskService {
    @POST("tasks.json")
    suspend fun add(@Body task: Task): TasksIdResponse

    @GET("tasks.json")
    suspend fun getAll(): Map<String, Task>
}