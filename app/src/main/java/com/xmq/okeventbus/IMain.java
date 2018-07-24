package com.xmq.okeventbus;

import com.xmq.mylibrary.IOKEvent;

/**
 * Created by xmq on 2018/1/10.
 */

public interface IMain extends IMiddle {
    void doAction(String str);
}
