package rainhu.powershot;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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



}