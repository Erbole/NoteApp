package com.geektach.kotlin2.presentation.fragments.add_note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geektach.kotlin2.databinding.FragmentAddNoteBinding
import com.geektach.kotlin2.domain.model.room.Note
import com.geektach.kotlin2.extencion.showToast

class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickers()
    }

    private fun initClickers() {
        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDesc.text.toString()
            when {
                title.isEmpty() -> {
                    requireContext().showToast("title empty")
                }
                description.isEmpty() -> {
                    requireContext().showToast("desc empty")
                }
                else -> {
                    val bundle = Bundle()
                    bundle.putSerializable("key", Note(title = title, description = description))
                    parentFragmentManager.setFragmentResult("1", bundle)
                }
            }
        }
    }
}