package com.xmq.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lucky.utils.app.ToastUtil;
import com.lucky.utils.log.LogUtil;
import com.shizhefei.eventbus.demo.R;
import com.xmq.eventbus.events.IAccountEvent;
import com.xmq.eventbus.events.IAddEvent;
import com.xmq.eventbus.events.IDeleteEvent;
import com.xmq.eventbus.events.IUpdateDataEvent;

public class MainActivity extends AppCompatActivity implements IAccountEvent, IDeleteEvent, IUpdateDataEvent {

    private View nextButton;
    private View fragmentsButton;
    private View threadTestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextButton = findViewById(R.id.button);
        fragmentsButton = findViewById(R.id.button2);
        threadTestButton = findViewById(R.id.button3);

        fragmentsButton.setOnClickListener(onClickListener);
        nextButton.setOnClickListener(onClickListener);
        threadTestButton.setOnClickListener(onClickListener);

        LogUtil.init(true);
        EventBus.register(this);
        EventBus.register(addEvent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.unregister(this);
        EventBus.unregister(addEvent);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            for (int i = 0; i < 1000; i++) {
                Log.e("xmq","exec in mian");
                ToastUtil.showShortMsg(MainActivity.this,"在几天之后根据项目需求，需要添加一张student表，于是DataBaseOpenHelper就出现了第二版:\n");
            }
            for (int i = 0; i < 100; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showShortMsg(MainActivity.this,"在几天之后根据项目需求，需要添加一张student表，于是DataBaseOpenHelper就出现了第二版:\n");
                    }
                }).start();
            }
//            if (v == nextButton) {
//                startActivity(new Intent(getApplicationContext(), NextActivity.class));
//            } else if (v == fragmentsButton) {
//                startActivity(new Intent(getApplicationContext(), FragmentTestActivity.class));
//            } else if (v == threadTestButton) {
//                startActivity(new Intent(getApplicationContext(), ThreadTestActivity.class));
//            }
        }
    };


    @Override //默认就是POSTING
    public void onUpdate(final String s) {
        tip("add s:" + s);
        Log.d("pppp", "onUpdate default: " + Thread.currentThread().hashCode() + "  thread:" + Thread.currentThread());
    }


    @Override
    @Subscribe(threadMode = Subscribe.POSTING)//发事件后直接调用，收发在同一线程中
    public void onLogout(final String name) {
        tip("onLogout name:" + name);
        Log.d("pppp", "onLogout POSTING: " + name + "  " + name + " hashcode" + Thread.currentThread().hashCode() + "  thread:" + Thread.currentThread());
    }

    @Override
    @Subscribe(threadMode = Subscribe.MAIN)//无论之前什么线程发送，都在主线程接收
    public void onDelete(final String file) {
        tip("delete file:" + file);
        Log.d("pppp", "onDelete MAIN: file" + file + " hashcode" + Thread.currentThread().hashCode() + "  thread:" + Thread.currentThread());
    }

    @Override
    @Subscribe(threadMode = Subscribe.BACKGROUND)//在后台线程接收，如果发送的线程不是主线程直接调用这个，如果是主线程就开个线程执行这个方法
    public void onLogin(final String name, final String pass) {
        tip(name + " " + pass);
        Log.d("pppp", "onLogin BACKGROUND: " + name + "  " + name + " hashcode" + pass + Thread.currentThread().hashCode() + "  thread:" + Thread.currentThread());
    }

    private IAddEvent addEvent = new IAddEvent() {
        @Override
        @Subscribe(threadMode = Subscribe.ASYNC)//无论如何都会在开个线程执行（会有个线程池，不一定是真的开个线程，可能取线程池中空闲的线程）
        public void onAdd(final String file) {
            tip("add file:" + file);
            Log.d("pppp", " onAdd ASYNC: " + Thread.currentThread().hashCode() + "  thread:" + Thread.currentThread());
        }
    };

    private void tip(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
