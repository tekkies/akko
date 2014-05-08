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
	MotionEvent motionEvent=null;
	Point origin;
	Point lhsDown=null;

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
	}

	private void calculateOrigin() {
		int[] viewOriginArray = new int[2];
		this.getLocationOnScreen(viewOriginArray);
		origin = new Point(viewOriginArray[0], viewOriginArray[1]);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		calculateOrigin();
		//TODO: Move call to onTouchEvent - only needs recalculating on touch. 
		calculateJoystickPositions(motionEvent, canvas);
		if(isInEditMode()) {
			canvas.drawRect(new Rect(50,50,150,150), redPaint);
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		motionEvent = event;
		invalidate();
		return true;
	}

	int leftPointerId=-2;
	private void calculateJoystickPositions(MotionEvent motionEvent, Canvas canvas) {
		if(motionEvent != null) {
			Log.i("ACTION", motionEvent.getActionMasked()+","+motionEvent.getActionIndex());
			canvas.drawText(motionEvent.getActionMasked()+","+motionEvent.getActionIndex(), 100, 100, bluePaint);
			//LHS
			int leftPointerIndex = motionEvent.findPointerIndex(leftPointerId);
			if(((motionEvent.getActionMasked() == MotionEvent.ACTION_UP) ||
				(motionEvent.getActionMasked() == MotionEvent.ACTION_POINTER_UP) 
			   ) && (motionEvent.getActionIndex() == leftPointerIndex)) {
				//LHS UP
				Log.i("ACTION", "LHS UP");
				leftPointerId = -2;
				lhsDown = null;
			} else {
				if(leftPointerIndex < 0) {
					//Find a pointer on LHS of screen.
					for(int i=0;i<motionEvent.getPointerCount();i++) {
						//on LHS of screen?
						if((motionEvent.getX(i)-origin.x) < (getWidth()/2)) {
							leftPointerIndex = i;
							leftPointerId = motionEvent.getPointerId(leftPointerIndex);
							lhsDown = new Point((int)motionEvent.getX(leftPointerIndex)-origin.x, (int)motionEvent.getY(leftPointerIndex)-origin.y);
							break;
						}
					}
				}
				
				if(leftPointerIndex >= 0) {
					int x = (int)motionEvent.getX(leftPointerIndex)-origin.x;
					int y = (int)motionEvent.getY(leftPointerIndex)-origin.y;
					//Rect touchRect = new Rect(lhsDown.x, lhsDown.y, (int)motionEvent.getX(leftPointerIndex)-origin.x, (int)motionEvent.getY(leftPointerIndex)-origin.y);
					//canvas.drawRect(touchRect, redPaint);
					canvas.drawCircle(lhsDown.x, lhsDown.y, 15, bluePaint);
					canvas.drawCircle(x, y, 15, redPaint);
					canvas.drawLine(lhsDown.x, lhsDown.y, x, y, redPaint);
				}
			}
			//RHS
		
		} else {
			lhsDown = null;
			canvas.drawText("null", 100, 100, bluePaint);
		}
		
	}
}

