package com.example.eric.drawfi.adr;

import android.location.Location;

import com.example.eric.drawfi.adr.resources.Data;

/**
 * This class is an interface to retrieve the information in the Adr
 * Service.
 */
public interface AdrInterface {
    public Location getLocation();

    public long getStepCount();

    public float getStrideLength();

    public Data getData();
}