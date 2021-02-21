package ru.dpankratov.projects.rpg.mydraw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;

public class MyGame extends View implements View.OnTouchListener {

    private Paint paint = new Paint(); //Кисть
    private int viewWidth;
    private int viewHeight;
    private Sprite enemy;
    private Bitmap pic;
    float touchX, touchY;
    Paint line;
private int points = 0;
    float myShipX, myShipY;
    private final int timerInterval = 30;
    String myAction = "None";
Bitmap pic2;
    boolean fire = false;
Rect firstFrame;
private float lineX;
    public MyGame(Context context) {

        super(context);
        setOnTouchListener(this);
        pic = BitmapFactory.decodeResource(getResources(), R.mipmap.ship1);
        myShipX = 0;





        pic2 = BitmapFactory.decodeResource(getResources(), R.mipmap.pic1);
      int  w = pic2.getWidth()/5;
       int h = pic2.getHeight()/3;

      firstFrame = new Rect(0, 0, w, h);

        enemy = new Sprite((float) (viewWidth + Math.random() * 500), 0, 0, 800, firstFrame, pic2);


       Timer t = new Timer();
        t.start();
    }
    protected void update () {
        enemy.update(timerInterval);




        if (enemy.getY() > viewHeight) {
          teleportEnemy();
        points=points-10;
        }


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w,h,oldw,oldh);
        viewHeight = h;
        viewWidth = w;
        myShipY = viewHeight-pic.getHeight();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawARGB(250, 127, 199, 255); // цвет фона
        enemy.draw(canvas);
        enemy.update(10);
        canvas.drawBitmap(pic, myShipX, myShipY, paint);
        paint.setTextSize(40);
        String a = String.valueOf(points);
        canvas.drawText(a ,100 ,100, paint );
        if (fire){
            fire = false;
            Paint line = new Paint();
            line.setStrokeWidth(23);
            line.setColor(Color.RED);
            lineX = myShipX+pic.getWidth()/2;
            canvas.drawLine(lineX,
                    myShipY,
                    lineX,
                    0, line);
            if(enemy.getX()-lineX<0&&enemy.getX()-lineX>-378)
            {
                teleportEnemy();
                points=points+10;
            }

        }
        invalidate();


    }


    private void teleportEnemy () {
        enemy.setX((float) (Math.random()*600));
        enemy.setY((float) (0));
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        touchX = event.getX();
        touchY = event.getY();

        if (touchX>viewWidth-pic.getWidth()) {
            myShipX = viewWidth-pic.getWidth()-pic.getWidth()/2;
        } else {
            myShipX = touchX-pic.getWidth()/2;
        }
        //myShipY = touchY;
        fire = true;

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                myAction = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_UP:
                myAction = "ACTION_UP";
                break;
            case MotionEvent.ACTION_MOVE:
                myAction = "ACTION_MOVE";
                break;
        }
        return false;
    }

    class adn extends CountDownTimer {

        public adn() {
            super(Integer.MAX_VALUE, 400);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            enemy = new Sprite((float) (viewWidth + Math.random() * 500), 0, 0, 500, firstFrame, pic2);
        }

        @Override
        public void onFinish() {

        }
    }

    class Timer extends CountDownTimer {

        public Timer() {
            super(Integer.MAX_VALUE, timerInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            update ();
        }

        @Override
        public void onFinish() {

        }
    }

}
