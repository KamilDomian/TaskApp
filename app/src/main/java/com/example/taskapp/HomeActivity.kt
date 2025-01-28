package com.example.taskapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskapp.api.ServiceConfiguration
import com.example.taskapp.api.TaskNetworkRepository
import com.example.taskapp.model.Task
import com.example.taskapp.utl.StorageOperations
import kotlinx.coroutines.runBlocking


var taskList = mutableListOf<Task>()
val taskNetworkRepository = TaskNetworkRepository(ServiceConfiguration.taskService)

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        runBlocking {
            try {
                taskList = taskNetworkRepository.getAllTasks().toMutableList()
            }
            catch (e:Exception){
                Log.e("MyTaskApp", "Network get all tasks: $e")
            }

       }

        //taskList=StorageOperations.readTaskList(this).toMutableList()

        //val welcomeValue: String? = intent.getStringExtra("welcome_value")


        val task = intent.getSerializableExtra("task") as? Task
        task?.let {
            //Toast.makeText(this, "task:, $task", Toast.LENGTH_LONG).show()
            taskList.add(task)
            StorageOperations.writeTaskList(this, taskList)

            runBlocking {
                taskNetworkRepository.addTask(task)
            }


        }
        setContent {
            //HomeText()
            HomeView()
        }
    }

    @Composable
    fun TaskListView() {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = " Lista TODO xD ",
                fontSize = 30.sp


            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)

            )
            {
                items(items = taskList) { task ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = task.colorType.color),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = task.title,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = task.description,
                                modifier = Modifier.fillMaxWidth()
                            )


                        }

                    }
                }

            }

        }
    }

    @Composable
    fun HomeView() {
        val context = LocalContext.current
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            //TaskListView()
            if(taskList.isEmpty()){
                Text(
                    text = "Lista jest pusta",
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else{
                TaskListView()
            }
            FloatingActionButton(
                onClick = {
                    val intent = Intent(context, TaskActivity::class.java)
                    startActivity(intent)

                    finish()
                },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd)


            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    }

    @Composable
    fun HomeText() {
        Text(text = "Witaj w domu")
    }
}
