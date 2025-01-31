package com.example.taskapp.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TasksIdResponse(val name: String)
