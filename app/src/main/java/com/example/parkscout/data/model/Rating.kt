package com.example.parkscout.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.PrimaryKey
import com.google.type.DateTime
import java.time.format.DateTimeFormatter

data class Rating (
    @PrimaryKey
    @ColumnInfo(name = "user_Id")
    val user_Id : String,
    val trainingSpotId : String,
    val rate : Int,
    @Embedded(prefix = "rating_") val rate_dateTime : DateTimeFormatter
)