package com.golgeciarif.newsapp.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.core.view.children
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.golgeciarif.newsapp.databinding.FilterDialogBinding
import com.golgeciarif.newsapp.model.Languages
import com.golgeciarif.newsapp.util.DateRange
import com.golgeciarif.newsapp.util.DateRange.LastOneWeek.convertDateToString
import com.golgeciarif.newsapp.util.Filters
import com.golgeciarif.newsapp.util.Language
import com.golgeciarif.newsapp.util.NewOrder
import com.golgeciarif.newsapp.util.NewOrder.Relevance.convertOrderToString
import com.golgeciarif.newsapp.util.NewsDates
import com.golgeciarif.newsapp.util.NewsLanguages
import com.golgeciarif.newsapp.util.NewsSortBy
import com.golgeciarif.newsapp.viewmodel.ExploreViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.launch

class FilterDialogFragment: DialogFragment() {


    private val viewModel: ExploreViewModel by activityViewModels()
    private var _binding: FilterDialogBinding? = null
    private val binding get() = _binding!!

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout((resources.displayMetrics.widthPixels * 0.8).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FilterDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeComponents()

        lifecycleScope.launch {
            viewModel.filterState.collect { filters ->
                updateUI(filters)
            }
        }

        binding.buttonCancel.setOnClickListener {
            dismiss()
        }

        binding.buttonApply.setOnClickListener {

            applyFilters()
            dismiss()
        }

    }

    private fun updateUI(filters: Filters) {
        binding.sfSortBy.setSelection(getPosition(binding.sfSortBy, filters.orderType.convertOrderToString()))
        binding.sfDate.setSelection(getPosition(binding.sfDate, filters.dateRange.convertDateToString()))

        binding.chipGroup.children.filterIsInstance<Chip>().forEach { chip ->
            chip.isChecked = chip.tag == (filters.language as? Language.Choosen)?.code
        }
    }

    private fun applyFilters() {
        val selectedLanguage = binding.chipGroup.children.filterIsInstance<Chip>().firstOrNull { it.isChecked }?.let {
            Language.Choosen(NewsLanguages.getLanguageByName(it.text.toString()))
        } ?: Language.Default

        val selectedDate = DateRange.convertStringToDate(binding.sfDate.selectedItem.toString())
        val selectedSortBy = NewOrder.convertStringToNewOrder(binding.sfSortBy.selectedItem.toString())

        viewModel.updateFilters(Filters(selectedSortBy, selectedDate, selectedLanguage))
    }

    private fun initializeSpinner(spinner: Spinner, items: List<String>) {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            items
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spinner.adapter = adapter
    }

    private fun initializeChips(chipGroup: ChipGroup, filters: List<Languages>) {
        filters.forEach { filter ->
            val chip = Chip(requireContext()).apply {
                text = filter.name
                isCheckable = true
                tag = filter.code
            }
            chipGroup.addView(chip)
        }
    }
    private fun getPosition(spinner: Spinner, value: String): Int {
        return (spinner.adapter as ArrayAdapter<String>).getPosition(value)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initializeComponents() {
        initializeSpinner(binding.sfSortBy, NewsSortBy.sortByCriterias.map { it.name })
        initializeSpinner(binding.sfDate, NewsDates.dateRange.map { it.name })
        initializeChips(binding.chipGroup, NewsLanguages.languages)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

