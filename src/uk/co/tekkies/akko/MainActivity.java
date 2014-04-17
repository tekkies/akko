package uk.co.tekkies.akko;

import java.io.IOException;

import uk.co.tekkies.akko.utils.Arp;
import uk.co.tekkies.akko.utils.Debug;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.textViewArp).setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.textViewArp:
			onClickTextViewArp();
			break;
		}
	}


	private void onClickTextViewArp() {
		Toast.makeText(this, "Arp pressed", Toast.LENGTH_SHORT).show();
		doArp();
	}


	private void doArp() {
		try {
			Arp.createArpMap();
		} catch (IOException e) {
			Debug.printStacktrace(e);
		}
	}
	
    
}
