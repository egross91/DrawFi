package com.example.eric.drawfi.adr.listeners;


import android.hardware.SensorManager;
import android.location.LocationManager;

import com.example.eric.drawfi.adr.resources.Data;

public abstract class Listener {
    protected Data data = null;

    public boolean register(SensorManager sensorManager) {
        return true;
    }

    public boolean register(LocationManager locationManager) {
        return true;
    }

    public void unregister(SensorManager sensorManager) {
    }

    public void unregister(LocationManager locationManager) {
    }

    abstract public void onStop();

    public void setData(Data data) {
        this.data = data;
    }
}