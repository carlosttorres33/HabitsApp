package com.carlostorres.habitsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.carlostorres.habitsapp.authentication.presentation.login.LoginScreen
import com.carlostorres.habitsapp.authentication.presentation.signup.SignupScreen
import com.carlostorres.habitsapp.home.presentation.detail.DetailScreen
import com.carlostorres.habitsapp.home.presentation.home.HomeScreen
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

        composable(NavigationRoutes.Signup.route) {
            SignupScreen(
                onSignIn = {
                    navController.navigate(NavigationRoutes.Home.route, builder = {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    })
                },
                onLogin = {
                    navController.popBackStack()
                })
        }

        composable(NavigationRoutes.Home.route) {
            HomeScreen(
                onNewHabit = {
                    navController.navigate(NavigationRoutes.Detail.route)
                },
                onSettings = {
                    navController.navigate(NavigationRoutes.Settings.route)
                },
                onEditHabit = {
                    navController.navigate(NavigationRoutes.Detail.route + "?habitId=$it")
                }
            )
        }

        composable(
            route = NavigationRoutes.Detail.route + "?habitId={habitId}",
            arguments = listOf(
                navArgument("habitId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            DetailScreen(
                onBack = {
                    navController.popBackStack()
                },
                onSave = {
                    navController.popBackStack()
                }
            )
        }

    }

}