package com.example.eric.drawfi.adr.listeners;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class GpsListener extends Listener implements LocationListener {
    public GpsListener() {
    }

    public boolean register(LocationManager locationManager) {
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void onLocationChanged(Location location) {
        this.data.addLocation(location);
    }

    public void onProviderDisabled(String provider) {
        this.data.gpsDisabled();
    }

    public void onProviderEnabled(String provider) {
        this.data.gpsEnabled();
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        this.data.gpsStatusChanged(status);
    }

    public void onStop() {
    }
}