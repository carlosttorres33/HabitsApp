package com.carlostorres.habitsapp.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: NavigationRoutes
){

    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ){

        composable(NavigationRoutes.Onboarding.route){
            Text("Onboarding")
        }

    }

}