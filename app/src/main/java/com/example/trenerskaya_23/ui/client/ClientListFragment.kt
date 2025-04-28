package com.example.trenerskaya_23.ui.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.trenerskaya_23.databinding.FragmentClientListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClientListFragment : Fragment() {
    private var _binding: FragmentClientListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 