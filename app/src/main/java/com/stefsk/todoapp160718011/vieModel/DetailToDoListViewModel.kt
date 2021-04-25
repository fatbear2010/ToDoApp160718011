package com.stefsk.todoapp160718011.vieModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.stefsk.todoapp160718011.model.Todo
import com.stefsk.todoapp160718011.model.TodoDatabase
import com.stefsk.todoapp160718011.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailToDoListViewModel (application: Application): AndroidViewModel(application), CoroutineScope {
    private val job = Job()
    val todoLD = MutableLiveData<Todo>()

    fun addTodo(list:List<Todo>) {
        launch {
            val db = buildDB(getApplication())
                //Room.databaseBuilder( getApplication(), TodoDatabase::class.java, "newtododb").build()db.todoDao().insertAll(*list.toTypedArray()) // dari week 8
        }
    }

    fun fetch(uuid:Int)
    {
        launch{
            val db = buildDB(getApplication())
            todoLD.value = db.todoDao().selectTodo(uuid)
        }
    }

    fun update(title:String, notes:String, priority:Int, uuid:Int)
    {
        launch {
            val db = buildDB(getApplication())
            db.todoDao().update(title,notes,priority,uuid)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}