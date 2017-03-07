package cn.songm.songmq.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.songm.songmq.core.president.AbstractMsgHandler;
import cn.songm.songmq.core.president.MQProtocol;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * 消息分发处理
 * 
 * @author zhangsong
 *
 */
@ChannelHandler.Sharable
public class MessageBrokerDispatcher extends AbstractMsgHandler {

    private static final Logger LOG = LoggerFactory
            .getLogger(MessageBrokerDispatcher.class);

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, MQProtocol msg)
            throws Exception {
        LOG.debug("SongMQ broker Recceived: {}", msg);
        // 获取请求消息
        // RequestMessage req = (RequestMessage) msg;

    }

}
