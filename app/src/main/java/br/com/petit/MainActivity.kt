package br.com.petit

import App
import androidx.compose.runtime.Composable
import br.com.petit.navigator.NavigationActivity
import br.com.petit.provider.BlocProvider
import br.com.petit.repository.StaticPetRepository
import dagger.hilt.android.AndroidEntryPoint
import tech.tiagoloureiro.navigator.NavigatorBloc
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : NavigationActivity() {

    override val content: @Composable (navigator: NavigatorBloc) -> Unit = {
            navigator ->

            App(navigator)
    }
}
