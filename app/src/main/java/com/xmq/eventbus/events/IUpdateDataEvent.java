package com.xmq.eventbus.events;


import com.xmq.eventbus.IEvent;

/**
 * Created by LuckyJayce on 2016/7/24.
 */
public interface IUpdateDataEvent extends IEvent {
    void onUpdate(String data);
}
