package uk.co.tekkies.akko.groundstation;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class JoystickView extends View {
	Paint redPaint;
	Paint bluePaint;
	Paint extentPaint;
	MotionEvent motionEvent=null;
	int lhsPointerId=-2;
	int rhsPointerId=-2;
	Point lhsDown=new Point();
	Point rhsDown=new Point();
	Point lhsNow=new Point();
	Point rhsNow=new Point();

	public JoystickView(Context context) {
		super(context);
		setUp(context);
	}

	public JoystickView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setUp(context);
	}
	
	private void setUp(Context context) {
		setupPalette();
	}

	private void setupPalette() {
		redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		redPaint.setARGB(0x80, 0xff, 0x00, 0x00);
		bluePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		bluePaint.setARGB(0x80, 0x00, 0x00, 0xff);
		extentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		extentPaint.setARGB(0x10, 0x00, 0x00, 0x00);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if(isInEditMode()) {
			canvas.drawRect(new Rect(50,50,150,150), redPaint);
		} else {
			if(lhsPointerId != -2) {
				canvas.drawCircle(lhsDown.x, lhsDown.y, getJoystickExtent(), extentPaint);
				canvas.drawCircle(lhsNow.x, lhsNow.y, 15, redPaint);
				canvas.drawLine(lhsDown.x, lhsDown.y, lhsNow.x, lhsNow.y, redPaint);
			}
			if(rhsPointerId != -2) {
				canvas.drawCircle(rhsDown.x, rhsDown.y, getJoystickExtent(), extentPaint);
				canvas.drawCircle(rhsNow.x, rhsNow.y, 15, redPaint);
				canvas.drawLine(rhsDown.x, rhsDown.y, rhsNow.x, rhsNow.y, redPaint);
			}
		}
		String upDown = (lhsPointerId != -2 ? "_" : "-") + " " + (rhsPointerId != -2 ? "_" : "-"); 
		canvas.drawText(upDown, 50, 50, bluePaint);
	}

	private int getJoystickExtent() {
		return getHeight()/4;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		motionEvent = event;
		//calculateOrigin();
		calculateJoystickPositions(motionEvent);
		invalidate();
		return true;
	}

	private void calculateJoystickPositions(MotionEvent motionEvent) {
		if((motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) ||
		   (motionEvent.getActionMasked() == MotionEvent.ACTION_POINTER_DOWN)) {
			detectJoystickDown(motionEvent);
		}
		if((motionEvent.getActionMasked() == MotionEvent.ACTION_UP) ||
		   (motionEvent.getActionMasked() == MotionEvent.ACTION_POINTER_UP)) {
			detectJoystickUp(motionEvent);
		}
		detectCurrentJoystickPositions(motionEvent);
	}

	private void detectCurrentJoystickPositions(MotionEvent motionEvent) {
		int lhsPointerIndex = motionEvent.findPointerIndex(lhsPointerId);
		if(lhsPointerIndex >= 0) {
			lhsNow.x = (int)motionEvent.getX(lhsPointerIndex);
			lhsNow.y = (int)motionEvent.getY(lhsPointerIndex);
		}
		int rhsPointerIndex = motionEvent.findPointerIndex(rhsPointerId);
		if(rhsPointerIndex >= 0) {
			rhsNow.x = (int)motionEvent.getX(rhsPointerIndex);
			rhsNow.y = (int)motionEvent.getY(rhsPointerIndex);
		}
		
	}

	private void detectJoystickUp(MotionEvent motionEvent) {
		int lhsPointerIndex = motionEvent.findPointerIndex(lhsPointerId);
		if(motionEvent.getActionIndex() == lhsPointerIndex) {
			lhsPointerId = -2;
		}

		int rhsPointerIndex = motionEvent.findPointerIndex(rhsPointerId);
		if(motionEvent.getActionIndex() == rhsPointerIndex) {
			rhsPointerId = -2;
		}
	}

	private void detectJoystickDown(MotionEvent motionEvent) {
		if(lhsPointerId == -2) {
			for(int i=0;i<motionEvent.getPointerCount();i++) {
				//on LHS of screen?
				if((motionEvent.getX(i)) < (getWidth()/2)) {
					lhsPointerId = motionEvent.getPointerId(i);
					lhsDown.x = (int)motionEvent.getX(i);
					lhsDown.y = (int)motionEvent.getY(i);
					break;
				}
			}
		}
		
		if(rhsPointerId == -2) {
			for(int i=0;i<motionEvent.getPointerCount();i++) {
				//on LHS of screen?
				if((motionEvent.getX(i)) > (getWidth()/2)) {
					rhsPointerId = motionEvent.getPointerId(i);
					rhsDown.x = (int)motionEvent.getX(i);
					rhsDown.y = (int)motionEvent.getY(i);
					break;
				}
			}
		}
	}
}

