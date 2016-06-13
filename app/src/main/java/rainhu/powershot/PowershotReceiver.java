package rainhu.powershot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by hu on 16-5-24.
 */
public class PowershotReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        CLog.i("PowershotReceiver getaction :"+intent.getAction());

        String action = intent.getAction();
        if(action.equals(Intent.ACTION_BOOT_COMPLETED)){
            Intent serviceIntent = new Intent();
            serviceIntent.setClass(context, PowershotService.class);
            //context.startService(serviceIntent);
        }
    }
}
