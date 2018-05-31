package com.xmq.eventbus.events;


import com.xmq.eventbus.IEvent;

public interface IDeleteEvent extends IEvent {
    public void onDelete(String file);
}