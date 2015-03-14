package com.example.eric.drawfi.draw;

public class DrawThread extends Thread {
    private WifiMap wifiMap;

    public DrawThread() {
        this.wifiMap = new WifiMap(WifiMap.DEFAULT_HEIGHT, WifiMap.DEFAULT_WIDTH);
    }

    public DrawThread(int n) {
        this.wifiMap = new WifiMap(n, n);
    }

    public DrawThread(int height, int width) {
        this.wifiMap = new WifiMap(height, width);
    }

    @Override
    public void run() {

    }
}
