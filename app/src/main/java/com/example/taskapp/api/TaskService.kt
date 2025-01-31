package com.example.taskapp.api

import com.example.taskapp.model.Task
import com.example.taskapp.model.TasksIdResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TaskService {
    @POST("tasks.json")
    suspend fun add(@Body task: Task): TasksIdResponse

    @GET("tasks.json")
    suspend fun getAll(): Map<String, Task>

    @DELETE("tasks/{id}.json")
    suspend fun deleteTask(@Path("id") taskId: String)

    @PUT("tasks/{id}.json")
    suspend fun editTasks(
        @Path("id") taskId: String,
        @Body task: Task
    )
}