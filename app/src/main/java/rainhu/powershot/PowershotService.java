package rainhu.powershot;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by hu on 16-5-24.
 */
public class PowershotService extends Service{
    private SensorManager mSensorManager;
    private Vibrator mVibrator;

    private final SensorEventListener mShakeListener = new SensorEventListener() {
        private static final float SENSITIVITY = 16;
        private static final int BUFFER = 5;
        private float[] gravity = new float[3];
        private float average = 0;
        private int fill = 0;

        @Override
        public void onSensorChanged(SensorEvent event) {
            final float alpha = 0.8F;

            for (int i=0;i < 3;i++){
                gravity[i] = alpha * gravity[i] + (1- alpha) * event.values[i];
            }
            float x = event.values[0] - gravity[0];
            float y = event.values[1] - gravity[1];
            float z = event.values[2] - gravity[2];

            if (fill <= BUFFER){
                average += Math.abs(x) + Math.abs(y) + Math.abs(z);
                fill++;
            }else{
                CLog.i("average:"+average);
                CLog.i("average / BUFFER:"+(average / BUFFER));
                if (average / BUFFER >= SENSITIVITY) {
                    handleShakeAction();
                }
                average  = 0;
                fill = 0;
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void handleShakeAction() {
        Toast.makeText(getApplicationContext(),"shake success",Toast.LENGTH_SHORT).show();

        long [] pattern = {100,400};
        mVibrator.vibrate(pattern, -1);

       // shotScreen();

    }


    @Override
    public void onCreate() {
        CLog.i("service oncreate");
        super.onCreate();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mSensorManager.registerListener(mShakeListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),50*1000);
        return super.onStartCommand(intent,flags,startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mSensorManager.unregisterListener(mShakeListener);
    }
}
