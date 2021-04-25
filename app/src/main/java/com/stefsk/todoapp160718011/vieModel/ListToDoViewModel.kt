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

class ListToDoViewModel (application: Application) :AndroidViewModel(application), CoroutineScope{
    val todoLD = MutableLiveData<List<Todo>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh()
    {
        loadingLD.value = true
        todoLoadErrorLD.value = false
        launch {
            val db = buildDB(getApplication())
                    //Room.databaseBuilder( getApplication(),  TodoDatabase::class.java , "newtododb").build() dari week 8
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }


    fun cleartask(todo:Todo){
        launch { //apapun yg di lauch nanti jadi thread yg berbeda tapi punya akses ke UI

            val db = buildDB(getApplication())
            //var db = Room.databaseBuilder(  getApplication(),  TodoDatabase::class.java, "newtododb"  ).build() dari week 8 diganti dengan util
            db.todoDao().idfonefun(todo.uuid,1)
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }
}