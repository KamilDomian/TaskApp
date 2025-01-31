package com.example.taskapp

import android.app.Application
import android.util.Log

class TasksAplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("MyTaskApp", "Applicaton onCreate()")
    }
}