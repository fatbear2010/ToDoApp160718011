package com.stefsk.todoapp160718011.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.stefsk.todoapp160718011.R
import com.stefsk.todoapp160718011.model.Todo
import com.stefsk.todoapp160718011.vieModel.ListToDoViewModel
import kotlinx.android.synthetic.main.fragment_todo_list.*


class TodoListFragment : Fragment() {
    private lateinit var viewModel: ListToDoViewModel
    private val todoListAdapter = ToDoListAdapter(arrayListOf(),{ item -> doClick(item) })

    fun doClick(item:Any) {
        viewModel.cleartask(item as Todo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListToDoViewModel::class.java)
        viewModel.refresh()

        recViewTodo.layoutManager = LinearLayoutManager(context)
        recViewTodo.adapter = todoListAdapter

        fabAddTodo.setOnClickListener {
            val action = TodoListFragmentDirections.actionCreateTodo()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            viewModel.todoLD.observe(viewLifecycleOwner, Observer {
                todoListAdapter.updateTodoList(it)
                if(it.isEmpty()) {
                    txtEmpty.visibility = View.VISIBLE
                } else {
                    txtEmpty.visibility = View.GONE
                }
            })

        })
    }
}