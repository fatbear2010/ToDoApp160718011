package com.stefsk.todoapp160718011.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.stefsk.todoapp160718011.R
import com.stefsk.todoapp160718011.model.Todo
import com.stefsk.todoapp160718011.vieModel.DetailToDoListViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*


class CreateTodoFragment : Fragment() {
    private lateinit var viewModel:DetailToDoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailToDoListViewModel::class.java)
        btnCreateTodo.setOnClickListener {
            var radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
            var todo = Todo(txtTitle.text.toString(), txtNotes.text.toString(), radio.tag.toString().toInt(),0)
            //var todo = Todo(txtTitle.text.toString(), txtNotes.text.toString()) dari week 8
            val list = listOf(todo)
            viewModel.addTodo(list)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack() // cara destroy dan back
        }
    }
}