package com.example.trenerskaya_23.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trenerskaya_23.R
import com.example.trenerskaya_23.databinding.FragmentHomeBinding
import com.example.trenerskaya_23.ui.home.adapter.ClientAdapter
import com.example.trenerskaya_23.ui.home.adapter.TrainingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var clientAdapter: ClientAdapter
    private lateinit var trainingAdapter: TrainingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViews()
        setupBmiCalculator()
        observeViewModel()
    }

    private fun setupRecyclerViews() {
        // Настройка списка клиентов
        clientAdapter = ClientAdapter()
        binding.clientsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = clientAdapter
        }

        // Настройка списка тренировок
        trainingAdapter = TrainingAdapter()
        binding.trainingsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = trainingAdapter
        }

        // Настройка кнопок добавления
        binding.addClientButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddClientFragment())
        }

        binding.addTrainingButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddTrainingFragment())
        }
    }

    private fun setupBmiCalculator() {
        binding.calculateButton.setOnClickListener {
            val height = binding.heightInput.editText?.text.toString().toIntOrNull()
            val weight = binding.weightInput.editText?.text.toString().toDoubleOrNull()

            if (height != null && weight != null) {
                viewModel.calculateBMI(height, weight)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.trainer.observe(viewLifecycleOwner) { trainer ->
            trainer?.let {
                binding.toolbar.title = "Привет, ${it.name}!"
            }
        }

        viewModel.clients.observe(viewLifecycleOwner) { clients ->
            (binding.clientsRecyclerView.adapter as ClientAdapter).submitList(clients)
        }

        viewModel.trainings.observe(viewLifecycleOwner) { trainings ->
            (binding.trainingsRecyclerView.adapter as TrainingAdapter).submitList(trainings)
        }

        viewModel.bmiResult.observe(viewLifecycleOwner) { result ->
            binding.bmiResult.text = result
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}