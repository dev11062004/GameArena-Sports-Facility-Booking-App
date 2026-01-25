package com.example.helloworldapk.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.helloworldapk.data.Facility
import com.example.helloworldapk.data.FacilityType
import com.example.helloworldapk.data.repository.FacilityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class FacilitiesUiState(
    val facilities: List<Facility> = emptyList(),
    val selectedType: FacilityType? = null,
    val isLoading: Boolean = true
)

class FacilitiesViewModel(
    private val facilityRepository: FacilityRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FacilitiesUiState())
    val uiState: StateFlow<FacilitiesUiState> = _uiState.asStateFlow()

    init {
        loadFacilities()
    }

    fun filterByType(type: FacilityType?) {
        _uiState.value = _uiState.value.copy(selectedType = type)
        loadFacilities()
    }

    private fun loadFacilities() {
        viewModelScope.launch {
            val flow = _uiState.value.selectedType?.let {
                facilityRepository.getFacilitiesByType(it)
            } ?: facilityRepository.getAllFacilities()

            flow.collect { facilities ->
                _uiState.value = _uiState.value.copy(
                    facilities = facilities,
                    isLoading = false
                )
            }
        }
    }
}

class FacilitiesViewModelFactory(
    private val facilityRepository: FacilityRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FacilitiesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FacilitiesViewModel(facilityRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
