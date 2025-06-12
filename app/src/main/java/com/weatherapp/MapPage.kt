package com.weatherapp

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.weatherapp.ui.model.MainViewModel

@SuppressLint("ContextCastToActivity")
@Composable
fun MapPage(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
){

    val cityList = viewModel.cities

    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.Gray)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Mapa",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}