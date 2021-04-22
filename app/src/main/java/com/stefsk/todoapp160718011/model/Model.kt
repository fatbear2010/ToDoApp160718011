package com.stefsk.todoapp160718011.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo(name = "title") // opsional untuk ganti nama kolom di table
    var title:String,
    @ColumnInfo(name = "notes")
    var notes:String
)
{
    @PrimaryKey(autoGenerate = true)
    var uuid:Int = 0
}