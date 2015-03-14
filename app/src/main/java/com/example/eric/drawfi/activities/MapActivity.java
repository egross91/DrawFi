package com.example.eric.drawfi.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.eric.drawfi.R;
import com.example.eric.drawfi.draw.WifiMap;

public class MapActivity extends Activity {
    private DrawThread mDrawThread;
    private ImageView mWifiImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);

        this.mWifiImageView = (ImageView)findViewById(R.id.wifiMapImageView);
        this.mDrawThread = new DrawThread();
        this.mDrawThread.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();

        this.mDrawThread.interrupt();
    }

    /**
     * API
     */
    public void update(final Bitmap map) {
        this.mWifiImageView.setImageBitmap(map);
    }

    /**
     * BUTTON HANDLERS
     */
    public void doneButtonHandler(View view) {
        this.mDrawThread.interrupt();
    }

    /**
     * HELPER METHODS
     */
    public void displaySimpleToast(CharSequence dialog) {
        Toast toast = Toast.makeText(getApplicationContext(), dialog, Toast.LENGTH_LONG);
        toast.show();
    }

    private class DrawThread extends Thread {
        private WifiMap wifiMap;

        public DrawThread() {
            this.wifiMap = new WifiMap(WifiMap.DEFAULT_HEIGHT, WifiMap.DEFAULT_WIDTH);
        }

        public DrawThread(int n) {
            this.wifiMap = new WifiMap(n, n);
        }

        public DrawThread(int height, int width) {
            this.wifiMap = new WifiMap(height, width);
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                // Update the map with the current position.
                wifiMap.update();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        update(wifiMap.getMap());
                    }
                });
            }
        }
    }
}
