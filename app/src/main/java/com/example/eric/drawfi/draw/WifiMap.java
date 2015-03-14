package com.example.eric.drawfi.draw;

import android.graphics.Bitmap;

public class WifiMap {
    private int[][] visited;
    private int center;
    private Bitmap map;


    public static final int DEFAULT_HEIGHT = 10000000;
    public static final int DEFAULT_WIDTH = 10000000;

    public WifiMap(int rows, int cols) {
        this.visited = new int[rows][cols];
        this.center = (rows < cols) ? cols >> 1 : rows >> 1;
        this.map = Bitmap.createBitmap(cols, rows, Bitmap.Config.RGB_565);
    }

    public void drawCircle(int x , int y , int color , int radius){



        int startX = (x- radius)<0?0:x- radius;
        int startY = (y-radius)<0?0:y-radius;

        int endX = (x+radius)>DEFAULT_WIDTH?DEFAULT_WIDTH:x+radius;
        int endY = (y+radius)>DEFAULT_HEIGHT?DEFAULT_HEIGHT:y+radius;
        endX = endX < 0 ? 0 : endX;
        endY = endY < 0 ? 0 : endY;

        for(int i = startX ; i < endX; i++){
            for(int j = 0 ; j < endY; j++){
                if(getDistance(x , y , i , j)<=radius){
                    
                    //draw shit
                }

            }
        }



    }

    public double getDistance(int x1 , int y1 , int x2 , int y2 ){
        return Math.sqrt( Math.pow(x1 - x2 , 2) + Math.pow(y1 - y2 , 2));
    }
}
