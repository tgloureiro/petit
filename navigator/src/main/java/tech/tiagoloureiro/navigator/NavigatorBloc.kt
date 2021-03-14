package tech.tiagoloureiro.navigator

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import tech.tiagoloureiro.bloc.Bloc


interface Route

sealed class NavigationEvent
object PopRoute : NavigationEvent()
data class Navigate(val route: Route) : NavigationEvent()


class NavigatorBloc(initialRoute: Route) : Bloc<Route, NavigationEvent>(
    initialRoute
) {


    private val stack = RouteStack(
        initialRoute
    )

    override fun mapEventToState(
        event: NavigationEvent,
        state: Route,
        emit: (state: Route) -> Unit
    ) {

    }

    /*override suspend fun mapEventToState(
        event: NavigationEvent,
        state: Route,
        flow: MutableSharedFlow<Route>
    ) {
        when (event) {
            is Navigate -> {
                stack.push(event.route)
                flow.emit(event.route)
            }
            PopRoute -> {
                when(stack.pop()){
                    is Popped ->
                        flow.emit(stack.current())
                    StackOnMinimum -> {
                        //Couldn't pop anymore (navigator needs at least one route)
                        flow.emit(state)
                    }
                }
            }
        }
    }*/


}