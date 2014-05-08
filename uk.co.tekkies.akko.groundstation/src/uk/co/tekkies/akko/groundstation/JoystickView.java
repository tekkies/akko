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
	Point origin=new Point(0,0);
	int lhsPointerId=-2;
	Point lhsDown=new Point();
	Point lhsNow=new Point();

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
		
		if(isInEditMode()) {
			canvas.drawRect(new Rect(50,50,150,150), redPaint);
		} else {
			if(lhsPointerId != -2) {
				canvas.drawCircle(lhsDown.x, lhsDown.y, 15, bluePaint);
				canvas.drawCircle(lhsNow.x, lhsNow.y, 15, redPaint);
				canvas.drawLine(lhsDown.x, lhsDown.y, lhsNow.x, lhsNow.y, redPaint);
			}
		}

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
		if(motionEvent != null) {
			Log.i("ACTION", motionEvent.getActionMasked()+","+motionEvent.getActionIndex());
			//LHS
			int leftPointerIndex = motionEvent.findPointerIndex(lhsPointerId);
			if(((motionEvent.getActionMasked() == MotionEvent.ACTION_UP) ||
				(motionEvent.getActionMasked() == MotionEvent.ACTION_POINTER_UP) 
			   ) && (motionEvent.getActionIndex() == leftPointerIndex)) {
				//LHS UP
				Log.i("ACTION", "LHS UP");
				lhsPointerId = -2;
				lhsDown = null;
			} else {
				if(leftPointerIndex < 0) {
					//Find a pointer on LHS of screen.
					for(int i=0;i<motionEvent.getPointerCount();i++) {
						//on LHS of screen?
						if((motionEvent.getX(i)-origin.x) < (getWidth()/2)) {
							leftPointerIndex = i;
							lhsPointerId = motionEvent.getPointerId(leftPointerIndex);
							lhsDown = new Point((int)motionEvent.getX(leftPointerIndex)-origin.x, (int)motionEvent.getY(leftPointerIndex)-origin.y);
							break;
						}
					}
				}
				
				if(leftPointerIndex >= 0) {
					lhsNow.x = (int)motionEvent.getX(leftPointerIndex)-origin.x;
					lhsNow.y = (int)motionEvent.getY(leftPointerIndex)-origin.y;
				}
			}
			//RHS
		
		} else {
			lhsDown = null;
		}
		
	}
}

