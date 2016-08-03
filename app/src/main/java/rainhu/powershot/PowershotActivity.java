package rainhu.powershot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class PowershotActivity extends Activity {
    private Button startserviceBtn;
    private Button screenshotBtn;
    private Context mContext;
    private WindowManager mWindowManager;
    private BallView mBallView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

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


        /*
        mWindowManager =  getWindowManager();
        WindowManager.LayoutParams mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        mLayoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        //mLayoutParams.alpha = 0;
        //mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;

        mFlowView= new FlowView(mContext);
        mWindowManager.addView(mFlowView, mLayoutParams );
        mFlowView.setTipText("开始截屏");
        mFlowView.setStartBtnText("点击开始");
        */
    }

    private void startService(){
        Intent serviceIntent = new Intent();
        serviceIntent.setClass(PowershotActivity.this, PowershotService.class);
        startService(serviceIntent);
    }

}
