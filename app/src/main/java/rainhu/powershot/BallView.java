package rainhu.powershot;

import android.content.Context;
import android.net.Uri;
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

    //记录点击时候球内部的坐标
    private float downInviewX;
    private float downInviewY;

    //记录点击时候相对于屏幕的坐标
    private float downInScreenX;
    private float downInScreenY;

    //记录手指up后，相对于屏幕的坐标
    private float upInScreenX;
    private float upInScreenY;

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
        tipView = (TextView) findViewById(R.id.tips);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downInviewX = event.getX();
                downInviewY = event.getY();
                downInScreenX = event.getRawX();
                downInScreenY = event.getRawY() - getStatusBarHeight();

                //这里进行赋值是为了在up的时候判断球的位置有无移动
                upInScreenX = event.getRawX();
                upInScreenY =  event.getRawY() - getStatusBarHeight();
                break;

            case MotionEvent.ACTION_MOVE:
                upInScreenX = event.getRawX();
                upInScreenY = event.getRawY() - getStatusBarHeight();
                updateViewPosition();
                break;

            case MotionEvent.ACTION_UP:

                if(downInScreenX == upInScreenX && downInScreenY  == upInScreenY){
                  //表示点击球
                    Toast.makeText(mContext, "ball clicked !", Toast.LENGTH_SHORT).show();
                }

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

    private int getStatusBarHeight(){
        return Util.getStatusBarHeight(mContext);
    }

    private void updateViewPosition(){
        mParams.x = (int)(upInScreenX - downInviewX);
        mParams.y = (int)(upInScreenY - downInviewY);
        CLog.i("mParams.x : "+mParams.x+"  mParams.y : "+mParams.y);
        mWindowManager.updateViewLayout(this, mParams);
    }
}
