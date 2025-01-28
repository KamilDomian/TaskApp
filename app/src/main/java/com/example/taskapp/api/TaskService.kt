package com.example.taskapp.api

import com.example.taskapp.model.Task
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TaskService {
    @POST("tasks.json")
    suspend fun add(@Body task: Task)

    @GET("tasks.json")
    suspend fun getAll(): Map<String, Task>
}