package com.example.taskapp.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import java.io.Serializable
import java.util.UUID
@Entity
@JsonClass(generateAdapter = true)

data class Task(
    val title: String,
    val description: String,
    val colorType: ColorType,
   @PrimaryKey val id: String = UUID.randomUUID().toString()
) : Serializable

enum class ColorType(val color: Color) {
    GREEN(Color.Green),
    YELLOW(Color.Yellow),
    MAGENTA(Color.Magenta)
}