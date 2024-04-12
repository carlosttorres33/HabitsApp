package com.carlostorres.habitsapp.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.carlostorres.habitsapp.authentication.presentation.login.LoginScreen
import com.carlostorres.habitsapp.authentication.presentation.signup.SignupScreen
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
            LoginScreen(
                onLogin = {
                    navController.popBackStack()
                    navController.navigate(NavigationRoutes.Home.route)
                },
                onSignUp = {
                    navController.navigate(NavigationRoutes.Signup.route)
                }
            )
        }

        composable(NavigationRoutes.Home.route) {
            Text("HOME SCREEN")
        }

        composable(NavigationRoutes.Signup.route) {
            SignupScreen(
                onSignIn = {
                    navController.navigate(NavigationRoutes.Home.route, builder = {
                        popUpTo(navController.graph.id){
                            inclusive = true
                        }
                    })
                },
                onLogin = {
                    navController.popBackStack()
                })
        }

    }

}