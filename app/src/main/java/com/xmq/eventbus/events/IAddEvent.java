package com.xmq.eventbus.events;

import com.xmq.eventbus.IEvent;

public interface IAddEvent extends IEvent {
    void onAdd(String file);
}