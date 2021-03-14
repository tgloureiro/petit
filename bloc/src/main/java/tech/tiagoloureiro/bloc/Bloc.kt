package tech.tiagoloureiro.bloc

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter

data class Transition<State, Event>(
    val event: Event,
    val previousState: State,
    val newState: State
)

abstract class Bloc<State, Event>(
    initialState: State
) {
    private val _state = MutableStateFlow(initialState)
    private val _transition = MutableSharedFlow<Transition<State,Event>>()

    val state = _state
    val transition = _transition
    val change = _transition.filter {
        it.previousState != it.newState
    }

    fun add(event: Event) {
        fun onEmitState(newState:State){
            _state.tryEmit(newState)
            _transition.tryEmit(Transition(event, state.value, newState))
        }
        mapEventToState(
            event,
            state.value,
            ::onEmitState)
    }

    abstract fun mapEventToState(event: Event, state: State, emit: (state: State) -> Unit)
}




/*
    private val _events = MutableSharedFlow<Event>()

    val eventSubscription =
    _events
        .onEach { event ->
            val stateFlow = MutableSharedFlow<State>()
            val stateSubscription = stateFlow
                    .map {
                        Transition(event, state.value, it)
                    }
                    .onEach { transition ->
                        _state.emit(transition.newState)
                        _transition.emit(transition)
                    }
                    .launchIn(scope)

            mapEventToState(
                event, state.value, { state -> scope.launch { stateFlow.emit(state) } }
            )
            stateSubscription.cancel()
        }.launchIn(scope)
*/