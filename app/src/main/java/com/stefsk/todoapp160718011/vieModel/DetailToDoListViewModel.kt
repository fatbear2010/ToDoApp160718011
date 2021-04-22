package com.stefsk.todoapp160718011.vieModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.stefsk.todoapp160718011.model.Todo
import com.stefsk.todoapp160718011.model.TodoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailToDoListViewModel (application: Application): AndroidViewModel(application), CoroutineScope {
    private val job = Job()

    fun addTodo(list:List<Todo>) {
        launch {
            val db = Room.databaseBuilder(
                    getApplication(), TodoDatabase::class.java,
                    "newtododb").build()
            db.todoDao().insertAll(*list.toTypedArray())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}