package com.example.trenerskaya_23.ui.training.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.trenerskaya_23.databinding.FragmentTrainingListBinding
import com.example.trenerskaya_23.ui.training.adapter.TrainingsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrainingListFragment : Fragment() {
    private var _binding: FragmentTrainingListBinding? = null
    private val binding get() = _binding!!
    // private val viewModel: TrainingListViewModel by viewModels() // Закомментировано

    private val trainingsAdapter = TrainingsAdapter { training ->
        // TODO: Раскомментировать навигацию после исправления ViewModel и SafeArgs
        /* findNavController().navigate(
            TrainingListFragmentDirections.actionTrainingListFragmentToViewTrainingFragment(training.id)
        ) */
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrainingListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.trainingsRecyclerView.adapter = trainingsAdapter

        // TODO: Раскомментировать после исправления ViewModel
        /* binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshData()
        } */

        binding.addTrainingButton.setOnClickListener {
            // TODO: Раскомментировать навигацию после исправления ViewModel и SafeArgs
            /* findNavController().navigate(
                TrainingListFragmentDirections.actionTrainingListFragmentToAddTrainingFragment()
            ) */
        }

        // TODO: Раскомментировать после исправления ViewModel
        /* viewModel.trainings.observe(viewLifecycleOwner) { trainings ->
            trainingsAdapter.submitList(trainings)
            binding.swipeRefresh.isRefreshing = false
        } */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 