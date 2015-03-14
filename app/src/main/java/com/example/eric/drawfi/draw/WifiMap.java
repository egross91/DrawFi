package com.example.eric.drawfi.draw;

import android.graphics.Bitmap;
import android.graphics.Color;

public class WifiMap {
    private int[][] visited;
    private int center;
    private Bitmap map;



    public static final int DEFAULT_HEIGHT = 500;
    public static final int DEFAULT_WIDTH = 500;

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
            for(int j = startY ; j < endY; j++){
                if(getDistance(x , y , i , j) <= radius){
                    float[] newColor = getNewColor(color , map.getPixel(i, j) , visited[i][j]);
                    map.setPixel(i , j , Color.HSVToColor(1, newColor));
                    //draw shit
                }

            }
        }
    }

    public float[] getNewColor( int NextColor , int CurrentColor , int numTimes){

        float [] hsvNext = new float[3];
        Color.colorToHSV(NextColor , hsvNext);
        float [] hsvCurrent = new float[3];
        Color.colorToHSV( CurrentColor , hsvCurrent);

        return getNewHSV(numTimes, hsvCurrent , hsvNext);
    }

    public float[] getNewHSV(int numTimes , float[] hsvCurrent , float[] hsvNext){
        float [] newHSV =new float[3];
        for(int index=0;index<3;index++) {
            newHSV[index] = (hsvCurrent[index] * numTimes + hsvNext[index]) / (numTimes + 1);
        }
        return newHSV;
    }

    public double getDistance(int x1 , int y1 , int x2 , int y2 ){
        return Math.sqrt( Math.pow(x1 - x2 , 2) + Math.pow(y1 - y2 , 2));
    }
}
