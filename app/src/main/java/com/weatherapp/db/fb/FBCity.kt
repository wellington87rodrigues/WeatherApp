package com.weatherapp.db.fb

import com.google.android.gms.maps.model.LatLng
import com.weatherapp.model.City


class FBCity {
    var name : String? = null
    var lat : Double? = null
    var lng : Double? = null

    fun toCity(): City {
        val latLng = if(lat!=null&&lng!=null) LatLng(lat!!, lng!!) else null
        return  City(name!!, weather = null, location = latLng)
    }
}

fun City.toFBCity() : FBCity {
    val fbCity = FBCity()

    fbCity.name = this.name
    fbCity.lat = this.location?.latitude ?: 0.0
    fbCity.lng = this.location?.longitude ?: 0.0

    return fbCity
}