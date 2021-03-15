package tech.tiagoloureiro.bloc

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class Bloc<State, Event>(initialState: State,
                                  private val scope: CoroutineScope
) {
    private val _state = MutableStateFlow(initialState)
    private val _transition = MutableSharedFlow<Transition<State, Event>>()
    private val _event = MutableSharedFlow<Event>()

    val state : StateFlow<State> = _state
    val transition  : SharedFlow<Transition<State,Event>>  = _transition
    val event : SharedFlow<Event> = _event

    val change = _transition.filter { it.previousState != it.newState }

    val eventSubscription =
        _event
            .onEach { event ->
                suspend fun onEmitState(newState: State) {
                    _state.emit(newState)
                    _transition.emit(Transition(event, state.value, newState))
                }
                mapEventToState(
                    event, state.value, ::onEmitState
                )
            }.launchIn(scope)

    fun add(event: Event) {
        scope.launch {
            _event.emit(event)
        }
    }

    abstract suspend fun mapEventToState(
        event: Event,
        state: State,
        emit: suspend (state: State) -> Unit
    )
}
