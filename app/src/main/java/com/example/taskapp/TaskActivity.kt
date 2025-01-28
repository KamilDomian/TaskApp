package com.example.taskapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskapp.model.ColorType
import com.example.taskapp.model.Task
import com.example.taskapp.ui.theme.TaskAppTheme
import java.time.format.TextStyle

class TaskActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskView()

        }

    }

    @Composable
    fun TaskView() {
        val context = LocalContext.current
        val taskColors = ColorType.values()

        var currentColor by remember { mutableStateOf(taskColors.first()) }
        var descriptionText by remember { mutableStateOf("") }
        var titleText by remember { mutableStateOf("") }
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
                OutlinedTextField(
                    value = titleText,
                    onValueChange = { titleText = it },
                    label = { Text(text = "Tytuł zadania") },
                    //textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                OutlinedTextField(
                    value = descriptionText,
                    onValueChange = { descriptionText = it },
                    label = { Text(text = "Treść zadania") },
                    //textStyle = TextStyle(fontSize= 20.sp,fontWeight=FontWeight.Bold)
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
                    val task = Task(
                        title = titleText,
                        description = descriptionText,
                        colorType = currentColor
                    )
                    val intent = Intent(context, HomeActivity::class.java)
                    intent.putExtra("task", task)
                    startActivity(intent)

                    finish()


                }


            ) {
                Text(text = "Zapisz")
            }
        }


    }
}



