package cn.songm.songmq.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.songm.songmq.core.president.AbstractMessageHandler;
import cn.songm.songmq.core.president.MQProtocol;
import cn.songm.songmq.model.MessageSource;
import cn.songm.songmq.model.MessageType;
import cn.songm.songmq.model.RequestMessage;
import cn.songm.songmq.model.message.QMessage;
import io.netty.channel.ChannelHandlerContext;

/**
 * 消息客户端处理
 * 
 * @author zhangsong
 *
 */
public class MessageClientHandler extends AbstractMessageHandler {

    private static final Logger LOG = LoggerFactory
            .getLogger(MessageClientHandler.class);

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, MQProtocol msg)
            throws Exception {
        LOG.debug("MessageReceived {}", ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOG.debug("ChannelActive {}", ctx);
        
        // 队列消息
        QMessage qm = new QMessage();
        qm.setBody("你好吗".getBytes());
        RequestMessage req = new RequestMessage(qm);
        req.setMsgSource(MessageSource.PRODUCER);
        req.setMsgType(MessageType.QMESSAGE);
        
        ctx.channel().writeAndFlush(req);
    }
}
