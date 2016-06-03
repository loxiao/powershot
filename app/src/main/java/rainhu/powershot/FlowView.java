package rainhu.powershot;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Yu on 2016/5/25.
 */
public class FlowView extends FrameLayout {
    private Button startBtn;
    private TextView tipView;


    public FlowView(Context context) {
        super(context);
        init(context, null);
    }


    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.flowview, this);



        startBtn = (Button) findViewById(R.id.startshot);
        startBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tipView = (TextView) findViewById(R.id.tips);


    }

    public void setTipText(String tips){
        tipView.setText(tips);
    }
    public void setStartBtnText(String text){
        startBtn.setText(text);
    }

}
