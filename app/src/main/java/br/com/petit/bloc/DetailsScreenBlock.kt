package br.com.petit.bloc
/*

import br.com.petit.model.Adoption
import br.com.petit.model.Pet
import br.com.petit.navigator.*
import br.com.petit.repository.PetRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import tech.tiagoloureiro.bloc.Bloc
import tech.tiagoloureiro.navigator.NavigatorBloc
import tech.tiagoloureiro.navigator.Route
import java.util.logging.Level
import java.util.logging.Logger


class DetailsScreenBloc @AssistedInject constructor(
    @Assisted
    private val route: Route,
    @Assisted
    val scope: CoroutineScope = CoroutineScope(Dispatchers.Main),
    private val petListBloc: PetListBloc,
    private val navigator: NavigatorBloc
) : Bloc<DetailsScreenState, DetailsScreenEvent>() {

    override val initialState: DetailsScreenState
        get() = Loading

    private val job = PetListBloc.fetchPet((route as DetailsRoute).petId).onEach {
        delay(3000)
        setState(Loaded(it))
    }.launchIn(scope)


    override fun mapEventToState(event: DetailsScreenEvent) {
        when (event) {
            is DetailsScreenAdoptEvent -> {
                val value = state.value
                if (value is Loaded) {
                    scope.launch {
                        petRepository.adopt(Adoption(0, value.pet))
                        navigator.emitEvent(Navigate(SuccessfulAdoptionRoute((route as DetailsRoute).petId)))
                    }
                }
            }
            is DetailsScreenPopEvent -> {
                navigator.emitEvent(PopRoute)
            }
        }
    }


    override fun onShow() {
        Logger.getLogger("DetailsScreenBloc").log(
            Level.WARNING, "DetailsScreenBloc screen shown"
        )
    }

    override fun onHide() {
        Logger.getLogger("DetailsScreenBloc").log(
            Level.WARNING, "DetailsScreenBloc screen hidden"
        )
    }

    override fun onDestroy() {
        Logger.getLogger("DetailsScreenBloc").log(
            Level.WARNING, "DetailsScreenBloc removed from backstack"
        )
        job.cancel()
    }

}


sealed class DetailsScreenState
object Loading : DetailsScreenState()
data class Loaded(val pet: Pet) : DetailsScreenState()

sealed class DetailsScreenEvent
object DetailsScreenPopEvent : DetailsScreenEvent()
object DetailsScreenAdoptEvent : DetailsScreenEvent()


@AssistedFactory
interface MyDetailScreenBlocFactory : LiveBlocFactory {
    override fun create(route: Route): DetailsScreenBloc
}



*/
