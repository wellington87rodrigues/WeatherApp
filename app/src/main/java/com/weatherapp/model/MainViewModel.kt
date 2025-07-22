package com.weatherapp.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.weatherapp.WeatherApp
import com.weatherapp.api.WeatherService
import com.weatherapp.api.toForecast
import com.weatherapp.api.toWeather
import com.weatherapp.db.fb.FBCity
import com.weatherapp.db.fb.FBDatabase
import com.weatherapp.db.fb.FBUser
import com.weatherapp.db.fb.toFBCity
import com.weatherapp.ui.nav.Route

class MainViewModel(private val db: FBDatabase,
    private val service : WeatherService) :
    ViewModel(), FBDatabase.Listener{
        private var _page = mutableStateOf<Route>(Route.Home)
        var page: Route
        get() = _page.value
        set(tmp) { _page.value = tmp }


        private val _cities = mutableStateMapOf<String, City>()
        val cities : List<City>
            get() = _cities.values.toList()

        private val _user = mutableStateOf<User?>(null)
        val user : User?
            get() = _user.value

        private var _city = mutableStateOf<City?>(null)
        var city: City?
            get() = _city.value
            set(tmp) { _city.value = tmp?.copy() }

        init {
            db.setListener(this)
        }

        fun remove(city: City) {
            db.remove(city.toFBCity())
        }

        fun add(name: String) {
            service.getLocation(name) { lat, lng ->
                if (lat != null && lng != null) {
                    db.add(City(name=name, location=LatLng(lat, lng)).toFBCity())
                }
            }
        }
        fun add(location: LatLng) {
            service.getName(location.latitude, location.longitude) { name ->
                if (name != null) {
                    db.add(City(name = name, location = location).toFBCity())
                }
            }
        }

        override fun onUserLoaded(user: FBUser) {
            _user.value = user.toUser()
        }
        override fun onUserSignOut() {
            TODO("Not yet implemented")
        }
        override fun onCityAdded(city: FBCity) {
            _cities[city.name!!] = city.toCity()
        }

        override fun onCityUpdate(city: FBCity) {
            _cities.remove(city.name)
            _cities[city.name!!] = city.toCity()
            if (_city.value?.name == city.name) { _city.value = city.toCity() }
        }

        override fun onCityRemoved(city: FBCity) {
            _cities.remove(city.name)
        }

        fun loadWeather(name: String) {
            service.getWeather(name) { apiWeather ->
                val newCity = _cities[name]!!.copy( weather = apiWeather?.toWeather() )
                _cities.remove(name)
                _cities[name] = newCity
                if (_city.value?.name == city?.name) { _city.value = null }
            }
        }

        fun loadForecast(name: String) {
            service.getForecast(name) { apiForecast ->
                val newCity = _cities[name]!!.copy( forecast = apiForecast?.toForecast() )
                _cities.remove(name)
                _cities[name] = newCity
                city = if (city?.name == name) newCity else city
            }
        }
    }