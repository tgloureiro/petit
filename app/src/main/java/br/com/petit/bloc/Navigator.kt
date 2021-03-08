package br.com.petit.bloc

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class NavigationRoute
object PopBackStack: NavigationRoute()
object Main: NavigationRoute()
data class Details(val petId: Long): NavigationRoute()
data class SuccessfulAdoption(val petId: Long): NavigationRoute()


class Navigator @Inject constructor(
    private val scope : CoroutineScope
)  {
    private val _route = MutableSharedFlow<NavigationRoute>()
    val route: SharedFlow<NavigationRoute> = _route

    fun navigateTo(navigationRoute: NavigationRoute){
        scope.launch {
            _route.emit(navigationRoute)
        }
    }
}