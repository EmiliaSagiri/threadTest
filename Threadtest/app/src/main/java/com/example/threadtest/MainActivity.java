package com.example.threadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {
public static final String TAG = "sb" ;
private Button start;
private  Button stop;
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start_tv);
        stop = (Button) findViewById(R.id.stop_tv);
        textView = (TextView) findViewById(R.id.text);
        // 为控件设置监听器
        start.setOnClickListener(new StartButtonListener());
        stop.setOnClickListener(new EndButtonListener());
    }

    // 创建Handler对象
    Handler handler = new Handler();
    // 新建一个线程对
    Runnable updateThread = new Runnable() {
        // 将要执行的曹祖哦写在线程对象的run方法当中
        public void run() {
            // 输出当前时间
            textView.setText("当前时间：" + new Time(System.currentTimeMillis()));

            /*
             * 调用Handler的postDelayed()方法
             * 这个方法的作用是：将要执行的线程对象放入到队列当中，待时间结束后，运行指定的线程对象
             * 第一个参数是Runnable类型：将要执行的线程对象，第二个参数是long类型：延迟的时间，以毫秒为单位
             */
            handler.postDelayed(updateThread, 1000);
        }
    };

    // 开始按钮
    class StartButtonListener implements View.OnClickListener {

        public void onClick(View v) {
            // 调用Handler的post()方法，将要执行的线程对象放到队列当中，如果队列中没有其他线程，就马上运行
            handler.post(updateThread);
        }

    }

    // 结束按钮
    class EndButtonListener implements View.OnClickListener {

        public void onClick(View v) {
            // 调用Handler的removeCallbacks()方法，删除队列当中未执行的线程对象
            handler.removeCallbacks(updateThread);
        }

    }
}
