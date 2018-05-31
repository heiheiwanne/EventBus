package com.xmq.eventbus.events;


import com.xmq.eventbus.IEvent;

public interface IAccountEvent extends IEvent {

    void onLogin(String name, String pass);

    void onLogout(String name);

}
