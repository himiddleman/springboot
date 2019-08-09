package com.allinpay.core.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

@Slf4j
public class MyClient {
    private static String HOST = "47.99.172.60";

    private static int PORT = 9999;

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            // 创建一个非阻塞的客户端客户端   the non-blocking Socket transport IoConnector
            IoConnector connector = new NioSocketConnector();
            // 设置链接超时时间
            connector.setConnectTimeoutMillis(30000);
            // 添加过滤器
            connector.getFilterChain().addLast( // 添加消息过滤器
                    "codec",
                    // Mina自带的根据文本换行符编解码的TextLineCodec过滤器 看到\r\n就认为一个完整的消息结束了
                    new ProtocolCodecFilter(new TextLineCodecFactory(Charset
                            .forName("UTF-8"), LineDelimiter.UNIX.getValue(),
                            LineDelimiter.UNIX.getValue())));
            // 添加业务逻辑处理器类
            connector.setHandler(new MinaClientHandler());
            IoSession session;
            for (; ; ) {
                try {
                    ConnectFuture future = connector.connect(new InetSocketAddress(
                            HOST, PORT));
                    future.awaitUninterruptibly(); // 等待连接创建完成
                    session = future.getSession();
                    break;
                } catch (RuntimeIoException e) {
                    log.info("Failed to connect：【{}】", e);
                    Thread.sleep(5000);
                }
            }
            WriteFuture mina = session.write("mina");
            mina.awaitUninterruptibly();
            log.info("Mina要关闭了:" + i);
            connector.dispose();
        }
    }

}