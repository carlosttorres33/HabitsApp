package com.carlostorres.habitsapp.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.carlostorres.habitsapp.onboarding.presentation.OnboardingScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: NavigationRoutes
) {

    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {

        composable(NavigationRoutes.Onboarding.route) {
            OnboardingScreen(
                onFinish = {
                    navController.popBackStack()
                    navController.navigate(NavigationRoutes.Login.route)
                }
            )
        }

        composable(NavigationRoutes.Login.route) {
            Text("Pantalla Login")
        }

    }

}