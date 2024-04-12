package com.carlostorres.habitsapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.carlostorres.habitsapp.navigation.NavigationHost
import com.carlostorres.habitsapp.navigation.NavigationRoutes
import com.carlostorres.habitsapp.ui.theme.HabitsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavigationHost(
                        navController = navController,
                        startDestination = getStartDestination()
                    )

                }
            }
        }
    }

    private fun getStartDestination() : NavigationRoutes{
        if (viewModel.isLoggedIn){
            return NavigationRoutes.Home
        }
        return if (viewModel.hasSeenOnboarding){
            NavigationRoutes.Login
        }else{
            NavigationRoutes.Onboarding
        }
    }

}