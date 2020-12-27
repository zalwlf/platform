package com.zalwlf.service.event;

import org.springframework.context.ApplicationEvent;

/**
 * platform
 * <p>事务事件</p>
 *
 * @author : lcq
 * @date : 2020-12-14 16:35
 **/
public class TransactionEvent extends ApplicationEvent {


    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public TransactionEvent(Object source) {
        super(source);
    }

}
