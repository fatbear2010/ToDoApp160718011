package com.stefsk.todoapp160718011.view

import android.view.View
import android.widget.CompoundButton
import com.stefsk.todoapp160718011.model.Todo

interface TodoCheckedChangeListener {
    fun onCheckChanged(cb: CompoundButton,
                       isChecked:Boolean,
                       obj: Todo
    )
}
interface TodoEditClick {
    fun onTodoEditClick(v: View)
}

interface RadioClick {
    fun onRadioClick(v:View, priority:Int, obj:Todo)
}

interface TodoSaveChangesClick {
    fun onTodoSaveChangesClick(v: View, obj: Todo)
}
