package br.com.petit

import br.com.petit.core.ui.App
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.compose.rememberNavController
import br.com.petit.core.ui.UIRoute
import br.com.petit.core.ui.theme.PetitTheme
import br.com.petit.feature.adoption.ui.route.PetAdoptionRoute
import br.com.petit.feature.pet.ui.route.PetGridScreenRoute
import br.com.petit.feature.petDetails.ui.route.PetDetailsRoute
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            val petGridRoute = PetGridScreenRoute(navController)
            val detailsRoute = PetDetailsRoute(navController)
            val adoptionRoute = PetAdoptionRoute(navController)

            val routes: List<UIRoute> =
                listOf(
                    petGridRoute,
                    detailsRoute,
                    adoptionRoute,
                )

            PetitTheme { App(navController, petGridRoute.routeName, routes) }
        }
    }
}
