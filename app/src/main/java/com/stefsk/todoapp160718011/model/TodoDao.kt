package com.stefsk.todoapp160718011.model

import androidx.room.*

@Dao // delarasi interface adalah dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) // jika conflik maka yg lama di replace
    suspend fun insertAll(vararg todo:Todo) // vararg atrinya parameter bisa lebih dari 1 to do //suspend berrti bisa di pause

    @Query("SELECT * FROM todo WHERE is_done = 0 ORDER BY priority DESC ")
    suspend fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid = :id")
    suspend fun selectTodo(id:Int):Todo

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("UPDATE todo SET title=:title, notes=:notes, priority=:priority WHERE uuid = :id")
    suspend fun update(title:String, notes:String, priority:Int, id:Int)

    @Query("UPDATE todo SET is_done=:is_done WHERE uuid = :id")
    suspend fun idfonefun(is_done:Int, id:Int)

}