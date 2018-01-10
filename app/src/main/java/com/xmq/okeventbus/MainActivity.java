package com.xmq.okeventbus;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.xmq.mylibrary.OKEventBus;
import com.xmq.mylibrary.OKSubscribe;

public class MainActivity extends AppCompatActivity implements IMain{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OKEventBus.register(this);
        startActivity(new Intent(this,Main1Act.class));
    }



    @OKSubscribe(thread = OKSubscribe.ASYNC)
    @Override
    public void doAction(final String str) {
        try {
            Thread.sleep(6*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OKEventBus.unregister(this);
    }
}
