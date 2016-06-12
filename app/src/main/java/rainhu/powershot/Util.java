package rainhu.powershot;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by Yu on 2016/5/25.
 */
public class Util {
    public static void shotscreen(Activity activity){
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();

        Bitmap mp = view.getDrawingCache();

        saveScreenshot(mp);
       // Bitmap mp = Bitmap.createBitmap(view.getDrawingCache(),0,0,);
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();
       // return mp;
    }
    public  static boolean saveScreenshot(Bitmap bitmap){
        if(bitmap != null){
            String sdCardPath = Environment.getExternalStorageDirectory().getPath();
            String filePath = sdCardPath + File.separator + "Pictures"+File.separator + "screenshot.png";

            try {
                File file = new File(filePath);
                FileOutputStream fos =  new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100 , fos);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return  false;
            } catch (IOException e) {
                e.printStackTrace();

            }
            return  true;
        }
        return false;

    }

    public Activity getTopActivity(Context context){
        PackageManager pm = context.getPackageManager();

        return null;
    }


    public static int getStatusBarHeight(Context context){
            int statusBarHeight = 0;
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                 statusBarHeight = context.getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }

        CLog.i("StatusBarHeight : " + statusBarHeight);
        return statusBarHeight;
    }


}
