package com.xmq.eventbus.events;


import com.xmq.eventbus.IEvent;

/**
 * Created by LuckyJayce on 2016/7/25.
 */
public interface IMessageEvent extends IEvent {
    void onReceiveMessage(String sendTime, String message);
}
