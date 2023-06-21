package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.screens.WeatherItem
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.utils.Utils
import com.example.myapplication.viewmodels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val weatherList by viewModel.weatherList.observeAsState(initial = emptyList())

                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Row(
                                        modifier = Modifier.fillMaxHeight(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(text = "Weather App")
                                    }
                                },
                                modifier = Modifier.height(80.dp)
                            )
                        },
                        content = { innerPadding ->
                            LazyColumn(modifier = Modifier.padding(top = innerPadding.calculateTopPadding())) {
                                items(weatherList) { city ->
                                    WeatherItem(city)
                                }
                            }
                        }
                    )

                    LaunchedEffect(Unit) {
                        viewModel.fetchWeatherDataAsyncThenSync(
                            Utils.list1,
                            Utils.list2
                        )
                    }
                }
            }
        }
    }
}



