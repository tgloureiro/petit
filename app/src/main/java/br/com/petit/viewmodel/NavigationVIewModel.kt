package br.com.petit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import br.com.petit.model.Pet
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class NavigationRoute
object PopBackStack: NavigationRoute()
object Main: NavigationRoute()
data class Details(val petId: Long): NavigationRoute()
data class SuccessfulAdoption(val petId: Long): NavigationRoute()

class NavigationViewModel() : ViewModel() {

    private val _route = MutableSharedFlow<NavigationRoute>()
    val route: SharedFlow<NavigationRoute> = _route

    fun navigateTo(navigationRoute: NavigationRoute){
        viewModelScope.launch {
            _route.emit(navigationRoute)
        }
    }
}
