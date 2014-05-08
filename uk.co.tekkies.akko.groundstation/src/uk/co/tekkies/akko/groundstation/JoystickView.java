package uk.co.tekkies.akko.groundstation;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class JoystickView extends View {
	Paint redPaint;
	Paint bluePaint;
	MotionEvent motionEvent=null;
	Point origin;

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
		if(motionEvent != null) {
			for(int i=0;i<motionEvent.getPointerCount();i++) {
				Paint paint = ((motionEvent.getX(i)-origin.x) < (getWidth()/2)) ? redPaint : bluePaint; 
				Rect touchRect = new Rect(0,0,(int)motionEvent.getX(i)-origin.x, (int)motionEvent.getY(i)-origin.y);
				canvas.drawRect(touchRect, paint);
			}
		}
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
}
