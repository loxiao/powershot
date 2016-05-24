package rainhu.powershot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PowershotActivity extends Activity {

    private Button startserviceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_powershot);
        startserviceBtn = (Button) findViewById(R.id.startservice);
        startserviceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent();
                serviceIntent.setClass(PowershotActivity.this, PowershotService.class);
                startService(serviceIntent);
            }
        });

    }
}
