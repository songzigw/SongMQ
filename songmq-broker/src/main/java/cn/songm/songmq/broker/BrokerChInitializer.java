package cn.songm.songmq.broker;

import cn.songm.songmq.core.president.AbstractChInitializer;
import cn.songm.songmq.core.president.AbstractMsgHandler;
import io.netty.channel.socket.SocketChannel;

/**
 * Broker 管道初始化器
 * 
 * @author zhangsong
 *
 */
public class BrokerChInitializer extends AbstractChInitializer {

    public BrokerChInitializer(AbstractMsgHandler msgHandler) {
        super(msgHandler);
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(
                // 消息对象的编码
                new MessageObjectEncoder(),
                // 消息对象的解码
                new MessageObjectDecoder());
        super.initChannel(ch);
    }

}
