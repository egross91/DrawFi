package com.pathrecorder;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

public class WifiMap {
    private static int[][] visited;
    private static Bitmap map;

    public static final int DEFAULT_HEIGHT = 1701;
    public static final int DEFAULT_WIDTH = 1080;

    static {
        visited = new int[DEFAULT_WIDTH][DEFAULT_HEIGHT];
        map = Bitmap.createBitmap(DEFAULT_WIDTH, DEFAULT_HEIGHT, Bitmap.Config.RGB_565);
    }

    public static void clear() {

    }

    public static void drawCircle(int x , int y , int color , int radius , Canvas canvas){
        int startX = (x-radius)<0?0:x- radius;
        int startY = (y-radius)<0?0:y-radius;

        int endX = (x+radius)>=DEFAULT_WIDTH?DEFAULT_WIDTH:x+radius;
        int endY = (y+radius)>=DEFAULT_HEIGHT?DEFAULT_HEIGHT:y+radius;
        endX = endX < 0 ? 0 : endX;
        endY = endY < 0 ? 0 : endY;

        for(int i = startX ; i < endX; i++){
            for(int j = startY ; j < endY; j++){
                if(getDistance(x , y , i , j) <= radius){
                    float[] newColor = getNewColor(color , map.getPixel(i, j) , visited[i][j]);
                    map.setPixel(i , j , Color.HSVToColor(1, newColor));
                    visited[i][j]++;
                    canvas.drawBitmap(map , 0 , 0 , null);
                    //draw shit
                }

            }
        }
    }

    public static float[] getNewColor( int NextColor , int CurrentColor , int numTimes){
        float [] hsvNext = new float[3];
        Color.colorToHSV(NextColor , hsvNext);
        float [] hsvCurrent = new float[3];
        Color.colorToHSV( CurrentColor , hsvCurrent);

        return getNewHSV(numTimes, hsvCurrent , hsvNext);
    }

    public static float[] getNewHSV(int numTimes , float[] hsvCurrent , float[] hsvNext){
        float [] newHSV =new float[3];
        for(int index=0;index<3;index++) {
            newHSV[index] = (hsvCurrent[index] * numTimes + hsvNext[index]) / (numTimes + 1);
        }
        return newHSV;
    }

    public static double getDistance(int x1 , int y1 , int x2 , int y2 ){
        return Math.sqrt( Math.pow(x1 - x2 , 2) + Math.pow(y1 - y2 , 2));
    }


}