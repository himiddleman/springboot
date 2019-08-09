package com.allinpay.core.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MinaClientHandler extends IoHandlerAdapter {

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        String msg = message.toString();
        Thread.sleep(5000);
        log.info("客户端接收到的信息为：" + msg);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        log.info("客户端发生异常..." + cause);
    }
}  