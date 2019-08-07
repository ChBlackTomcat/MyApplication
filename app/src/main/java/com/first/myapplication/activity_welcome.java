package com.first.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import activity.LoginActivity;

public class activity_welcome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        handler.sendEmptyMessageDelayed(1,2000);
    }

        private Handler handler = new Handler(new Handler.Callback() {
                     @Override
                     public boolean handleMessage(Message message) {
                             if (message.what == 1){
                                       Intent intent = new Intent(activity_welcome.this, LoginActivity.class);
                                       startActivity(intent);
                                       finish();

                                 }
                            return false;
                         }
                });
    }

