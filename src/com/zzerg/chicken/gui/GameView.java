package com.zzerg.chicken.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// import towe.def.GameView.GameThread;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.zzerg.chicken.life.Ball;
import com.zzerg.chicken.life.Player;

/**
 * User: zzerg
 * Date: 19.7.13
 * Time: 23.54
 */
public class GameView extends SurfaceView {
    private GameThread mThread;

    public int shotX;
    public int shotY;

    private boolean running = false;

    /* Entities */
    private List<Ball> balls = new ArrayList<Ball>();
    private Player player;

    Bitmap playerBitmaps;

//-------------Start of GameThread--------------------------------------------------\\

    public class GameThread extends Thread {
        private GameView view;

        public GameThread(GameView view) {
            this.view = view;
        }

        public void setRunning(boolean run) {
            running = run;
        }

        public void run() {
            while (running) {
                Canvas canvas = null;
                try {
                    canvas = view.getHolder().lockCanvas();
                    synchronized (view.getHolder()) {
                        onDraw(canvas);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    if (canvas != null) {
                        view.getHolder().unlockCanvasAndPost(canvas);
                    }
                }
            }
        } // run
    } // GameThread

    /*-------------End of GameThread--------------------------------------------------*/

    public GameView(Context context) {
        super(context);
        mThread = new GameThread(this);

        getHolder().addCallback(new SurfaceHolder.Callback() {
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                mThread.setRunning(false);
                while (retry) {
                    try {
                        mThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

            public void surfaceCreated(SurfaceHolder holder) {
                mThread.setRunning(true);
                mThread.start();
            }

            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
        });

        playerBitmaps = BitmapFactory.decodeResource(getResources(), R.drawable.player);
        player = new Player(this, guns);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        Iterator<Ball> j = balls.iterator();
        while (j.hasNext()) {
            Ball b = j.next();
            if (b.x >= 1000 || b.x <= 1000) {
                b.onDraw(canvas);
            } else {
                j.remove();
            }
        }
        canvas.drawBitmap(guns, 5, 120, null);
    }

    public Ball createSprite(int resouce) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
        return new Ball(this, bmp);
    }
}
