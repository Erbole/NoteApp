package com.geektach.kotlin2.presentation.fragments.update

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geektach.kotlin2.R
import com.geektach.kotlin2.databinding.FragmentUpdateBinding
import com.geektach.kotlin2.domain.model.Note
import com.geektach.kotlin2.presentation.fragments.main.MainFragment

class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val note = arguments?.getSerializable(MainFragment.keyNote) as Note

    }
}