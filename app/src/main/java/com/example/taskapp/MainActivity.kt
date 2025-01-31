package com.example.taskapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskapp.ui.theme.TasksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TasksTheme() {
                Surface() {
                    Column() {
                        Text(text = "Text color")
                        Text(text = "Text")
                        Icon(imageVector = Icons.Default.Add, contentDescription = "")
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Button")
                        }
                        FloatingActionButton(onClick = { /*TODO*/ }) {
                            Text(text = "FAB")
                        }
                        TextField(value = "TextField", onValueChange = { })
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(value = "OutlinedTextField", onValueChange = {})
                        OutlinedTextField(value = "", onValueChange = {}, label = { Text("label") })

                        Checkbox(checked = false, onCheckedChange = {})
                        Checkbox(checked = true, onCheckedChange = {})
                        CircularProgressIndicator()
                        LinearProgressIndicator()
                        Switch(checked = false, onCheckedChange = {})
                        Switch(checked = true, onCheckedChange = {})
                        Divider()
                        Card() { Text("Card") }
                        Surface() { Text("Surface") }
                    }

                }
            }
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TasksTheme {
        Greeting("Android")
    }
}