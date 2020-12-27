package com.zalwlf.service.listener;

import com.zalwlf.common.handler.PlatformTransactionHandler;
import com.zalwlf.service.event.TransactionEvent;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * platform
 * <p>事务监听器</p>
 *
 * @author : lcq
 * @date : 2020-12-14 16:38
 **/
@Component
public class TransactionListener extends PlatformTransactionHandler {

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(TransactionEvent event){
        sendMsg(event);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(TransactionEvent event){
        sendMsg(event);
    }
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(TransactionEvent event){
        sendMsg(event);
    }
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(TransactionEvent event){
        sendMsg(event);
    }

}
