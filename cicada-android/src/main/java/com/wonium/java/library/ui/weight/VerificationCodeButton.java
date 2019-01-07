package com.wonium.java.library.ui.weight;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.wonium.java.library.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName: VerificationCodeButton
 * @Description: 验证码Button
 * @Author: Ethan
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/12/11 23:16
 * @UpdateUser: update user
 * @UpdateDate: 2018/12/11 23:16
 * @UpdateDescription: 更新说明
 * @Version: 1.0.0
 */
public class VerificationCodeButton extends AppCompatButton {
    private Timer timer;
    private String btn_content = "发送验证码";
    private int time = 60;
    private  VerificationCodeButton timeButton;
  @SuppressLint ("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            goTime();
        }
    };

    public VerificationCodeButton(Context context) {
        super(context);
        init(context);
    }

    public VerificationCodeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public VerificationCodeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        timeButton = this;
        timeButton.setText(btn_content);
        timeButton.setBackgroundResource(R.drawable.img_verification_code_bg_yellow);

    }

    public void startTime() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(Message.CONTENTS_FILE_DESCRIPTOR);
            }
        }, 0, 1000);


    }
    public void cancel()
    {
        if (timer!=null)
        {
            timer.cancel();
        }

    }


    public  void goTime() {
        timeButton.setEnabled(false);
        timeButton.setText(time + "秒后重新获取");
        timeButton.setBackgroundResource(R.drawable.img_verification_code_bg_gray);
        time--;
        timeButton.setFocusable(false);
        if (time == 0) {
            time = 60;
            timeButton.setText(btn_content);
            timeButton.setFocusable(true);
            timeButton.setEnabled(true);
            timeButton.setBackgroundResource(R.drawable.img_verification_code_bg_yellow);
          cancel();
        }
    }
}
