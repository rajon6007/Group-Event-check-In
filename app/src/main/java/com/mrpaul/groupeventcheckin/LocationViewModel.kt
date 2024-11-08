package com.mrpaul.groupeventcheckin

import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task

class LocationViewModel(context: Context) : ViewModel() {
    private val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    private val _currentLocation = MutableLiveData<Location>()
    val currentLocation: LiveData<Location> get() = _currentLocation

    fun getCurrentLocation() {
        fusedLocationClient.lastLocation.addOnCompleteListener { task: Task<Location> ->
            if (task.isSuccessful && task.result != null) {
                _currentLocation.value = task.result
            } else {
                // Handle the error
            }
        }
    }
}