package com.stefsk.todoapp160718011.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.stefsk.todoapp160718011.R
import com.stefsk.todoapp160718011.databinding.FragmentEditTodoBinding
import com.stefsk.todoapp160718011.model.Todo
import com.stefsk.todoapp160718011.vieModel.DetailToDoListViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*


class EditTodoFragment : Fragment() , RadioClick,TodoSaveChangesClick{
    private lateinit var viewModel: DetailToDoListViewModel
    private lateinit var dataBinding: FragmentEditTodoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate<FragmentEditTodoBinding> (inflater, R.layout.fragment_edit_todo, container, false)
        //return inflater.inflate(R.layout.fragment_edit_todo, container, false)
     return dataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailToDoListViewModel::class.java)
        textViewJudul.text = "Edit Todo"
        btnCreateTodo.text = "Save Changes"

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()

        btnCreateTodo.setOnClickListener {
            val radio =  view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(), radio.tag.toString().toInt(), uuid)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
    }

    fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            /*txtTitle.setText(it.title)
            txtNotes.setText(it.notes)
            when (it.priority) {
                1 -> radioLow.isChecked = true
                2 -> radioMedium.isChecked = true
                else -> radioHgh.isChecked = true
            }*/
            dataBinding.todo = it
        })

    }

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }

    override fun onTodoSaveChangesClick(v: View, obj: Todo) {
        viewModel.update(obj.title, obj.notes, obj.priority, obj.uuid)
        Toast.makeText(v.context, "Todo Updated", Toast.LENGTH_SHORT).show()

    }

}