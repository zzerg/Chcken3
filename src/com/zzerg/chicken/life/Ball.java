package com.zzerg.chicken.life;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.zzerg.chicken.gui.GameView;

/**
 * User: zzerg
 * Date: 20.7.13
 * Time: 0.04
 */
public class Ball {
    public GameView gameView;
    private Bitmap bmp;

    public int x;
    public int y;

    private int mSpeed = 25;
    public double angle;

    public int width;
    public int height;


    public Ball(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;

        this.x = 0;            //позиция по Х
        this.y = 120;          //позиция по У
        this.width = 27;       //ширина снаряда
        this.height = 40;      //высота снаряда

        //угол полета пули в зависипости от координаты косания к экрану
        angle = Math.atan((double) (y - gameView.shotY) / (x - gameView.shotX));
    }

    /**
     * Перемещение объекта, его направление
     */
    private void update() {
        x += mSpeed * Math.cos(angle);         //движение по Х со скоростью mSpeed и углу заданном координатой angle
        y += mSpeed * Math.sin(angle);         // движение по У -//-
    }

    /**
     * Рисуем наши спрайты
     */
    public void onDraw(Canvas canvas) {
        update();                              //говорим что эту функцию нам нужно вызывать для работы класса
        canvas.drawBitmap(bmp, x, y, null);
    }
}
