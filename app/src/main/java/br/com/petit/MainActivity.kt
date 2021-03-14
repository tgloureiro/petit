package br.com.petit

import App
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import br.com.petit.ui.theme.PetitTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import tech.tiagoloureiro.navigator.NavigatorBloc

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetitTheme {
                App()
            }
        }
    }
}
