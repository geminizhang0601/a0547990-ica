package uk.ac.tees.scedt.mad.a0547990.a0547990icaapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Timer;
import  android.content.Intent;
import java.util.TimerTask;

public class WelcomeActivit extends AppCompatActivity{
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcomeactivity);  //快捷键alt+enter

        //延时操作
        Timer timer = new Timer();
        timer.schedule(timetast, 2000);
    }
    TimerTask timetast = new TimerTask() {
        @Override
        public void run() {

            startActivity(new Intent(WelcomeActivit.this,LoginActivity.class));//跳转登录界面
        }
    };



}

