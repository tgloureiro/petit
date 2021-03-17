package br.com.petit

import App
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import br.com.petit.core.ui.UIRoute
import br.com.petit.core.ui.theme.PetitTheme
import br.com.petit.feature.petAdoption.route.PetAdoptionRoute
import br.com.petit.feature.petDetails.ui.route.PetDetailsRoute
import br.com.petit.feature.petList.ui.route.PetListRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            val mainRoute = PetListRoute(navController)
            val adoptionRoute = PetAdoptionRoute(navController)

            val routes: List<UIRoute> =
                listOf(
                    mainRoute,
                    PetDetailsRoute(navController),
                    adoptionRoute,
                )

            PetitTheme { App(navController, mainRoute.route, routes) }
        }
    }
}
