package uk.co.tekkies.akko.groundstation;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class JoystickView extends View {
	Paint paint;
	MotionEvent motionEvent=null;

	public JoystickView(Context context) {
		super(context);
		setupPalette(context);
	}

	public JoystickView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setupPalette(context);
	}
	
	private void setupPalette(Context context) {
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setARGB(0x80, 0xff, 0x00, 0x00);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(motionEvent != null) {
			int[] viewOrigin = new int[2];
			this.getLocationOnScreen(viewOrigin);
			for(int i=0;i<motionEvent.getPointerCount();i++) {
				Rect touchRect = new Rect(0,0,(int)motionEvent.getX(i)-viewOrigin[0], (int)motionEvent.getY(i)-viewOrigin[1]);
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
