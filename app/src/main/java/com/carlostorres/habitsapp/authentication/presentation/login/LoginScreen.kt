package com.carlostorres.habitsapp.authentication.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlostorres.habitsapp.R
import com.carlostorres.habitsapp.authentication.presentation.login.components.LoginForm
import com.carlostorres.habitsapp.core.presentation.HabitTitle

@Composable
fun LoginScreen(
    viewModel : LoginViewModel = hiltViewModel()
){

    val state = viewModel.state

    Box(modifier = Modifier.fillMaxSize()){

        Image(
            painter = painterResource(id = R.drawable.loginbackground),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f)
                .graphicsLayer(scaleX = 1.25f, scaleY = 1.25f)
        )

        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.background,
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
        )

        Column (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(Modifier)

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                HabitTitle("Welcome to")
                HabitTitle("monumental habits")
            }

            LoginForm(
                state,
                viewModel::onEvent
            )

        }

    }

}