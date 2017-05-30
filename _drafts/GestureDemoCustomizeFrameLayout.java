

import android.content.Context;
import android.content.Intent;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.FrameLayout;


/**
 * Created by Xiao Chao on 5/24/2017.
 */

public class CustomizeFrameLayout extends FrameLayout {
    private static final String TAG = "MyFrameLayout";
    private VelocityTracker velocityTracker;
    private int slideCount = 0;
    private int averVelocityX = 0;
    private int totalVelocityX = 0;
    private int directionAverVelocityX = 0;
    private int directionTotalVelocityX = 0;
    private int directionCounter = 0;
    private final float threshold = 10;
    private final float threshold_velocity = 100;
    private int direction = 0; //0: standstill 1:up 2:left 3:down 4:right
    private int oldDirection = 0; //0: standstill 1:up 2:left 3:down 4:right
    private float oldX = 0;
    private float oldY = 0;
    private Context mContext = null;

    private FragmentTabHost mTabHost;

    public CustomizeFrameLayout(@NonNull Context context) {
        super(context);
    }

    public CustomizeFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

    }

    public CustomizeFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        velocityTracker = VelocityTracker.obtain();
        velocityTracker.addMovement(ev);
        velocityTracker.computeCurrentVelocity(1000);//计算速度
        float xVelocity = Math.abs(velocityTracker.getXVelocity());
        float yVelocity = Math.abs(velocityTracker.getYVelocity());

        if (ev.getAction() == ev.ACTION_DOWN) {
            statisticDataReset();
        } else if ( ev.getAction() == ev.ACTION_UP) {
            if (slideCount > 0) {
                averVelocityX = (int)totalVelocityX / slideCount;
//                Log.d(TAG, "dispatchTouchEvent: averVelocityX:" + averVelocityX + "slideCount:" + slideCount);
                if ( (averVelocityX > 5000) && (slideCount > 6) ) {
                    Intent intentCleaning = new Intent(mContext, CleaningActivity.class);
                    mContext.startActivity(intentCleaning);
                }
            }
        }
//        Log.e(TAG, "dispatchTouchEvent: xVelocity: " + xVelocity + " getX:" + ev.getX() + " yVelocity:  " + yVelocity + " getY:" + ev.getY());
        actionDetector(ev.getX(), ev.getY(), xVelocity, yVelocity);

        return super.dispatchTouchEvent(ev);
    }

    private void actionDetector(float currentX, float currentY, float xVelocity, float yVelocity) {
        //velocity
        if (currentX - oldX > threshold) {
            direction = 4;
            if (xVelocity > threshold_velocity) {
                directionTotalVelocityX += xVelocity;
                directionCounter++;
            }
        } else if (oldX - currentX > threshold) {
            direction = 3;
            if (xVelocity > threshold_velocity) {
                directionTotalVelocityX += xVelocity;
                directionCounter++;
            }
        }

        if (oldDirection != direction) {
            Log.d(TAG, "dispatchTouchEvent: directionCounter:" + directionCounter);
            Log.d(TAG, "dispatchTouchEvent: directionAverVelocityX:" + directionAverVelocityX);
            if (directionCounter != 0) {
                directionAverVelocityX = (int)directionTotalVelocityX / directionCounter;
                Log.d(TAG, "dispatchTouchEvent: directionAverVelocityX:" + directionAverVelocityX);
                totalVelocityX += directionAverVelocityX;
                slideCount++;
            }
            if (directionAverVelocityX == 0) {

            }
            directionTotalVelocityX = 0;
            directionCounter = 0;
        }
        oldX = currentX;
        oldY = currentY;
        oldDirection = direction;
    }

    public void statisticDataReset() {
        oldX = 0;
        oldY = 0;
        slideCount = 0;
        oldDirection = 0;
        averVelocityX = 0;
        totalVelocityX = 0;
        directionCounter = 0;
        directionAverVelocityX = 0;
        directionTotalVelocityX = 0;
    }

//    /**
//     * Implement this method to handle touch screen motion events.
//     * <p>
//     * If this method is used to detect click actions, it is recommended that
//     * the actions be performed by implementing and calling
//     * {@link #performClick()}. This will ensure consistent system behavior,
//     * including:
//     * <ul>
//     * <li>obeying click sound preferences
//     * <li>dispatching OnClickListener calls
//     * <li>handling {@link AccessibilityNodeInfo#ACTION_CLICK ACTION_CLICK} when
//     * accessibility features are enabled
//     * </ul>
//     *
//     * @param event The motion event.
//     * @return True if the event was handled, false otherwise.
//     */
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.d(TAG, "onTouchEvent: ffffffffffff");
//        return super.onTouchEvent(event);
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return true;
//    }
}
