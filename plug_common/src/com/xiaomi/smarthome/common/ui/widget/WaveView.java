
package com.xiaomi.smarthome.common.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class WaveView extends View {

    int mWidth = 0;
    int mHeight = 0;

    // 是否正在运行。stop后会有一个延时动画，将现在的框跑完。
    boolean mRunning = false;
    // 是否停止，停止后会一一小段 running，此时不会产生新的circle。
    boolean mStop = true;

    int START_R = 0;
    int END_R = 0;
    int COLOR = 0;
    long CIRCLE_INTERVAL = 300;
    long SPEAD_TIME = 1 * 800;
    long REFRESH_TIME = 16;

    long startTime = 0;
    long stopTime = 0;
    long curTime = 0;
    Paint mPaint = new Paint();

    static final int MSG_START = 1;
    static final int MSG_REFRESH = 2;
    static final int MSG_STOP = 3;
    static final int MSG_REAL_STOP = 4;

    Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case MSG_START:
                    mHandler.sendEmptyMessage(MSG_REFRESH);
                    break;
                case MSG_REFRESH:
                    invalidate();
                    mHandler.sendEmptyMessageDelayed(MSG_REFRESH, REFRESH_TIME);
                    break;
                case MSG_STOP:
                    mStop = true;
                    stopTime = System.currentTimeMillis();
                    long pastTime = stopTime - startTime;
                    mHandler.sendEmptyMessageDelayed(MSG_REAL_STOP, SPEAD_TIME - pastTime % CIRCLE_INTERVAL);
                    invalidate();
                    break;
                case MSG_REAL_STOP:
                    mRunning = false;
                    mHandler.removeMessages(MSG_REFRESH);
                    invalidate();
                    break;
            }
        }
    };

    public WaveView(Context context) {
        super(context);

        init();
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    void init() {
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!mRunning) {
            return;
        }

        curTime = System.currentTimeMillis();
        long pastTime = curTime - startTime;
        int generatedCircle;
        if (!mStop) {
            generatedCircle = (int) Math.floor(pastTime / (double) CIRCLE_INTERVAL) + 1;
        } else {
            generatedCircle = (int) Math.floor((stopTime - startTime) / (double) CIRCLE_INTERVAL) + 1;
        }

        double spreadSpeed = (END_R - START_R) / (double) SPEAD_TIME;

        for (int i = generatedCircle; i > 0; i--) {

            int move = (int) ((pastTime - CIRCLE_INTERVAL * (i - 1)) * spreadSpeed);
            if (move >= (END_R - START_R)) {
                break;
            }
            float percent = move / (float) (END_R - START_R);
            // int color = ((((int) ((1 - percent) * 255)) & 0xFF) << 24) |
            // 0x00ABE3;
            // int color = ((((int) ((1 - percent) * 255)) & 0xFF) << 24) |
            // 0x9D9C9C;
            int color = ((((int) ((1 - percent) * 255)) & 0xFF) << 24) | COLOR;
            int radius = move + START_R;
            mPaint.setColor(color);
            canvas.drawCircle(mWidth / 2.0f, mHeight / 2.0f, radius, mPaint);
        }

    }

    public void start(int startR, int endR, int color) {
        start(startR, endR, color, 0);
    }

    public void start(int startR, int endR, int color, int stopDuration) {
        if (mRunning) {
            return;
        }

        START_R = startR;
        END_R = endR;
        COLOR = color;

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        startTime = System.currentTimeMillis();
        stopTime = 0;

        mRunning = true;
        mStop = false;
        mHandler.sendEmptyMessage(MSG_START);
        if (stopDuration > 0) {
            mHandler.sendEmptyMessageDelayed(MSG_STOP, stopDuration);
        }
    }

    public void stop() {
        if (!mRunning) {
            return;
        }
        mHandler.sendEmptyMessage(MSG_STOP);
    }

}
