package com.carlostorres.habitsapp.navigation

sealed class NavigationRoutes(val route : String) {

    object Onboarding : NavigationRoutes("Onboarding")

}