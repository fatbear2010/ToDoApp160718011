package com.stefsk.todoapp160718011.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stefsk.todoapp160718011.R
import com.stefsk.todoapp160718011.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class ToDoListAdapter(val todoList: ArrayList<Todo>,val adapterOnClick : (Any) -> Unit)
    :RecyclerView.Adapter<ToDoListAdapter.TodoViewHolder>(){

    class TodoViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.view.checkTask.setText(todoList[position].title.toString())
        print(todoList[position].title.toString()+"------------------------------------------------------------------")
        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, b ->
            adapterOnClick(todoList[position])
        }

    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

}