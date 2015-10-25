package com.aleksandrp.faceclock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.aleksandrp.faceclock.ads.Ads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import at.grabner.circleprogress.CircleProgressView;

public class MainActivity extends AppCompatActivity {


    private CircleProgressView circleHours;
    private CircleProgressView circleMin;
    private CircleProgressView circleSec;

    private TextView textView;

    public static final long MILLIS_IN_HOUR = 3600000;
    public static final long MILLIS_IN_MINUTE = 60000;
    public static final long MILLIS_IN_SECOND = 1000;

    private long timeLong,time, hours, minutes, seconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Ads.showBanner(MainActivity.this);

        initCircle();
        setData();
    }


    private void setData() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    getParamsDate();
                    runOnUiThread(setText);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    private void getParamsDate() {
        timeLong = new Date().getTime();
        time = timeLong;

        hours =  time / MILLIS_IN_HOUR;
        time = time % MILLIS_IN_HOUR;

        minutes = time / MILLIS_IN_MINUTE;
        time = time % MILLIS_IN_MINUTE;

        seconds = time / MILLIS_IN_SECOND;
    }


    Runnable setText = new Runnable() {

        @Override
        public void run() {
            textView.setText(new SimpleDateFormat("HH:mm:ss").format(timeLong) + "");

            int hoursText  = Integer.parseInt(new SimpleDateFormat("HH").format(timeLong));
            circleHours.setValue(hoursText);
            circleMin.setValue(minutes);
            circleSec.setValue(seconds);
        }
    };


    private void initCircle() {
        circleHours = (CircleProgressView) findViewById(R.id.circleView_hours);
        circleMin = (CircleProgressView) findViewById(R.id.circleView_min);
        circleSec = (CircleProgressView) findViewById(R.id.circleView_sec);

        textView = (TextView) findViewById(R.id.tv_time);


        circleSec.setBarColor(getResources().getColor(R.color.Chocolate),
                getResources().getColor(R.color.red));

        circleMin.setBarColor(getResources().getColor(R.color.LimeGreen),
                getResources().getColor(R.color.LawnGreen),
                getResources().getColor(R.color.GreenYellow));

        circleHours.setBarColor(getResources().getColor(R.color.MediumBlue),
                getResources().getColor(R.color.blue));
    }
}
