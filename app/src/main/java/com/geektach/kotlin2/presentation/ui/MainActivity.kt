package com.geektach.kotlin2.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.geektach.kotlin2.databinding.ActivityMainBinding
import com.geektach.kotlin2.domain.model.Note

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        var i = 0
        binding.btnSave.setOnClickListener {
            i++
            viewModel.addNote(Note(i.toString(), i.toString()))
        }

        binding.btnDelete.setOnClickListener {
            viewModel.deleteNote(Note(i.toString(), i.toString()))
        }

        viewModel.liveData.observe(this) {
            binding.etNote.text = it.toString()
        }
    }
}