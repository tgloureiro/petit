package br.com.petit.navigator

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import tech.tiagoloureiro.navigator.NavigatorBloc
import tech.tiagoloureiro.navigator.PopRoute
import javax.inject.Inject


abstract class NavigationActivity : AppCompatActivity() {

    abstract val content:  @Composable (navigator: NavigatorBloc) -> Unit

    @Inject
    lateinit var navigator: NavigatorBloc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO: Check if it's following the lifecycle correctly
        lifecycleScope.launchWhenStarted {
            //TODO: look for transitions and when the state doesn't changes,
            //move to the back stack

            /*navigator.
            navigator.shouldNavigateToBack.onEach {
                moveTaskToBack(false)
            }.launchIn(this)
             */
        }

        setContent {
            content(
                navigator
            )
        }
    }

    override fun onBackPressed() {
        navigator.add(PopRoute)
    }

}

