package com.geektach.kotlin2.presentation.ui.mainActivity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.geektach.kotlin2.core.UiState
import com.geektach.kotlin2.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initClickers()
        logicClickers()
    }

    private fun initClickers() {

//        binding.btnSave.setOnClickListener {
//            i++
//            viewModel.addNote(Note(i.toString(), i.toString()))
//        }
//
//        binding.btnDelete.setOnClickListener {
//            i--
//            viewModel.deleteNote(Note(i.toString(), i.toString()))
//        }
        binding.fabAdd.setOnClickListener {

        }
    }

    private fun logicClickers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.addNotesState.collect {
                    when (it) {
                        is UiState.Error -> {
                            Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
                        }
                        is UiState.Loading -> {
                            Toast.makeText(this@MainActivity, "Loading", Toast.LENGTH_SHORT).show()
                        }
                        is UiState.Success -> {
                            if (it.data) {
                                viewModel.getAllNotes()
                            }
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.deleteNoteState.collect {
                    when (it) {
                        is UiState.Error -> {
                            Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
                        }
                        is UiState.Loading -> {
                            Toast.makeText(this@MainActivity, "Loading", Toast.LENGTH_SHORT).show()
                        }
                        is UiState.Success -> {
                            viewModel.getAllNotes()
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.notesState.collect {
                    when (it) {
                        is UiState.Error -> {
                            Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
                        }
                        is UiState.Loading -> {
                            Toast.makeText(this@MainActivity, "Loading", Toast.LENGTH_SHORT).show()
                        }
                        is UiState.Success -> {
                        }
                    }
                }
            }
        }
    }
}
