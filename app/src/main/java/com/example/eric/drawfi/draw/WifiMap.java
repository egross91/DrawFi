package com.example.eric.drawfi.draw;

import android.graphics.Bitmap;

public class WifiMap {
    private int[][] visited;
    private int center;
    private Bitmap map;

    public WifiMap(int rows, int cols) {
        this.visited = new int[rows][cols];
        this.center = (rows < cols) ? cols >> 1 : rows >> 1;
        this.map = Bitmap.createBitmap(cols, rows, Bitmap.Config.RGB_565);
    }
}
