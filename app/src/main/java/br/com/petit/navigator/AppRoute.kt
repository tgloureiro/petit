package br.com.petit.navigator

import tech.tiagoloureiro.navigator.Route

sealed class AppRoute : Route
object MainRoute: AppRoute()
data class DetailsRoute(val petId: Long): AppRoute()
data class SuccessfulAdoptionRoute(val petId: Long): AppRoute()
