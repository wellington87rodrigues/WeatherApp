package com.weatherapp.model

import com.google.android.gms.maps.model.LatLng


data class City (
    val name : String,
    //val weather: String? = null,
    var location: LatLng? = null,
    var weather: Weather? = null
)