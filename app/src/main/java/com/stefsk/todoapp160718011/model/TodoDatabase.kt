package com.stefsk.todoapp160718011.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stefsk.todoapp160718011.util.MIGRATION_1_2
import java.security.AccessControlContext

@Database(entities = arrayOf(Todo::class),version = 2)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object{
        @Volatile private var instance:TodoDatabase ?=null
        private var LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext ,
                TodoDatabase::class.java,
                "newtododb")
                .addMigrations(MIGRATION_1_2)
                .build()

        operator fun invoke(context: Context){
            if(instance!=null)
            {
                synchronized(LOCK){
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}
