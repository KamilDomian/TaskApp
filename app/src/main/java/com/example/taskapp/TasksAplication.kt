package com.example.taskapp

import android.app.Application
import android.util.Log
import com.example.taskapp.api.ServiceConfiguration
import com.example.taskapp.api.TaskNetworkRepository
import com.example.taskapp.database.DatabaseConfiguration
import com.example.taskapp.database.TaskDatabaseRepository
import com.example.taskapp.viewmodel.TaskViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class TasksAplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("MyTaskApp", "Applicaton onCreate()")

        startKoin {
             androidContext(this@TasksAplication)
            modules (
                module {
                    single { DatabaseConfiguration.getDatabase(androidContext())}
                    factory { TaskDatabaseRepository(get())}

                    single { ServiceConfiguration.taskService }
                    factory {  TaskNetworkRepository(get()) }

                    viewModel { TaskViewModel(get(), get()) }
                }
            )
        }
    }
}
