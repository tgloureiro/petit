package tech.tiagoloureiro.navigator

import tech.tiagoloureiro.bloc.Bloc


interface Route

sealed class NavigationEvent
object PopRoute : NavigationEvent()
data class Navigate(val route: Route) : NavigationEvent()


class NavigatorBloc(initialRoute: Route) : Bloc<Route, NavigationEvent>(initialRoute) {


    private val stack = RouteStack(
        initialRoute
    )

    override fun mapEventToState(event: NavigationEvent, state: Route): Route {
        return when (event) {
            is Navigate -> {
                stack.push(event.route)
                event.route
            }
            PopRoute -> {
                when(stack.pop()){
                    is Popped -> stack.current()
                    StackOnMinimum -> {
                        //Couldn't pop anymore (navigator needs at least one route)
                        state
                    }
                }
            }
        }
    }

}