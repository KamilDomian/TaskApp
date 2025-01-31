package com.example.taskapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskapp.model.ColorType
import com.example.taskapp.model.Task
import com.example.taskapp.model.TaskOperationStatus
import com.example.taskapp.ui.theme.Blue
import com.example.taskapp.ui.theme.TasksTheme
import com.example.taskapp.viewmodel.TaskViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskActivity : ComponentActivity() {

    val taskViewModel by viewModel<TaskViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val editTask: Task? = intent.getSerializableExtra("edit_task") as? Task


        setContent {
            TasksTheme() {
                Surface (Modifier.fillMaxSize()) {
                    TaskView(editTask)
                    observeAddTaskStatus()
                }
            }
        }
    }

    private fun observeAddTaskStatus() {
        when (taskViewModel.addEditTaskStatus) {
            TaskOperationStatus.SUCCESS -> {
                val intent = Intent(this, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                startActivity(intent)
                finish()
            }
            TaskOperationStatus.ERROR -> {
                Toast.makeText(this, "Problem z połączeniem internetowym", Toast.LENGTH_LONG).show()
            }
            TaskOperationStatus.LOADING, TaskOperationStatus.UNKNOWN -> {}
        }
    }

    @Composable
    fun TaskView(editTask: Task?) {
        val context = LocalContext.current
        val taskColors = ColorType.values()

        var currentColor by rememberSaveable { mutableStateOf(editTask?.colorType ?:taskColors.first()) }
        var descriptionText by rememberSaveable { mutableStateOf(editTask?.description ?:"") }
        var titleText by rememberSaveable { mutableStateOf(editTask?.title ?:"") }
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text(
                text = "Dodaj zadanie",
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(10.dp)


            )
            Spacer(modifier = Modifier.heightIn(20.dp))


            Card(
                colors = CardDefaults.cardColors(containerColor = currentColor.color),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
            ) {
                val outlinedTextStyle = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )
                val outlinedTextFieldColor = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Blue,
                    unfocusedLabelColor = Color.Gray
                )

                OutlinedTextField(
                    value = titleText,
                    onValueChange = { titleText = it },
                    label = { Text(text = "Tytuł zadania") },
                    textStyle = outlinedTextStyle,
                    colors = outlinedTextFieldColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                OutlinedTextField(
                    value = descriptionText,
                    onValueChange = { descriptionText = it },
                    label = { Text(text = "Treść zadania") },
                    textStyle = outlinedTextStyle,
                    colors = outlinedTextFieldColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))


            LazyRow() {
                items(items = taskColors) { colorType ->
                    Button(
                        onClick = { currentColor = colorType },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = colorType.color),
                        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 10.dp),
                        border = BorderStroke(
                            4.dp,
                            if (currentColor == colorType) Color.Black else colorType.color
                        ),
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .size(45.dp)
                    ) { }


                }
            }
            Spacer(modifier = Modifier.height(40.dp))


            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (editTask == null) {
                        val task = Task(
                            title = titleText,
                            description = descriptionText,
                            colorType = currentColor
                        )

                        taskViewModel.addTask(task)

                    } else {
                        val task = editTask.copy(
                            title = titleText,
                            description = descriptionText,
                            colorType = currentColor
                        )
                        taskViewModel.editTask(task)
                    }
                }


            ) {
                if (taskViewModel.addEditTaskStatus == TaskOperationStatus.LOADING) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
                } else {
                    Text(text = "Zapisz")
                }
            }
            Image(painter = painterResource(R.drawable.image), contentDescription = null)
        }

    }
}



