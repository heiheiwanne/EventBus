package com.xmq.okeventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xmq.mylibrary.OKEventBus;

/**
 * Created by xmq on 2018/1/10.
 */

public class Main1Act extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OKEventBus.doEvent(IMain.class).doAction("第二个页面");
        OKEventBus.doEvent(IMain.class).doAction("第二个页面2");
    }
}
