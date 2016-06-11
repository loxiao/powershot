package rainhu.powershot;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Yu on 2016/5/25.
 */
public class BallView extends FrameLayout {
//    private Button startBtn;
    private TextView tipView;

    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mParams;
    private Context mContext;

   // private

    public BallView(Context context) {
        super(context);
        init(context, null);
    }


    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.ball_view, this);
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mContext = context;

//        startBtn = (Button) findViewById(R.id.startshot);
//        startBtn.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
       // tipView = (TextView) findViewById(R.id.tips);


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float downX , downY;
        float offsetX, offsetY;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                mParams.x = (int)event.getX();
                mParams.y = (int)event.getY();
                mWindowManager.updateViewLayout(this, mParams);
                break;

            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }

        return true;
    }



    public void setTipText(String tips){
        tipView.setText(tips);
    }
    public void setParams(WindowManager.LayoutParams p){
        mParams = p;
    }


    private void dismiss(){
        this.dismiss();

    }

}
