package com.allinpay.core.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MinaServerHandler extends IoHandlerAdapter {
    @Override
    public void sessionCreated(IoSession session) throws Exception { //用户连接到服务器
        SocketSessionConfig cfg = (SocketSessionConfig) session.getConfig();
        cfg.setSoLinger(-1);
        log.info("[服务建立]" + session.getId());
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String str = (String) message;
        System.out.println(str);


        WriteFuture future = session.write("hello world");

        future.awaitUninterruptibly();
    }

    @Override
    public void messageSent(IoSession session, Object message) { //发送消息结束
//    	log.info("[发送消息结束]" + session.getId() + "message" + message);
        log.info("【{响应结束}】");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        log.info("服务端发送异常..." + cause);
        session.closeNow();
    }
}