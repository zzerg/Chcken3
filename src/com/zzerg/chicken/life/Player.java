package com.zzerg.chicken.life;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.zzerg.chicken.gui.GameView;

/**
 * User: zzerg
 * Date: 20.7.13
 * Time: 0.02
 */
public class Player {
    private GameView gameView;
    private Bitmap bmp;

    public int x;
    public int y;

    public Player(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;
        this.x = 0;                        //отступ по х нет
        this.y = gameView.getHeight() / 2; //делаем по центру
    }

    public void onDraw(Canvas c) {
        c.drawBitmap(bmp, x, y, null);
    }
}
