package rainhu.powershot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PowershotActivity extends Activity {
    private Button startserviceBtn;
    private Button screenshotBtn;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_powershot);
        mContext = this;
        startserviceBtn = (Button) findViewById(R.id.startservice);
        startserviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();
                finish();
            }
        });
        screenshotBtn = (Button) findViewById(R.id.screenshotBtn);
        screenshotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.shotscreen((Activity) mContext);

            }
        });

    }

    private void startService(){
        Intent serviceIntent = new Intent();
        serviceIntent.setClass(PowershotActivity.this, PowershotService.class);
        startService(serviceIntent);
    }


}
