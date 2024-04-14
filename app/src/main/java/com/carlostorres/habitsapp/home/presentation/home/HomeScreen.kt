package com.carlostorres.habitsapp.home.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carlostorres.habitsapp.R
import com.carlostorres.habitsapp.home.presentation.home.components.HomeDateSelector
import com.carlostorres.habitsapp.home.presentation.home.components.HomeHabit
import com.carlostorres.habitsapp.home.presentation.home.components.HomeQuote
import java.time.ZonedDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel : HomeViewModel = hiltViewModel()
){

    val state = viewModel.state

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {

                CenterAlignedTopAppBar(
                    title = {

                        Text("Home")

                    },
                    navigationIcon = {

                        IconButton(
                            onClick = {}
                        ){

                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings"
                            )

                        }

                    }
                )
        }
    ){ paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(start = 20.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(bottom = 20.dp)
        ) {

            item {
                HomeQuote(
                    quote = "WE FIRST MAKE OUR HABITS AND THEN OUR HABITS MAKE US.",
                    author = "Anonymus",
                    imageId = R.drawable.onboarding1,
                    modifier = Modifier.padding(bottom = 13.dp, end = 20.dp)
                )
            }

            item {
                Row (
                    modifier = Modifier.fillMaxWidth().padding(bottom = 13.dp, end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Text(
                        text = "Habits". uppercase(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    HomeDateSelector(
                        selectedDate = state.selectedDate,
                        mainDate = state.currentDate,
                        onDateClick = {
                            viewModel.onEvent(HomeEvent.ChangeDate(it))
                        }
                    )

                }
            }

            items(state.habits){ habit ->
                HomeHabit(
                    habit = habit,
                    selectedDate = state.selectedDate.toLocalDate(),
                    onCheckedChange = {
                        viewModel.onEvent(HomeEvent.CompleteHabits(habit))
                    },
                    onHabitClick = {}
                )
            }

        }


    }

}