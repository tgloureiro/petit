package tech.tiagoloureiro.navigator


/*
 * Has at least one current route/doesn't pops to empty
 */

sealed class PopResult
data class Popped(val poppedRoute: Route) : PopResult()
object StackOnMinimum : PopResult()

class RouteStack(initialRoute: Route) {
    private val stack = mutableListOf(initialRoute)

    /*
     * Push a [CompleteNavRoute] to the path
     */
    fun push(route: Route){
        stack.add(route)
    }

    /*
     * Returns the removed NavRoute
     */
    fun pop(): PopResult {
        return if(stack.size > 1){
            val removed = stack.removeLast()
            Popped(removed)
        }else{
            StackOnMinimum
        }
    }

    fun current() : Route = stack.last()
}