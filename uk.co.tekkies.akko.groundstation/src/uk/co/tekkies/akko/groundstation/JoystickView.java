package uk.co.tekkies.akko.groundstation;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
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
	PointF lhsStick=new PointF(0,0);
	PointF rhsStick=new PointF(0,0);

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
				//canvas.drawCircle(lhsDown.x, lhsDown.y, getJoystickExtent(), extentPaint);
				canvas.drawRect(lhsDown.x-getJoystickExtent(), lhsDown.y-getJoystickExtent(), lhsDown.x+getJoystickExtent(), lhsDown.y+getJoystickExtent(), extentPaint);
				canvas.drawCircle(lhsNow.x, lhsNow.y, 15, redPaint);
				canvas.drawLine(lhsDown.x, lhsDown.y, lhsNow.x, lhsNow.y, redPaint);
			}
			if(rhsPointerId != -2) {
				canvas.drawCircle(rhsDown.x, rhsDown.y, getJoystickExtent(), extentPaint);
				//canvas.drawRect(rhsDown.x-getJoystickExtent(), rhsDown.y-getJoystickExtent(), rhsDown.x+getJoystickExtent(), rhsDown.y+getJoystickExtent(), extentPaint);
				canvas.drawCircle(rhsNow.x, rhsNow.y, 15, redPaint);
				canvas.drawLine(rhsDown.x, rhsDown.y, rhsNow.x, rhsNow.y, redPaint);
			}
		}
		String upDown = (lhsPointerId != -2 ? "_" : "-") + " " + (rhsPointerId != -2 ? "_" : "-"); 
		canvas.drawText(upDown, 50, 50, bluePaint);
		canvas.drawText("lx:"+lhsStick.x, 50, 100, bluePaint);
		canvas.drawText("ly:"+lhsStick.y, 50, 120, bluePaint);
		canvas.drawText("rx:"+rhsStick.x, 50, 140, bluePaint);
		canvas.drawText("ry:"+rhsStick.y, 50, 160, bluePaint);
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
			//limitCoordinatesCircle(lhsDown, lhsNow);
			limitCoordinatesRect(lhsDown, lhsNow);
			lhsStick.x = (lhsNow.x-lhsDown.x)/(float)getJoystickExtent();
			lhsStick.y = (lhsDown.y-lhsNow.y)/(float)getJoystickExtent();
		}
		int rhsPointerIndex = motionEvent.findPointerIndex(rhsPointerId);
		if(rhsPointerIndex >= 0) {
			rhsNow.x = (int)motionEvent.getX(rhsPointerIndex);
			rhsNow.y = (int)motionEvent.getY(rhsPointerIndex);
			limitCoordinatesCircle(rhsDown, rhsNow);
			//limitCoordinatesRect(rhsDown, rhsNow);
			rhsStick.x = (rhsNow.x-rhsDown.x)/(float)getJoystickExtent();
			rhsStick.y = (rhsDown.y-rhsNow.y)/(float)getJoystickExtent();
		}
	}

	private void limitCoordinatesRect(Point down, Point now) {
		int x = now.x - down.x;
		int y = now.y - down.y;
		x = Math.min(x, getJoystickExtent());
		x = Math.max(x, 0-getJoystickExtent());
		y = Math.min(y, getJoystickExtent());
		y = Math.max(y, 0-getJoystickExtent());
		now.x = down.x+x;
		now.y = down.y+y;
	}

	private void limitCoordinatesCircle(Point down, Point now) {
		float theta = (float) Math.atan2(now.y-down.y, now.x-down.x);
		float hyp= (float) Math.sqrt((now.y-down.y)*(now.y-down.y)+(now.x-down.x)*(now.x-down.x));
		hyp = Math.min(hyp, getJoystickExtent());
		now.x = (int) (down.x + (Math.cos(theta) * hyp));
		now.y = (int) (down.y + (Math.sin(theta) * hyp));
	}
	

	private void detectJoystickUp(MotionEvent motionEvent) {
		int lhsPointerIndex = motionEvent.findPointerIndex(lhsPointerId);
		if(motionEvent.getActionIndex() == lhsPointerIndex) {
			lhsPointerId = -2;
			lhsStick.x = 0;
			lhsStick.y = 0;
		}

		int rhsPointerIndex = motionEvent.findPointerIndex(rhsPointerId);
		if(motionEvent.getActionIndex() == rhsPointerIndex) {
			rhsPointerId = -2;
			rhsStick.x = 0;
			rhsStick.y = 0;
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

