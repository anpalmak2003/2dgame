package ru.dpankratov.projects.rpg.mydraw;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class Sprite {
    private Bitmap ship;
    private int frameWidth;
    private int frameHeight;

    private float x;
    private float y;

    private float velocityX;
    private float velocityY;

    private int padding;

    public Sprite(float x, float y, float velocityX, float velocityY,  Rect initialFrame, Bitmap ship){

        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.ship = ship;

        this.ship = ship;

        this.frameWidth = initialFrame.width();
        this.frameHeight = initialFrame.height();
        this.padding = 20;
    }
    public void setX(float x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }
    public double getX() {
        return x;
    }
    public void setY(float y) {
        this.y = y;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public double getVx() {
        return velocityX;
    }

    public void setVx(float velocityX) {
        this.velocityX = velocityX;
    }

    public double getVy() {
        return velocityY;
    }

    public void setVy(float velocityY) {
        this.velocityY = velocityY;
    }






    public void update (int ms) {

       /* timeForCurrentFrame += ms;

        if (timeForCurrentFrame >= frameTime) {
            currentFrame = (currentFrame + 1) % frames.size();
            timeForCurrentFrame = timeForCurrentFrame - frameTime;
        }*/

        x = (float) (x + velocityX * ms/1000.0);
        y = (float) (y + velocityY * ms/1000.0);
    }
    public void draw (Canvas canvas) {
        Paint p = new Paint();
        //Rect destination = new Rect((int)x, (int)y, (int)(x + frameWidth), (int)(y + frameHeight));
        canvas.drawBitmap(ship, x, y, p);
    }
    public Rect getBoundingBoxRect () {
        return new Rect((int)x+padding, (int)y+padding, (int)(x + frameWidth - 2 *padding),
                (int)(y + frameHeight - 2* padding));
    }
    public boolean intersect (Sprite s) {
        return getBoundingBoxRect().intersect(s.getBoundingBoxRect());
    }
}
