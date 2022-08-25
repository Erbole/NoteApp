package com.geektach.kotlin2.presentation.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.geektach.kotlin2.R
import com.geektach.kotlin2.core.BaseFragment
import com.geektach.kotlin2.core.UiState
import com.geektach.kotlin2.databinding.FragmentMainBinding
import com.geektach.kotlin2.domain.model.room.Note
import com.geektach.kotlin2.extencion.invisible
import com.geektach.kotlin2.extencion.showToast
import com.geektach.kotlin2.extencion.visible
import com.geektach.kotlin2.presentation.ui.main_activity.MainViewModel
import com.geektach.kotlin2.presentation.ui.main_activity.NoteAdapter
import com.geektach.kotlin2.presentation.fragments.update.EditingFragment

class MainFragment : BaseFragment(), NoteAdapter.ItemClickListener {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener("2") { key, bundle ->
            val result = bundle.getSerializable("key")
            viewModel.addNote(result as Note)
        }

        setFragmentResultListener("2") { key, bundle ->
            val result = bundle.getSerializable(EditingFragment.editingKey)
            viewModel.editingNote(result as Note)
        }
        initClick()
        viewModel.getAllNotes()

        viewModel.addNotesState.subscribe {
            when (it) {
                is UiState.Error -> {
                    requireActivity().showToast(it.error)
                }
                is UiState.Loading -> {
                    binding.progress.visible()
                }
                is UiState.Success -> {
                    requireActivity().showToast("Add Note")
                    binding.progress.invisible()
                    viewModel.getAllNotes()
                }
            }
        }
        viewModel.notesState.subscribe {
            when (it) {
                is UiState.Error -> {
                    requireActivity().showToast(it.error)
                }
                is UiState.Loading -> {
                    binding.progress.visible()
                }
                is UiState.Success -> {
                    requireActivity().showToast("Get All Notes")
                    binding.progress.invisible()
                    adapter = NoteAdapter(it.data)
                    binding.recycler.adapter = adapter
                    adapter.setItemClickListener(this)
                }
            }
        }
        viewModel.deleteNoteState.subscribe {
            when (it) {
                is UiState.Error -> {
                    requireActivity().showToast(it.error)
                }
                is UiState.Loading -> {
                    binding.progress.visible()
                }
                is UiState.Success -> {
                    requireActivity().showToast("Delete Note")
                    binding.progress.invisible()
                    viewModel.getAllNotes()
                }
            }

        }
        viewModel.editingNoteState.subscribe {
            when (it) {
                is UiState.Error -> {
                    requireActivity().showToast(it.error)
                }
                is UiState.Loading -> {
                    binding.progress.visible()
                }
                is UiState.Success -> {
                    requireActivity().showToast("Update Note")
                    binding.progress.invisible()
                    viewModel.getAllNotes()
                }
            }
        }
    }

    private fun initClick() {
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.addNoteFragment)
        }
    }

    override fun onItemClick(note: Note) {
        val bundle = Bundle()
        bundle.putSerializable(keyNote, note)
        findNavController().navigate(R.id.editingFragment, bundle)
    }

    override fun onLongClickListener(note: Note) {
        viewModel.deleteNote(note)
    }
    companion object {
        const val keyNote = "keyNote"
    }

}