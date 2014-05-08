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
	Paint paint;
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
		setupOrigin();
		setupPalette();
	}

	private void setupPalette() {
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setARGB(0x80, 0xff, 0x00, 0x00);
	}

	private void setupOrigin() {
		int[] viewOriginArray = new int[2];
		this.getLocationOnScreen(viewOriginArray);
		origin = new Point(viewOriginArray[0], viewOriginArray[1]);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(motionEvent != null) {
			for(int i=0;i<motionEvent.getPointerCount();i++) {
				Rect touchRect = new Rect(0,0,(int)motionEvent.getX(i)-origin.x, (int)motionEvent.getY(i)-origin.y);
				canvas.drawRect(touchRect, paint);
			}
		}
		if(isInEditMode()) {
			canvas.drawRect(new Rect(50,50,150,150), paint);
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		motionEvent = event;
		invalidate();
		return true;
	}
}
