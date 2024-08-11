package com.example.mobiletreasurehunt.ui.theme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MobileViewModel: ViewModel() {
    //MobileUIState
    private val _uiState = MutableStateFlow(MobileUIState())
}