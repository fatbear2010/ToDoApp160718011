package com.stefsk.todoapp160718011.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.stefsk.todoapp160718011.R
import com.stefsk.todoapp160718011.databinding.TodoItemLayoutBinding
import com.stefsk.todoapp160718011.model.Todo
import com.stefsk.todoapp160718011.vieModel.DetailToDoListViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class ToDoListAdapter(val todoList: ArrayList<Todo>,val adapterOnClick : (Any) -> Unit)
    :RecyclerView.Adapter<ToDoListAdapter.TodoViewHolder>(),TodoCheckedChangeListener,TodoEditClick
{

  //  class TodoViewHolder(var view: View): RecyclerView.ViewHolder(view)
    class TodoViewHolder(var view: TodoItemLayoutBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        val view = DataBindingUtil.inflate<TodoItemLayoutBinding>(inflater, R.layout.todo_item_layout, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        /*holder.view.imageEdit.setOnClickListener{
            val action = TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.checkTask.setText(todoList[position].title.toString())
        //print(todoList[position].title.toString())
        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked == true) {
                adapterOnClick(todoList[position])
            }
        }*/
        holder.view.todo = todoList[position]
        holder.view.listener = this

    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCheckChanged(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(isChecked) {
            adapterOnClick(obj)
        }

    }

    override fun onTodoEditClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = TodoListFragmentDirections.actionEditTodoFragment(uuid)

        Navigation.findNavController(v).navigate(action)
    }

}