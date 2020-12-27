package com.zalwlf.common.handler;

import com.zalwlf.service.event.TransactionEvent;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * platform
 * <p>全局事务事件处理器</p>
 *
 * @author : lcq
 * @date : 2020-12-14 16:29
 **/
@Component
public class PlatformTransactionHandler {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(rollbackFor = Exception.class)
    public void sendMsg(TransactionEvent event){
        Thread.dumpStack();
        jdbcTemplate.execute("INSERT INTO test VALUES('"+ UUID.randomUUID().toString() +"', 1)");
    }
}
