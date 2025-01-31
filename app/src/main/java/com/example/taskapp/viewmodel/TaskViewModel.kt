package com.example.taskapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskapp.api.TaskNetworkRepository
import com.example.taskapp.database.TaskDatabaseRepository
import com.example.taskapp.model.Task
import com.example.taskapp.model.TaskOperationStatus
import com.example.taskapp.model.TasksIdResponse
import kotlinx.coroutines.launch

class TaskViewModel(
    private val taskDatabaseRepository: TaskDatabaseRepository,
    private val taskNetworkRepository: TaskNetworkRepository
) : ViewModel() {

    var taskList by mutableStateOf(emptyList<Task>())
    var addEditTaskStatus by mutableStateOf(TaskOperationStatus.UNKNOWN)
    var getAllTasksStatus by mutableStateOf(TaskOperationStatus.UNKNOWN)
    var sendSmsTaskStatus by mutableStateOf<Task?>(null)

    fun getAllTasks() {
        viewModelScope.launch {
            try {
                getAllTasksStatus = TaskOperationStatus.LOADING
                taskList = taskNetworkRepository.getAllTasks().toMutableList()
                taskDatabaseRepository.insertAllTasks(taskList)
                getAllTasksStatus = TaskOperationStatus.SUCCESS
            } catch (e: Exception) {
                taskList = taskDatabaseRepository.getAllTasks()
                getAllTasksStatus = TaskOperationStatus.ERROR
            }
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            try {
                addEditTaskStatus = TaskOperationStatus.LOADING
                val response: TasksIdResponse = taskNetworkRepository.addTask(task)
                taskDatabaseRepository.insertTask(task.copy(id = response.name))
                addEditTaskStatus = TaskOperationStatus.SUCCESS
            } catch (e: Exception) {
                addEditTaskStatus = TaskOperationStatus.ERROR
            }
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch {
            try {
                taskNetworkRepository.deleteTask(task.id)
                taskDatabaseRepository.deleteTask(task)
                removeTaskFromList(task)

            } catch (e: Exception){

            }
        }
    }

    private fun removeTaskFromList(task: Task){
     //   val mutableTaskList = taskList.toMutableList()
     //  mutableTaskList.remove(task)
     //   taskList = mutableTaskList
        taskList.toMutableList().also {
            it.remove(task)
            taskList = it
        }
    }
    fun editTask(task: Task){
        viewModelScope.launch {
            try {
                addEditTaskStatus = TaskOperationStatus.LOADING
                taskNetworkRepository.editTask(task)
                taskDatabaseRepository.editTask(task)
                addEditTaskStatus = TaskOperationStatus.SUCCESS
            } catch (e: Exception){
                addEditTaskStatus = TaskOperationStatus.ERROR
                
            }
        }
    }
}