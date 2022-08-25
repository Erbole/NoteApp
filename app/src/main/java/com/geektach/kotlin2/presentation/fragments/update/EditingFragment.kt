package com.geektach.kotlin2.presentation.fragments.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.geektach.kotlin2.databinding.FragmentEditingBinding
import com.geektach.kotlin2.domain.model.room.Note
import com.geektach.kotlin2.extencion.showToast
import com.geektach.kotlin2.presentation.fragments.main.MainFragment

class EditingFragment : Fragment() {

    private lateinit var binding: FragmentEditingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val note = arguments?.getSerializable(MainFragment.keyNote) as Note
        binding.editTitle.setText(note.title)
        binding.editDesc.setText(note.description)

        binding.btnSave.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val desc = binding.editDesc.text.toString()
            when {
                title.isEmpty() -> {
                    requireContext().showToast("title empty")
                }
                desc.isEmpty() -> {
                    requireContext().showToast("desc empty")
                }
                else -> {
                    val bundle = Bundle()
                    bundle.putSerializable(
                        editingKey, Note(id = note.id, title = title, description = desc)
                    )
                    parentFragmentManager.setFragmentResult("2", bundle)
                    findNavController().navigateUp()
                }
            }
        }
    }
    companion object {
        const val editingKey = "editingKey"
    }
}















