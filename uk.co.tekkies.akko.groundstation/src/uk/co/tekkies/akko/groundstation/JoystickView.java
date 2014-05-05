package uk.co.tekkies.akko.groundstation;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
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
			for(int i=0;i<motionEvent.getPointerCount();i++) {
				Rect touchRect = new Rect(0,0,(int)motionEvent.getX(i), (int)motionEvent.getY(i));
				canvas.drawRect(touchRect, paint);
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		motionEvent = event;
		invalidate();
		return true;
	}
}
