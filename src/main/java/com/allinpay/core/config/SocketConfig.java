package com.allinpay.core.config;

import com.allinpay.core.handler.MinaServerHandler;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

@Configuration
public class SocketConfig {
    @Bean
    public IoAcceptor ioAcceptor() throws Exception {
        //the non-blocking Socket transport IoAcceptor TCP
        IoAcceptor acceptor = new NioSocketAcceptor();
        //todo 换行符 用于编码、解码，接受响应消息时可以使用String
        acceptor.getFilterChain().addLast("codec",
                // Mina自带的根据文本换行符编解码的TextLineCodec过滤器 看到\r\n就认为一个完整的消息结束了
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset
                        .forName("UTF-8"), LineDelimiter.UNIX.getValue(),
                        LineDelimiter.UNIX.getValue())));

        //多线程模式
        acceptor.getFilterChain().addLast("executor", new ExecutorFilter());
        acceptor.setHandler(new MinaServerHandler());
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        acceptor.bind(new InetSocketAddress(9999));
        System.out.println("服务器在端口：" + 9999 + "已经启动");
        return acceptor;
    }
}
