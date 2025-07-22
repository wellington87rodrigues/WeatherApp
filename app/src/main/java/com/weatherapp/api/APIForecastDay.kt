package com.weatherapp.api

import com.weatherapp.model.Forecast

data class APIForecastDay (
    var date: String? = null,
    var day: APIWeather? = null
)
