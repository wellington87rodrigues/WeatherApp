package com.weatherapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun MapPage(){
    Column(
        modifier = Modifier.Companion.fillMaxSize()
            .background(Color.Companion.Magenta)
            .wrapContentSize(Alignment.Companion.Center)
    ) {
        Text(
            text = "Favoritas",
            fontWeight = FontWeight.Companion.Bold,
            color = Color.Companion.White,
            modifier = Modifier.Companion.align(Alignment.Companion.CenterHorizontally),
            textAlign = TextAlign.Companion.Center,
            fontSize = 20.sp
        )
    }
}