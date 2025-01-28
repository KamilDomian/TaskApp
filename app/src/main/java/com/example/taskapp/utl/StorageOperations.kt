package com.example.taskapp.utl

import android.content.Context
import com.example.taskapp.model.Task

object StorageOperations {
    fun writeTaskList(context: Context, taskList: List<Task>){
        val json =  JsonConverter.taskListToJson(taskList)

        val sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE).edit()
        sharedPrefs.putString(TASK_LIST_KEY, json)
        sharedPrefs.apply()
    }
    fun readTaskList(context: Context): List<Task> {
        val sharedPref = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val json = sharedPref.getString(TASK_LIST_KEY, null)
        return if (json!=null) JsonConverter.taskListFromJson(json)else emptyList()
    }
    private const val SHARED_PREFS_NAME = "TASK_SHARED_PREFS"
    private  const val TASK_LIST_KEY = "TASK_LIST_KEY"
}


//GPTfun readTaskList(context: Context): List<Task> {
//    val sharedPref = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
//    val json = sharedPref.getString(TASK_LIST_KEY, null)
//
//    if (json.isNullOrBlank() || !json.startsWith("[")) { // Sprawdzamy, czy JSON wygląda jak lista
//        Log.e("STORAGE_ERROR", "Niepoprawne dane w SharedPreferences: $json")
//        return emptyList()
//    }
//
//    return try {
//        JsonConverter.taskListFromJson(json) // Parsowanie
//    } catch (e: Exception) {
//        Log.e("JSON_PARSING_ERROR", "Błąd parsowania JSON: ${e.message}")
//        emptyList() // Zwracamy pustą listę zamiast crasha
//    }
//}