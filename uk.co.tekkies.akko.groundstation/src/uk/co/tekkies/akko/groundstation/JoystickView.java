package uk.co.tekkies.akko.groundstation;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class JoystickView extends View {
	Paint paint;

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
		canvas.drawRect(canvas.getClipBounds(), paint);
	}

}
